import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import org.antlr.v4.runtime.tree.*;

public class MyVisitor extends MicroBaseVisitor<NodeStructure>
{
	
	public ArrayList<String> output = new ArrayList<>();
	public ArrayList<String> Stack_of_Pushes = new ArrayList<>();
	private Stack<String> Stack_of_Labels = new Stack<>();
	private Stack<String> Stack_of_Functions = new Stack<>();
	private Stack<Integer> pop_Number_on_FunctionStack = new Stack<>();
	private Stack<ArrayList<NodeStructure>> factorStack = new Stack<>();
	private Stack<ArrayList<NodeStructure>> expressionStack = new Stack<>();
	protected Map<String, Map<String, NodeStructure>> tableMap = new LinkedHashMap<>();
	protected Map<String, Integer> functionMap = new LinkedHashMap<>();
	protected Map<String, ArrayList<String>> temporaryMap = new LinkedHashMap<>();
	private int temporaryIndex = 0;
	public int finalTempIndex = 0;
	private int varIndex = 0;
	private int paramIndex = 0;
	private int labelIndex = 0;
	private int countPUSH = 0;
	private String functionRecord = "GLOBAL";
	
	public MyVisitor(SymbolTable table, Map<String, Integer> functionMap)
	{
		this.functionMap = functionMap;
		for(Boresight scope : table.scopeBlocks.subList(0,table.scopeBlocks.size()))
		{
			Map<String, NodeStructure> varMap = new LinkedHashMap<>();
			if(scope.type.equals("GLOBAL"))
			{
				for (String key : scope.symbolMap.keySet()) 
				{
			          if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT)
			          {
			            varMap.put(key, new NodeStructure(key, 1));
			          } 
			          else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) 
			          {
			            varMap.put(key, new NodeStructure(key, 2));
			          } 
			          else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) 
			          {
			            varMap.put(key, new NodeStructure(key, 5));
			          } 
			        }
			      } else {
			        for (String key : scope.symbolMap.keySet())
			        {
			          if (((BuildNode)scope.symbolMap.get(key)).quantifying.query)
			          {
			            if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) 
			            {
			              varMap.put(key, new NodeStructure(createVar(true), 1));
			            } 
			            else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) 
			            {
			              varMap.put(key, new NodeStructure(createVar(true), 2));
			            } 
			            else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING)
			            {
			              varMap.put(key, new NodeStructure(key, 5));
			            } 
			          }
			          else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) 
			          {
			            varMap.put(key, new NodeStructure(createVar(false), 1));
			          } 
			          else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) 
			          {
			            varMap.put(key, new NodeStructure(createVar(false), 2));
			          }
			          else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING)
			          {
			            varMap.put(key, new NodeStructure(key, 5));
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
			    if (isParameter == true)
			    {
			      this.paramIndex += 1;
			      return "$P" + Integer.toString(this.paramIndex);
			    }
			    this.varIndex += 1;
			    return "$L" + Integer.toString(this.varIndex);
			  }
			  
			  public NodeStructure findNameNode(String name, String scopeName)
			  {
			    if ((this.tableMap.get(scopeName)).get(name) == null)
			    {
			      if ((this.tableMap.get("GLOBAL")).get(name) == null)
			      {
			        return null;
			      }
			      return (NodeStructure)(this.tableMap.get("GLOBAL")).get(name);
			    }
			    return (NodeStructure)(this.tableMap.get(scopeName)).get(name);
			  }
			  
			  public NodeStructure visitPrimary(MicroParser.PrimaryContext ctx)
			  {
			    if (ctx.expression() != null) 
			    {
			      return (NodeStructure)visit(ctx.expression());
			    }
			    if (ctx.name() != null) 
			    {
			      return findNameNode(ctx.name().getText(), this.functionRecord);
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
			    ArrayList<String> newTempList = new ArrayList<>();
			    
			    this.output.add("LABEL " + ctx.name().getText());
			    this.functionRecord = ctx.name().getText();
			    this.temporaryMap.put(this.functionRecord, newTempList);
			    this.output.add("LINK ");
			    visitChildren(ctx);
			    this.temporaryIndex = 0;
			    if (ctx.any_type().getText().equals("VOID"))
			    {
			      this.output.add("RET");
			    }
			    return null;
			  }
			  
			  public NodeStructure visitExpression_call(MicroParser.Expression_callContext ctx)
			  {
			    this.pop_Number_on_FunctionStack.push(Integer.valueOf(this.countPUSH));
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
			    this.countPUSH = ((Integer)this.pop_Number_on_FunctionStack.pop()).intValue();
			    
			    NodeStructure newNode = new NodeStructure(createTemp(), ((Integer)this.functionMap.get(this.functionRecord)).intValue());
			    this.output.add("POP " + newNode.content);
			    return newNode;
			  }
			  
			  public NodeStructure visitExpression_list(MicroParser.Expression_listContext ctx)
			  {
			  	ParseTree temporary_tree = (ParseTree)ctx.expression();
			    NodeStructure expressionNode = (NodeStructure)visit(temporary_tree);
			    

			    this.Stack_of_Functions.push(expressionNode.content);
			    this.countPUSH += 1;
			    temporary_tree = (ParseTree)ctx.expression_list_repeat();
			    if (!temporary_tree.getText().isEmpty()) 
			    {
			      	visit(temporary_tree);
			    }
			    return null;
			  }
			  
			  public NodeStructure visitExpression_list_repeat(MicroParser.Expression_list_repeatContext ctx)
			  {
			  	ParseTree temporary_tree = (ParseTree)ctx.expression();
			    NodeStructure expressionNode = (NodeStructure)visit(temporary_tree);
			    
			    this.Stack_of_Functions.push(expressionNode.content);
			    this.countPUSH += 1;
			    temporary_tree = (ParseTree)ctx.expression_list_repeat();
			    if (!temporary_tree.getText().isEmpty()) 
			    {
			      	visit(temporary_tree);
			    }
			    return null;
			  }
			  
			  public NodeStructure visitRe_turn(MicroParser.Re_turnContext ctx)
			  {
			    NodeStructure expressionNode = (NodeStructure)visit(ctx.expression());
			    NodeStructure temporary_Node = new NodeStructure(createTemp(), expressionNode.type);
			    if (expressionNode.type == 1)
			    {
			      this.output.add("STOREI " + expressionNode + " " + temporary_Node);
			      this.output.add("STOREI " + temporary_Node + " $R");
			    }
			    else
			    {
			      this.output.add("STOREF " + expressionNode + " " + temporary_Node);
			      this.output.add("STOREF " + temporary_Node + " $R");
			    }
			    this.output.add("RET");
			    return null;
			  }
			  
			  public NodeStructure visitParameter_declaration(MicroParser.Parameter_declarationContext ctx)
			  {
			    NodeStructure newNode = null;
			    if (ctx.variable_type().getText().equals("INT")) 
			    {
			      newNode = new NodeStructure(ctx.name().getText(), 1);
			    } 
			    else 
			    {
			      newNode = new NodeStructure(ctx.name().getText(), 2);
			    }
			    return newNode;
			  }
			  
			  public NodeStructure visitExpression(MicroParser.ExpressionContext ctx)
			  {
			    if (!ctx.pre_expression().getText().isEmpty())
			    {
			      ArrayList<NodeStructure> exprList = new ArrayList<>();
			      this.expressionStack.push(exprList);
			      visit(ctx.pre_expression());
			      NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
			      ((ArrayList<NodeStructure>)this.expressionStack.peek()).add(factorNode);
			      NodeStructure resolveNode = resolve(this.expressionStack.pop());
			      
			      return resolveNode;
			    }
			    NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
			    return factorNode;
			  }
			  
			  public NodeStructure visitPre_expression(MicroParser.Pre_expressionContext ctx)
			  {
			    if (!ctx.pre_expression().getText().isEmpty()) 
			    {
			      visit(ctx.pre_expression());
			    }
			    NodeStructure operation_Node = new NodeStructure(ctx.addition_operation().getText(), 3);
			    NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
			    ((ArrayList<NodeStructure>)this.expressionStack.peek()).add(factorNode);
			    ((ArrayList<NodeStructure>)this.expressionStack.peek()).add(operation_Node);
			    
			    return null;
			  }
			  
			  public NodeStructure visitFactor(MicroParser.FactorContext ctx)
			  {
			    if (!ctx.pre_factor().getText().isEmpty())
			    {
			      ArrayList<NodeStructure> factorList = new ArrayList<>();
			      this.factorStack.push(factorList);
			      visit(ctx.pre_factor());
			      NodeStructure post_expressionNode = (NodeStructure)visit(ctx.post_expression());
			      ((ArrayList<NodeStructure>)this.factorStack.peek()).add(post_expressionNode);
			      NodeStructure resolveNode = resolve(this.factorStack.pop());
			      
			      return resolveNode;
			    }
			    return (NodeStructure)visit(ctx.post_expression());
			  }
			  
			  public NodeStructure visitPre_factor(MicroParser.Pre_factorContext ctx)
			  {
			    if (!ctx.pre_factor().getText().isEmpty()) 
			    {
			      visit(ctx.pre_factor());
			    }
			    NodeStructure operation_Node = new NodeStructure(ctx.multiplication_operation().getText(), 3);
			    NodeStructure postfixNode = (NodeStructure)visit(ctx.post_expression());
			    ((ArrayList<NodeStructure>)this.factorStack.peek()).add(postfixNode);
			    ((ArrayList<NodeStructure>)this.factorStack.peek()).add(operation_Node);
			    
			    return null;
			  }
			  
			  public NodeStructure visitWrite(MicroParser.WriteContext ctx)
			  {
			    String[] nameArray = ctx.name_list().getText().split(",");
			    for (int i = 0; i < nameArray.length; i++)
			    {
			      NodeStructure newNode = findNameNode(nameArray[i], this.functionRecord);
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
			  
			  public NodeStructure visitRead(MicroParser.ReadContext ctx)
			  {
			    String[] nameArray = ctx.name_list().getText().split(",");
			    for (int i = 0; i < nameArray.length; i++)
			    {
			      NodeStructure newNode = findNameNode(nameArray[i], this.functionRecord);
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
			  
			  public NodeStructure visitAssignment_frame(MicroParser.Assignment_frameContext ctx)
			  {
			    NodeStructure expressionNode = (NodeStructure)visit(ctx.expression());
			    NodeStructure newNode = findNameNode(ctx.name().getText(), this.functionRecord);
			    if (newNode.type == 1) 
			    {
			      this.output.add("STOREI " + expressionNode.content + " " + newNode.content);
			    } 
			    else 
			    {
			      this.output.add("STOREF " + expressionNode.content + " " + newNode.content);
			    }
			    return null;
			  }
			  
			  public NodeStructure visitWhile_statement(MicroParser.While_statementContext ctx)
			  {
			    String newLabel = createLabel();
			    this.output.add("LABEL " + newLabel);
			    String newLabel2 = createLabel();
			    this.Stack_of_Labels.add(newLabel2);
			    
			    return null;
			  }
			  
			  public NodeStructure visitIf_statement(MicroParser.If_statementContext ctx)
			  {
			    if (ctx.else_portion().getText().isEmpty())
			    {
			      NodeStructure comparison = (NodeStructure)visit(ctx.condition());
			      if (comparison.content.equals("TRUE"))
			      {
			        visit(ctx.statement_list());
			      }
			      else if (!comparison.content.equals("FALSE"))
			      {
			        String newLabel2 = createLabel();
			        this.output.add(comparison.content + " " + newLabel2);
			        visit(ctx.statement_list());
			        this.output.add("LABEL " + newLabel2);
			      }
			    }
			    else
			    {
			    	NodeStructure comparison = (NodeStructure)visit(ctx.condition());
				      if (comparison.content.equals("TRUE"))
				      {
				        visit(ctx.statement_list());
				      }
				      else if (comparison.content.equals("FALSE"))
				      {
				        String newLabel2 = createLabel();
				        this.Stack_of_Labels.push(newLabel2);
				        visit(ctx.else_portion());
				        this.output.add("LABEL " + newLabel2);
				      }
				      else
				      {
				        String newLabel = createLabel();
				        this.output.add(comparison.content + " " + newLabel);
				        visit(ctx.statement_list());
				        String newLabel2 = createLabel();
				        this.Stack_of_Labels.push(newLabel2);
				        this.output.add("JUMP " + newLabel2);
				        this.output.add("LABEL " + newLabel);
				        visit(ctx.else_portion());
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
			    NodeStructure left_variable = (NodeStructure)visit(ctx.expression(0));
			    
			    visit(ctx.comparison_operator());
			    NodeStructure right_operation = (NodeStructure)visit(ctx.expression(1));
			    return determineComparison(left_variable, right_operation, ctx.comparison_operator().getText());
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
			  
			  public NodeStructure determineComparison(NodeStructure left_variable, NodeStructure right_operation, String operation)
			  {
			    if ((left_variable.type == 1) && (right_operation.type == 1))
			    {
			    	switch(operation)
			    	{
			    		case("<"):
			    			return new NodeStructure("GEI " + left_variable.content + " " + right_operation.content, 4);
			    		case(">"):
			    			return new NodeStructure("LEI " + left_variable.content + " " + right_operation.content, 4);
			    		case("="):
			    			return new NodeStructure("NEI " + left_variable.content + " " + right_operation.content, 4);
			    		case("!="):
			    			return new NodeStructure("EQI " + left_variable.content + " " + right_operation.content, 4);
			    		case("<="):
			    			return new NodeStructure("GTI " + left_variable.content + " " + right_operation.content, 4);
			    		case(">="):
			    			return new NodeStructure("LTI " + left_variable.content + " " + right_operation.content, 4);
			    		default:
			    			return null;
			    	}
			    }
			    else
			    {
			    	switch(operation)
			    	{
			    	case("<"):
			    		return new NodeStructure("GEF " + left_variable.content + " " + right_operation.content, 4);
			    	case(">"):
			    		return new NodeStructure("LEF " + left_variable.content + " " + right_operation.content, 4);
			    	case("="):
			    		return new NodeStructure("NEF " + left_variable.content + " " + right_operation.content, 4);
			      	case("!="):
			      		return new NodeStructure("EQF " + left_variable.content + " " + right_operation.content, 4);
			    	case("<="):
			    		return new NodeStructure("GTF " + left_variable.content + " " + right_operation.content, 4);
			      	case(">="):
			      		return new NodeStructure("LTF " + left_variable.content + " " + right_operation.content, 4);
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
			    this.temporaryIndex += 1;
			    if (this.temporaryIndex > this.finalTempIndex)
			    {
			      this.finalTempIndex = this.temporaryIndex;
			    }
			    ((ArrayList<String>)this.temporaryMap.get(this.functionRecord)).add("$T" + Integer.toString(this.temporaryIndex));
			    return "$T" + Integer.toString(this.temporaryIndex);
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
			      result = result + (String)this.output.get(i)+"\n";
			    }
			    return result;
			  }
			}