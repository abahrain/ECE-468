import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import org.antlr.v4.runtime.tree.*;

public class MyVisitor extends MicroBaseVisitor<Node>
{
	
	public ArrayList<String> output = new ArrayList<>();
	public ArrayList<String> Stack_of_Pushes = new ArrayList<>();
	private Stack<String> Stack_of_Labels = new Stack<>();
	private Stack<String> Stack_of_Functions = new Stack<>();
	private Stack<Integer> functionPopNumberStack = new Stack<>();
	private Stack<ArrayList<Node>> factorStack = new Stack<>();
	private Stack<ArrayList<Node>> exprStack = new Stack<>();
	protected Map<String, Map<String, Node>> tableMap = new LinkedHashMap<>();
	protected Map<String, Integer> functionMap = new LinkedHashMap<>();
	protected Map<String, ArrayList<String>> tempMap = new LinkedHashMap<>();
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
		for(Scope scope : table.scopeStack.subList(0,table.scopeStack.size()))
		{
			Map<String, Node> varMap = new LinkedHashMap<>();
			if(scope.type.equals("GLOBAL"))
			{
				for (String key : scope.symbolMap.keySet()) 
				{
			          if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT)
			          {
			            varMap.put(key, new Node(key, 1));
			          } 
			          else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) 
			          {
			            varMap.put(key, new Node(key, 2));
			          } 
			          else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) 
			          {
			            varMap.put(key, new Node(key, 5));
			          } 
			          else
			          {
			            System.out.println("ERROR");
			          }
			        }
			      } else {
			        for (String key : scope.symbolMap.keySet())
			        {
			          if (((Symbol)scope.symbolMap.get(key)).descriptor.isParameter)
			          {
			            if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) 
			            {
			              varMap.put(key, new Node(createVar(true), 1));
			            } 
			            else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) 
			            {
			              varMap.put(key, new Node(createVar(true), 2));
			            } 
			            else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING)
			            {
			              varMap.put(key, new Node(key, 5));
			            } 
			            else 
			            {
			              System.out.println("ERROR");
			            }
			          }
			          else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) 
			          {
			            varMap.put(key, new Node(createVar(false), 1));
			          } 
			          else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) 
			          {
			            varMap.put(key, new Node(createVar(false), 2));
			          }
			          else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING)
			          {
			            varMap.put(key, new Node(key, 5));
			          } 
			          else 
			          {
			            System.out.println("ERROR");
			          }
			        }
			      }
			      this.tableMap.put(scope.type, varMap);
			      this.varIndex = 0;
			      this.paramIndex = 0;
			    }
			  }
			  
			  private String createVar(boolean isParameter)
			  {
			    if (isParameter)
			    {
			      this.paramIndex += 1;
			      return "$P" + Integer.toString(this.paramIndex);
			    }
			    this.varIndex += 1;
			    return "$L" + Integer.toString(this.varIndex);
			  }
			  
			  public Node findNameNode(String name, String scopeName)
			  {
			    if (((Map)this.tableMap.get(scopeName)).get(name) == null)
			    {
			      if (((Map)this.tableMap.get("GLOBAL")).get(name) == null)
			      {
			        System.out.println("ERROR");
			        return null;
			      }
			      return (Node)((Map)this.tableMap.get("GLOBAL")).get(name);
			    }
			    return (Node)((Map)this.tableMap.get(scopeName)).get(name);
			  }
			  
			  public Node visitPrimary(MicroParser.PrimaryContext ctx)
			  {
			    if (ctx.expression() != null) 
			    {
			      return (Node)visit(ctx.expression());
			    }
			    if (ctx.name() != null) 
			    {
			      return findNameNode(ctx.name().getText(), this.functionRecord);
			    }
			    if (ctx.INTLITERAL() != null)
			    {
			      Node newNode = new Node(createTemp(), 1);
			      this.output.add("STOREI " + ctx.INTLITERAL().getText() + " " + newNode.content);
			      
			      return newNode;
			    }
			    Node newNode = new Node(createTemp(), 2);
			    this.output.add("STOREF " + ctx.FLOATLITERAL().getText() + " " + newNode.content);
			    
			    return newNode;
			  }
			  
			  public Node visitFunction_declaration(MicroParser.Function_declarationContext ctx)
			  {
			    ArrayList<String> newTempList = new ArrayList<>();
			    
			    this.output.add("LABEL " + ctx.name().getText());
			    this.functionRecord = ctx.name().getText();
			    this.tempMap.put(this.functionRecord, newTempList);
			    this.output.add("LINK ");
			    visitChildren(ctx);
			    this.tempIndex = 0;
			    if (ctx.any_type().getText().equals("VOID"))
			    {
			      this.output.add("RET");
			    }
			    return null;
			  }
			  
			  public Node visitExpression_call(MicroParser.Expression_callContext ctx)
			  {
			    this.functionPopNumberStack.push(Integer.valueOf(this.countPUSH));
			    this.countPUSH = 0;
			    if (ctx.expression_list() != null)
			    {
			      visit(ctx.expression_list());
			    }
			    this.output.add("PUSH ");
			    String[] reverseList = new String[this.countPUSH];
			    for (int i = 0; i < this.countPUSH; i++) {
			      reverseList[i] = ((String)this.Stack_of_Functions.pop());
			    }
			    for (int i = this.countPUSH - 1; i >= 0; i--) 
			    {
			      this.output.add("PUSH " + reverseList[i]);
			    }
			    this.output.add("JSR " + ctx.name().getText());
			    for (int i = 0; i < this.countPUSH; i++) 
			    {
			      this.output.add("POP ");
			    }
			    this.countPUSH = ((Integer)this.functionPopNumberStack.pop()).intValue();
			    
			    Node newNode = new Node(createTemp(), ((Integer)this.functionMap.get(this.functionRecord)).intValue());
			    this.output.add("POP " + newNode.content);
			    return newNode;
			  }
			  
			  public Node visitExpression_list(MicroParser.Expression_listContext ctx)
			  {
			  	ParseTree temp = (ParseTree)ctx.expression();
			    Node exprNode = (Node)visit(temp);
			    

			    this.Stack_of_Functions.push(exprNode.content);
			    this.countPUSH += 1;
			    temp = (ParseTree)ctx.expression_list_repeat();
			    if (!"".equals(temp.getText())) 
			    {
			      	visit(temp);
			    }
			    return null;
			  }
			  
			  public Node visitExpression_list_repeat(MicroParser.Expression_list_repeatContext ctx)
			  {
			  	ParseTree temp = (ParseTree)ctx.expression();
			    Node exprNode = (Node)visit(temp);
			    
			    this.Stack_of_Functions.push(exprNode.content);
			    this.countPUSH += 1;
			    temp = (ParseTree)ctx.expression_list_repeat();
			    if (!"".equals(temp.getText())) 
			    {
			      	visit(temp);
			    }
			    return null;
			  }
			  
			  public Node visitRe_turn(MicroParser.Re_turnContext ctx)
			  {
			    Node exprNode = (Node)visit(ctx.expression());
			    Node tempNode = new Node(createTemp(), exprNode.type);
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
			  
			  public Node visitParameter_declaration(MicroParser.Parameter_declarationContext ctx)
			  {
			    Node newNode = null;
			    if (ctx.variable_type().getText().equalsIgnoreCase("INT")) 
			    {
			      newNode = new Node(ctx.name().getText(), 1);
			    } 
			    else 
			    {
			      newNode = new Node(ctx.name().getText(), 2);
			    }
			    return newNode;
			  }
			  
			  public Node visitExpression(MicroParser.ExpressionContext ctx)
			  {
			    if (!"".equals(ctx.pre_expression().getText()))
			    {
			      ArrayList<Node> exprList = new ArrayList<>();
			      this.exprStack.push(exprList);
			      Node exprNode = (Node)visit(ctx.pre_expression());
			      Node factorNode = (Node)visit(ctx.factor());
			      ((ArrayList)this.exprStack.peek()).add(factorNode);
			      Node resolveNode = resolve((ArrayList)this.exprStack.pop());
			      
			      return resolveNode;
			    }
			    Node factorNode = (Node)visit(ctx.factor());
			    return factorNode;
			  }
			  
			  public Node visitPre_expression(MicroParser.Pre_expressionContext ctx)
			  {
			    if (!"".equals(ctx.pre_expression().getText())) 
			    {
			      visit(ctx.pre_expression());
			    }
			    Node opNode = new Node(ctx.addition_operation().getText(), 3);
			    Node factorNode = (Node)visit(ctx.factor());
			    ((ArrayList)this.exprStack.peek()).add(factorNode);
			    ((ArrayList)this.exprStack.peek()).add(opNode);
			    
			    return null;
			  }
			  
			  public Node visitFactor(MicroParser.FactorContext ctx)
			  {
			    if (!"".equals(ctx.pre_factor().getText()))
			    {
			      ArrayList<Node> factorList = new ArrayList();
			      this.factorStack.push(factorList);
			      Node exprNode = (Node)visit(ctx.pre_factor());
			      Node postfixNode = (Node)visit(ctx.post_expression());
			      ((ArrayList)this.factorStack.peek()).add(postfixNode);
			      Node resolveNode = resolve((ArrayList)this.factorStack.pop());
			      
			      return resolveNode;
			    }
			    return (Node)visit(ctx.post_expression());
			  }
			  
			  public Node visitPre_factor(MicroParser.Pre_factorContext ctx)
			  {
			    if (!"".equals(ctx.pre_factor().getText())) 
			    {
			      visit(ctx.pre_factor());
			    }
			    Node opNode = new Node(ctx.multiplication_operation().getText(), 3);
			    Node postfixNode = (Node)visit(ctx.post_expression());
			    ((ArrayList)this.factorStack.peek()).add(postfixNode);
			    ((ArrayList)this.factorStack.peek()).add(opNode);
			    
			    return null;
			  }
			  
			  public Node visitWrite(MicroParser.WriteContext ctx)
			  {
			    String[] nameArray = ctx.name_list().getText().split(",");
			    for (int i = 0; i < nameArray.length; i++)
			    {
			      Node newNode = findNameNode(nameArray[i], this.functionRecord);
			      if (newNode.type == 1) 
			      {
			        this.output.add("WRITEI " + newNode.content);
			      } 
			      else if (newNode.type == 5) 
			      {
			        this.output.add("WRITES " + newNode.content);
			      }
			      else 
			      {
			        this.output.add("WRITEF " + newNode.content);
			      }
			    }
			    return null;
			  }
			  
			  public Node visitRead(MicroParser.ReadContext ctx)
			  {
			    String[] nameArray = ctx.name_list().getText().split(",");
			    for (int i = 0; i < nameArray.length; i++)
			    {
			      Node newNode = findNameNode(nameArray[i], this.functionRecord);
			      if (newNode.type == 1)
			      {
			        this.output.add("READI " + newNode.content);
			      } 
			      else 
			      {
			        this.output.add("READF " + newNode.content);
			      }
			    }
			    return null;
			  }
			  
			  public Node visitAssignment_frame(MicroParser.Assignment_frameContext ctx)
			  {
			    Node exprNode = (Node)visit(ctx.expression());
			    Node newNode = findNameNode(ctx.name().getText(), this.functionRecord);
			    if (newNode.type == 1) 
			    {
			      this.output.add("STOREI " + exprNode.content + " " + newNode.content);
			    } 
			    else 
			    {
			      this.output.add("STOREF " + exprNode.content + " " + newNode.content);
			    }
			    return null;
			  }
			  
			  public Node visitWhile_statement(MicroParser.While_statementContext ctx)
			  {
			    String newLabel = createLabel();
			    this.output.add("LABEL " + newLabel);
			    String newLabel2 = createLabel();
			    this.Stack_of_Labels.add(newLabel2);
			    Node comp = (Node)visit(ctx.condition());
			    
			    return null;
			  }
			  
			  public Node visitIf_statement(MicroParser.If_statementContext ctx)
			  {
			    if (!"".equals(ctx.else_portion().getText()))
			    {
			      Node comp = (Node)visit(ctx.condition());
			      if (comp.content.equalsIgnoreCase("TRUE"))
			      {
			        visit(ctx.statement_list());
			      }
			      else if (comp.content.equalsIgnoreCase("FALSE"))
			      {
			        String newLabel2 = createLabel();
			        this.Stack_of_Labels.push(newLabel2);
			        visit(ctx.else_portion());
			        this.output.add("LABEL " + newLabel2);
			      }
			      else
			      {
			        String newLabel = createLabel();
			        this.output.add(comp.content + " " + newLabel);
			        visit(ctx.statement_list());
			        String newLabel2 = createLabel();
			        this.Stack_of_Labels.push(newLabel2);
			        this.output.add("JUMP " + newLabel2);
			        this.output.add("LABEL " + newLabel);
			        visit(ctx.else_portion());
			        this.output.add("LABEL " + newLabel2);
			      }
			    }
			    else
			    {
			      Node comp = (Node)visit(ctx.condition());
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
			  
			  public Node visitElse_portion(MicroParser.Else_portionContext ctx)
			  {
			    visit(ctx.statement_list());
			    return null;
			  }
			  
			  public Node visitCondition(MicroParser.ConditionContext ctx)
			  {
			    Node op1 = (Node)visit(ctx.expression(0));
			    
			    visit(ctx.comparison_operator());
			    Node op2 = (Node)visit(ctx.expression(1));
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
					  System.out.println("ERROR");
					  return null;
				  }
			  }
			  
			  public Node resolveComp(Node op1, Node op2, String op)
			  {
			    if ((op1.type == 1) && (op2.type == 1))
			    {
			    	switch(op)
			    	{
			    	case("<"):
			    		return new Node("GEI " + op1.content + " " + op2.content, 4);
			    	case(">"):
			    		return new Node("LEI " + op1.content + " " + op2.content, 4);
			    	case("="):
			    		return new Node("NEI " + op1.content + " " + op2.content, 4);
			      	case("!="):
			      		return new Node("EQI " + op1.content + " " + op2.content, 4);
			    	case("<="):
			    		return new Node("GTI " + op1.content + " " + op2.content, 4);
			      	case(">="):
			      		return new Node("LTI " + op1.content + " " + op2.content, 4);
			      	default:
			      		System.out.println("ERROR");
			      		return null;
			    	}
			    }
			    else
			    {
			    	switch(op)
			    	{
			    	case("<"):
			    		return new Node("GEF " + op1.content + " " + op2.content, 4);
			    	case(">"):
			    		return new Node("LEF " + op1.content + " " + op2.content, 4);
			    	case("="):
			    		return new Node("NEF " + op1.content + " " + op2.content, 4);
			      	case("!="):
			      		return new Node("EQF " + op1.content + " " + op2.content, 4);
			    	case("<="):
			    		return new Node("GTF " + op1.content + " " + op2.content, 4);
			      	case(">="):
			      		return new Node("LTF " + op1.content + " " + op2.content, 4);
			      	default:
			      		System.out.println("ERROR");
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
			    if (this.tempIndex > this.finalTempIndex)
			    {
			      this.finalTempIndex = this.tempIndex;
			    }
			    ((ArrayList)this.tempMap.get(this.functionRecord)).add("$T" + Integer.toString(this.tempIndex));
			    return "$T" + Integer.toString(this.tempIndex);
			  }
			  
			  public Node resolve(ArrayList<Node> input)
			  {
			    while (input.size() >= 3)
			    {
			      Node op1 = (Node)input.get(0);
			      Node op = (Node)input.get(1);
			      Node op2 = (Node)input.get(2);
			      
			      Node newNode = new Node(createTemp(), op1.type);
			      if (op.content.equalsIgnoreCase("+"))
			      {
			        if (op1.type == 1)
			        {
			          String output = "ADDI " + op1.content + " " + op2.content + " " + newNode.content;
			          this.output.add(output);
			        }
			        else
			        {
			          String output = "ADDF " + op1.content + " " + op2.content + " " + newNode.content;
			          this.output.add(output);
			        }
			      }
			      else if (op.content.equalsIgnoreCase("-"))
			      {
			        if (op1.type == 1)
			        {
			          String output = "SUBI " + op1.content + " " + op2.content + " " + newNode.content;
			          this.output.add(output);
			        }
			        else
			        {
			          String output = "SUBF " + op1.content + " " + op2.content + " " + newNode.content;
			          this.output.add(output);
			        }
			      }
			      else if (op.content.equalsIgnoreCase("*"))
			      {
			        if (op1.type == 1)
			        {
			          String output = "MULTI " + op1.content + " " + op2.content + " " + newNode.content;
			          this.output.add(output);
			        }
			        else
			        {
			          String output = "MULTF " + op1.content + " " + op2.content + " " + newNode.content;
			          this.output.add(output);
			        }
			      }
			      else if (op1.type == 1)
			      {
			        String output = "DIVI " + op1.content + " " + op2.content + " " + newNode.content;
			        this.output.add(output);
			      }
			      else
			      {
			        String output = "DIVF " + op1.content + " " + op2.content + " " + newNode.content;
			        this.output.add(output);
			      }
			      input.remove(0);
			      input.remove(0);
			      input.remove(0);
			      
			      input.add(0, newNode);
			    }
			    Node returnValue = (Node)input.get(0);
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