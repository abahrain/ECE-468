import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import org.antlr.v4.runtime.tree.*;

 public class MyVisitor extends MicroBaseVisitor<NodeStructure>
 {
   public ArrayList<String> output = new ArrayList<String>();
   public ArrayList<String> pushStack = new ArrayList<String>();
   private Stack<String> labelStack = new Stack<String>();
   private Stack<String> continueStack = new Stack<String>();
   private Stack<String> breakStack = new Stack<String>();
   private Stack<String> functionStack = new Stack<String>();
   private Stack<Integer> functionPopNumberStack = new Stack<Integer>();
   private Stack<ArrayList<NodeStructure>> factorStack = new Stack<ArrayList<NodeStructure>>();
   private Stack<ArrayList<NodeStructure>> exprStack = new Stack<ArrayList<NodeStructure>>();
   protected Map<String, Map<String, NodeStructure>> tableMap = new LinkedHashMap<String, Map<String, NodeStructure>>();
   protected Map<String, Integer> functionMap = new LinkedHashMap<String, Integer>();
   protected Map<String, ArrayList<String>> tempMap = new LinkedHashMap<String, ArrayList<String>>();
   private int tempIndex = 0;
   public int finalTempIndex = 0;
   private int varIndex = 0;
   private int paramIndex = 0;
   private int labelIndex = 0;
   private int countPUSH = 0;
   private String functionRecord = "GLOBAL";
   
   public MyVisitor(SymbolTable table, Map<String, Integer> functionMap)
   {
     this.functionMap = functionMap;
     for (Boresight scope : table.scopeStack.subList(0, table.scopeStack.size()))
     {
       Map<String, NodeStructure> varMap = new LinkedHashMap<String, NodeStructure>();
       if (scope.type.equalsIgnoreCase("GLOBAL")) {
         for (String key : scope.symbolMap.keySet()) {
           if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) {
             varMap.put(key, new NodeStructure(key, 1));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) {
             varMap.put(key, new NodeStructure(key, 2));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) {
             varMap.put(key, new NodeStructure(key, 5));
           } else {
             System.out.println("error@constructor");
           }
         }
       } else {
         for (String key : scope.symbolMap.keySet()) {
           if (((BuildNode)scope.symbolMap.get(key)).descriptor.query)
           {
             if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) {
               varMap.put(key, new NodeStructure(createVar(true), 1));
             } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) {
               varMap.put(key, new NodeStructure(createVar(true), 2));
             } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) {
               varMap.put(key, new NodeStructure(key, 5));
             } else {
               System.out.println("error@constructor");
             }
           }
           else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) {
             varMap.put(key, new NodeStructure(createVar(false), 1));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) {
             varMap.put(key, new NodeStructure(createVar(false), 2));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) {
             varMap.put(key, new NodeStructure(key, 5));
           } else {
             System.out.println("error@constructor");
           }
         }
       }
       this.tableMap.put(scope.type, varMap);
       this.varIndex = 0;
       this.paramIndex = 0;
     }
   }
   
   private String createVar(boolean query)
   {
     if (query)
     {
       this.paramIndex += 1;
       return "$P" + Integer.toString(this.paramIndex);
     }
     this.varIndex += 1;
     return "$L" + Integer.toString(this.varIndex);
   }
   
   public NodeStructure findIdNode(String name, String scopeName)
   {
     if (((Map<?, ?>)this.tableMap.get(scopeName)).get(name) == null)
     {
       if (((Map<?, ?>)this.tableMap.get("GLOBAL")).get(name) == null)
       {
         System.out.println("can't find assign variable, error@findIdNode");
         return null;
       }
       return (NodeStructure)((Map<?, ?>)this.tableMap.get("GLOBAL")).get(name);
     }
     return (NodeStructure)((Map<?, ?>)this.tableMap.get(scopeName)).get(name);
   }
   
   public NodeStructure visitPrimary(MicroParser.PrimaryContext ctx)
   {
     if (ctx.expression() != null) {
       return (NodeStructure)visit(ctx.expression());
     }
     if (ctx.name() != null) {
       return findIdNode(ctx.name().getText(), this.functionRecord);
     }
     if (ctx.INTLITERAL() != null)
     {
       NodeStructure newNode = new NodeStructure(createTemp(), 1);
       this.output.add("STOREI " + ctx.INTLITERAL().getText() + " " + newNode.content);
       
       return newNode;
     }
     NodeStructure newNode = new NodeStructure(createTemp(), 2);
     this.output.add("STOREF " + ctx.FLOATLITERAL().getText() + " " + newNode.content);
     
     return newNode;
   }
   
   public NodeStructure visitFunction_declaration(MicroParser.Function_declarationContext ctx)
   {
     ArrayList<String> newTempList = new ArrayList<String>();
     
     this.output.add("LABEL " + ctx.name().getText());
     this.functionRecord = ctx.name().getText();
     this.tempMap.put(this.functionRecord, newTempList);
     this.output.add("LINK ");
     visitChildren(ctx);
     this.tempIndex = 0;
     if (ctx.any_type().getText().equals("VOID")) {
       this.output.add("RET");
     }
     return null;
   }
   
   public NodeStructure visitExpression_call(MicroParser.Expression_callContext ctx)
   {
     this.functionPopNumberStack.push(Integer.valueOf(this.countPUSH));
     this.countPUSH = 0;
     if (ctx.expression_list() != null) {
       visit(ctx.expression_list());
     }
     this.output.add("PUSH ");
     String[] reverseList = new String[this.countPUSH];
     for (int i = 0; i < this.countPUSH; i++) {
       reverseList[i] = ((String)this.functionStack.pop());
     }
     for (int i = this.countPUSH - 1; i >= 0; i--) {
       this.output.add("PUSH " + reverseList[i]);
     }
     this.output.add("JSR " + ctx.name().getText());
     for (int i = 0; i < this.countPUSH; i++) {
       this.output.add("POP ");
     }
     this.countPUSH = ((Integer)this.functionPopNumberStack.pop()).intValue();
     
     NodeStructure newNode = new NodeStructure(createTemp(), ((Integer)this.functionMap.get(this.functionRecord)).intValue());
     this.output.add("POP " + newNode.content);
     return newNode;
   }
   
   public NodeStructure visitExpression_list(MicroParser.Expression_listContext ctx)
   {
	 NodeStructure exprNode = (NodeStructure) visit((ParseTree) ctx.expression());
     
     this.functionStack.push(exprNode.content);
     this.countPUSH += 1;
     if (!"".equals(((ParseTree) ctx.expression_list_repeat()).getText())) {
       visit((ParseTree) (ctx.expression_list_repeat()));
     }
     return null;
   }
   
   public NodeStructure visitExpression_list_repeat(MicroParser.Expression_list_repeatContext ctx)
   {
	 ParseTree temp = (ParseTree)ctx.expression();
     NodeStructure exprNode = (NodeStructure)visit(temp);
     
     this.functionStack.push(exprNode.content);
     this.countPUSH += 1;
     temp = (ParseTree)ctx.expression_list_repeat();
     if (!"".equals(temp.getText())) {
       visit(temp);
     }
     return null;
   }
   
   public NodeStructure visitRe_turn(MicroParser.Re_turnContext ctx)
   {
     NodeStructure exprNode = (NodeStructure)visit(ctx.expression());
     NodeStructure tempNode = new NodeStructure(createTemp(), exprNode.type);
     if (exprNode.type == 1)
     {
       this.output.add("STOREI " + exprNode + " " + tempNode);
       this.output.add("STOREI " + tempNode + " $R");
     }
     else
     {
       this.output.add("STOREF " + exprNode + " " + tempNode);
       this.output.add("STOREF " + tempNode + " $R");
     }
     this.output.add("RET");
     return null;
   }
   
   public NodeStructure visitParameter_declaration(MicroParser.Parameter_declarationContext ctx)
   {
     if (ctx.variable_type().getText().equalsIgnoreCase("INT")) {
       new NodeStructure(ctx.name().getText(), 1);
     } else {
       new NodeStructure(ctx.name().getText(), 2);
     }
     return null;
   }
   
   public NodeStructure visitExpression(MicroParser.ExpressionContext ctx)
   {
     if (!"".equals(ctx.pre_expression().getText()))
     {
       ArrayList<NodeStructure> exprList = new ArrayList<NodeStructure>();
       this.exprStack.push(exprList);
       visit(ctx.pre_expression());
       NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
       ((ArrayList<NodeStructure>)this.exprStack.peek()).add(factorNode);
       NodeStructure resolveNode = resolve((ArrayList<NodeStructure>)this.exprStack.pop());
       
       return resolveNode;
     }
     NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
     return factorNode;
   }
   
   public NodeStructure visitPre_expression(MicroParser.Pre_expressionContext ctx)
   {
     if (!"".equals(ctx.pre_expression().getText())) {
       visit(ctx.pre_expression());
     }
     NodeStructure opNode = new NodeStructure(ctx.addition_operation().getText(), 3);
     NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
     ((ArrayList<NodeStructure>)this.exprStack.peek()).add(factorNode);
     ((ArrayList<NodeStructure>)this.exprStack.peek()).add(opNode);
     
     return null;
   }
   
   public NodeStructure visitFactor(MicroParser.FactorContext ctx)
   {
     if (!"".equals(ctx.pre_factor().getText()))
     {
       ArrayList<NodeStructure> factorList = new ArrayList<NodeStructure>();
       this.factorStack.push(factorList);
       visit(ctx.pre_factor());
       NodeStructure postfixNode = (NodeStructure)visit(ctx.post_expression());
       ((ArrayList<NodeStructure>)this.factorStack.peek()).add(postfixNode);
       NodeStructure resolveNode = resolve((ArrayList<NodeStructure>)this.factorStack.pop());
       
       return resolveNode;
     }
     return (NodeStructure)visit(ctx.post_expression());
   }
   
   public NodeStructure visitPre_factor(MicroParser.Pre_factorContext ctx)
   {
     if (!"".equals(ctx.pre_factor().getText())) {
       visit(ctx.pre_factor());
     }
     NodeStructure opNode = new NodeStructure(ctx.multiplication_operation().getText(), 3);
     NodeStructure postfixNode = (NodeStructure)visit(ctx.post_expression());
     ((ArrayList<NodeStructure>)this.factorStack.peek()).add(postfixNode);
     ((ArrayList<NodeStructure>)this.factorStack.peek()).add(opNode);
     
     return null;
   }
   
   public NodeStructure visitWrite(MicroParser.WriteContext ctx)
   {
     String[] idArray = ctx.name_list().getText().split(",");
     for (int i = 0; i < idArray.length; i++)
     {
       NodeStructure newNode = findIdNode(idArray[i], this.functionRecord);
       if (newNode.type == 1) {
         this.output.add("WRITEI " + newNode.content);
       } else if (newNode.type == 5) {
         this.output.add("WRITES " + newNode.content);
       } else {
         this.output.add("WRITEF " + newNode.content);
       }
     }
     return null;
   }
   
   public NodeStructure visitRead(MicroParser.ReadContext ctx)
   {
     String[] idArray = ctx.name_list().getText().split(",");
     for (int i = 0; i < idArray.length; i++)
     {
       NodeStructure newNode = findIdNode(idArray[i], this.functionRecord);
       if (newNode.type == 1) {
         this.output.add("READI " + newNode.content);
       } else {
         this.output.add("READF " + newNode.content);
       }
     }
     return null;
   }
   
   public NodeStructure visitAssignment_frame(MicroParser.Assignment_frameContext ctx)
   {
     NodeStructure exprNode = (NodeStructure)visit(ctx.expression());
     NodeStructure newNode = findIdNode(ctx.name().getText(), this.functionRecord);
     if (newNode.type == 1) {
       this.output.add("STOREI " + exprNode.content + " " + newNode.content);
     } else {
       this.output.add("STOREF " + exprNode.content + " " + newNode.content);
     }
     return null;
   }
   
   public NodeStructure visitWhile_statement(MicroParser.While_statementContext ctx)
   {
     String newLabel = createLabel();
     this.output.add("LABEL " + newLabel);
     String newLabel2 = createLabel();
     this.labelStack.add(newLabel2);
     NodeStructure comp = (NodeStructure)visit(ctx.condition());
     
     this.continueStack.push("JUMP " + newLabel);
     this.breakStack.push("JUMP " + newLabel2);
     this.output.add(comp.content + " " + newLabel2);
     visit(ctx.statement_list());
     this.output.add("JUMP " + newLabel);
     this.output.add("LABEL " + newLabel2);
     this.breakStack.pop();
     this.continueStack.pop();
     return null;
   }
   
   public NodeStructure visitIf_statement(MicroParser.If_statementContext ctx)
   {
     if (!"".equals(ctx.else_portion().getText()))
     {
       NodeStructure comp = (NodeStructure)visit(ctx.condition());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.statement_list());
       }
       else if (comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         visit(ctx.else_portion());
         this.output.add("LABEL " + newLabel2);
       }
       else
       {
         String newLabel = createLabel();
         this.output.add(comp.content + " " + newLabel);
         visit(ctx.statement_list());
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         this.output.add("JUMP " + newLabel2);
         this.output.add("LABEL " + newLabel);
         visit(ctx.else_portion());
         this.output.add("LABEL " + newLabel2);
       }
     }
     else
     {
       NodeStructure comp = (NodeStructure)visit(ctx.condition());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.statement_list());
       }
       else if (!comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.output.add(comp.content + " " + newLabel2);
         visit(ctx.statement_list());
         this.output.add("LABEL " + newLabel2);
       }
     }
     return null;
   }
   
   public NodeStructure visitElse_portion(MicroParser.Else_portionContext ctx)
   {
     visit(ctx.statement_list());
     return null;
   }
   
   public NodeStructure visitCondition(MicroParser.ConditionContext ctx)
   {
     NodeStructure op1 = (NodeStructure)visit(ctx.expression(0));
     
     visit(ctx.comparison_operator());
     NodeStructure op2 = (NodeStructure)visit(ctx.expression(1));
     return resolveComp(op1, op2, ctx.comparison_operator().getText());
   }
   
   public String resolveDoComp(String input)
   {
	   switch(input)
	   {
	   	case("GEI"):
	   		return input.replace("GEI", "LTI");
		case("LEI"):
			return input.replace("LEI", "GTI");
		case("NEI"):
			return input.replace("NEI", "EQI");
		case("EQI"):
			return input.replace("EQI", "NEI");
		case("GTI"):
			return input.replace("GTI", "LEI");
		case("LTI"):
			return input.replace("LTI", "GEI");
		case("GEF"):
			  return input.replace("GEF", "LTF");
		case("LEF"):
			return input.replace("LEF", "GTF");
		case("NEF"):
			return input.replace("NEF", "EQF");
		case("EQF"):
			return input.replace("EQF", "NEF");
		case("GTF"):
			return input.replace("GTF", "LEF");
		case("LTF"):
			return input.replace("LTF", "GEF");
		default:
			return null;
	   }
   }
   
   public NodeStructure resolveComp(NodeStructure left_variable, NodeStructure right_variable, String operation)
   {
     if ((left_variable.type == 1) && (right_variable.type == 1))
     {
    	 switch(operation)
    	 {
    	 	case("<"):
    	 		return new NodeStructure("GEI " + left_variable.content + " " + right_variable.content, 4);
    	 	case(">"):
    	 		return new NodeStructure("LEI " + left_variable.content + " " + right_variable.content, 4);
    	 	case("="):
    	 		return new NodeStructure("NEI " + left_variable.content + " " + right_variable.content, 4);
    	 	case("!="):
    	 		return new NodeStructure("EQI " + left_variable.content + " " + right_variable.content, 4);
    	 	case("<="):
    	 		return new NodeStructure("GTI " + left_variable.content + " " + right_variable.content, 4);
    	 	case(">="):
    	 		return new NodeStructure("LTI " + left_variable.content + " " + right_variable.content, 4);
    	 	default:
    	 		return null;
    	 }
     }
     else
     {
    	 switch(operation)
    	 {
    	 	case("<"):
    	 		return new NodeStructure("GEF " + left_variable.content + " " + right_variable.content, 4);
	    	case(">"):
	    		return new NodeStructure("LEF " + left_variable.content + " " + right_variable.content, 4);
	    	case("="):
	    		return new NodeStructure("NEF " + left_variable.content + " " + right_variable.content, 4);
	      	case("!="):
	      		return new NodeStructure("EQF " + left_variable.content + " " + right_variable.content, 4);
	    	case("<="):
	    		return new NodeStructure("GTF " + left_variable.content + " " + right_variable.content, 4);
	      	case(">="):
	      		return new NodeStructure("LTF " + left_variable.content + " " + right_variable.content, 4);
	      	default:
	      		return null;
    	 }
     }
   }
   
   public String createLabel()
   {
     this.labelIndex += 1;
     return "label" + Integer.toString(this.labelIndex);
   }
   
   public String createTemp()
   {
     this.tempIndex += 1;
     if (this.tempIndex > this.finalTempIndex) {
       this.finalTempIndex = this.tempIndex;
     }
     ((ArrayList<String>)this.tempMap.get(this.functionRecord)).add("$T" + Integer.toString(this.tempIndex));
     return "$T" + Integer.toString(this.tempIndex);
   }
   
   public NodeStructure resolve(ArrayList<NodeStructure> input)
	  {
	    while (input.size() >= 3)
	    {
	      NodeStructure left_variable = (NodeStructure)input.get(0);
	      NodeStructure operation = (NodeStructure)input.get(1);
	      NodeStructure right_operation = (NodeStructure)input.get(2);
	      
	      NodeStructure newNode = new NodeStructure(createTemp(), left_variable.type);
	      switch(operation.content)
	      {
	      	case("+"):
	      		if (left_variable.type == 1)
	      		{
	      			String output = "ADDI " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	      			this.output.add(output);
	      		}
	      		else
	      		{
	      			String output = "ADDF " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	      			this.output.add(output);
	      		}
	      	break;
	      	case("-"):
	      		if (left_variable.type == 1)
	      		{
	      			String output = "SUBI " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	      			this.output.add(output);
	      		}
	      		else
	      		{
	      			String output = "SUBF " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	      			this.output.add(output);
	      		}
	      	break;
	        case("*"):
	        	if (left_variable.type == 1)
	        	{
	        		String output = "MULTI " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	        		this.output.add(output);
	        	}
	        	else
	        	{
	        		String output = "MULTF " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	        		this.output.add(output);
	        	}
	        break;
	        case("/"):
	        	if (left_variable.type == 1)
	        	{
	        		String output = "DIVI " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	        		this.output.add(output);
	        	}
	        	else
	        	{
	        		String output = "DIVF " + left_variable.content + " " + right_operation.content + " " + newNode.content;
	        		this.output.add(output);
	        	}
	        break;
	      }
	      for(int a = 0; a < 3; a++)
	      {
	    	  input.remove(0);
	      }
	      
	      input.add(0, newNode);
	    }
	    NodeStructure returnValue = (NodeStructure)input.get(0);
	    input.removeAll(input);
	    return returnValue;
	  }
   
   public String printOutput()
   {
     String result = "";
     for (int i = 0; i < this.output.size(); i++)
     {
       result = result + (String)this.output.get(i);
       result = result + "\n";
     }
     return result;
   }
 }
