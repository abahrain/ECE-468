import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.LinkedHashMap;
import java.util.Set;

public class MyListener extends MicroBaseListener
{
	class Scope
	{
		String title = null;
		LinkedHashMap<String, String> information = new LinkedHashMap();
    
		public Scope()
		{
		}
    
		public Scope(String title)
		{
			this.title = title;
		}
    
		@Override
		public String toString()
		{
			StringBuilder string = new StringBuilder();
			Set<String> indexs = information.keySet();
			if(indexs.size() > 0)
			{
				string.append(title);
				string.append("-");
				for(String index : indexs)
				{
					string.append(index);
					string.append(": ");
					string.append(information.get(index));
					string.append(", ");
				}
			}
			return string.toString();
		}
	}
  
	ParseTreeProperty<Scope> scopes;
    MakeSymbolTable symbolTable;
	int register_count;
	int label_count;
	
	public MyListener(MakeSymbolTable symbolTable)
	{
		this.symbolTable = symbolTable;
		this.scopes = new ParseTreeProperty<Scope>();
		this.register_count = 0;
		this.label_count = 0;
	}
	
	private String generateLabel()
	{
		label_count++;
		return currentLabel();
	}
	
	private String currentLabel()
	{
		return new String("label"+label_count);
	}
	
	private String getNewRegister(String type)
	{
		register_count++;
		String register_name = new String("$T" + register_count);
		symbolTable.addSingleVariable(register_name, type);
		return register_name;
	}
	 
   private String getOldRegister()
   {
    return new String("$T" + register_count);
   }
   
   private String whichOperation(String operation)
   {
		if(operation.equals(">"))
		{
			return "GT";
		}
		else if(operation.equals("<"))
		{
			return "LT";
		}
		else if(operation.equals(">="))
		{
			return "GE";
		}
		else if(operation.equals("<="))
		{
			return "LE";
		}
		else if(operation.equals("!="))
		{
			return "NE";
		}
		else if(operation.equals("="))
		{
			return "EQ";
		}
		else
		{
			return "ERROR";
		}
	}
   
   private String whichOperator(String operator, String type)
   {
		if(operator.equals("+"))
		{
			if(type.equals("INT"))
			{
				return "ADDI";
			}
			else if(type.equals("FLOAT"))
			{
				return "ADDF";
			}
		}
		else if(operator.equals("-"))
		{
			if(type.equals("INT"))
			{
				return "SUBI";
			}
			else if(type.equals("FLOAT"))
			{
				return "SUBF";
			}
		}
		else if(operator.equals("*"))
		{
			if(type.equals("INT"))
			{
				return "MULI";
			}
			else if(type.equals("FLOAT"))
			{
				return "MULF";
			}
		}
		else if(operator.equals("/"))
		{
			if(type.equals("INT"))
			{
				return "DIVI";
			}
			else if(type.equals("FLOAT"))
			{
				return "DIVF";
			}
		}
		return "ERROR";
	}
   
   public void createNode(ParserRuleContext ctx, String index, String value)
   {
    scopes.get(ctx).information.put(index,value);
   }
   
   public void returnChild(ParserRuleContext ctx, String string)
   {
    ParserRuleContext parent = ctx.getParent();
    if(parent != null)
    {
     Scope parentScope = scopes.get(ctx.getParent());
     if(string != null)
     {
      parentScope.title = parentScope.title + " " + string;
     }
    }
   }
	//come on... do stuff	
	@Override
	public void exitExpression(MicroParser.ExpressionContext ctx) 
	{ 
		Scope node_properties = scopes.get(ctx);
		if(scopes.get(ctx).information.containsKey("register"))
		{
			createNode(ctx,"register",scopes.get(ctx).information.get("register"));
		}
		else
		{
			createNode(ctx,"register",scopes.get(ctx).information.get("primary"));
		}
	}
	@Override
	public void enterAssignment_frame(MicroParser.Assignment_frameContext ctx) 
	{ 
		scopes.get(ctx).information.put("Left_value", null);
	}
	@Override
	public void exitAssignment_frame(MicroParser.Assignment_frameContext ctx) 
	{ 
		String left = scopes.get(ctx).information.get("Left_value");
		String store_Operation = "ERROR";
		String left_Type = symbolTable.lookup(left).type;
		if (left_Type.equals("INT"))
		{
			store_Operation = "STOREI";
		}
		else if(left_Type.equals("FLOAT"))
		{
			store_Operation = "STOREF";
		}
    
		System.out.println(store_Operation + " " + scopes.get(ctx).information.get("register") + " " + left);
	}
	@Override
	public void exitComparison_operator(MicroParser.Comparison_operatorContext ctx) 
	{ 
		createNode(ctx,"comparison_operator", ctx.getText());
	}
	@Override
	public void enterIf_statement(MicroParser.If_statementContext ctx) 
	{ 
		symbolTable.openScope();
	}
	@Override
	public void exitIf_statement(MicroParser.If_statementContext ctx) 
	{ 
		symbolTable.closeScope();
	}
	@Override
	public void enterWhile_statement(MicroParser.While_statementContext ctx) 
	{ 
		symbolTable.openScope();
	}
	@Override
	public void exitWhile_statement(MicroParser.While_statementContext ctx) 
	{ 
		symbolTable.closeScope();
	}
	@Override
	public void exitAddition_operation(MicroParser.Addition_operationContext ctx) 
	{ 
		createNode(ctx,"addition_operation",ctx.getText());
	}
	@Override
	public void exitPrimary(MicroParser.PrimaryContext ctx) 
	{ 
		createNode(ctx,"primary",ctx.getText());
	}
	@Override
	public void enterName(MicroParser.NameContext ctx) 
	{ 
		Scope parentScope = scopes.get(ctx.getParent());
		if(parentScope.information.containsKey("Left_value"))
		{
			createNode(ctx.getParent(), "Left_value", ctx.getText());
		}
	}
	@Override
	public void enterFunction_declaration(MicroParser.Function_declarationContext ctx) 
	{ 
		symbolTable.enterScope(); 
	}
	@Override
	public void exitFunction_declaration(MicroParser.Function_declarationContext ctx) 
	{ 
		symbolTable.exitScope();
	}
	@Override
	public void exitCondition(MicroParser.ConditionContext ctx) 
	{ 
		System.out.println(whichOperation(scopes.get(ctx.getChild(1)).information.get("compop")) + " " + scopes.get(ctx.getChild(0)).information.get("register") + " " + scopes.get(ctx.getChild(2)).information.get("register") + " " + generateLabel());
	}
	@Override
	public void exitFactor(MicroParser.FactorContext ctx) 
	{ 
		Scope expression = scopes.get(ctx.getParent().getChild(0));
		if(!expression.toString().isEmpty())
		{
			String type = symbolTable.lookup(scopes.get(ctx).information.get("primary")).type;
			String temp = getNewRegister(type);
			String operation = whichOperator(expression.information.get("addition_operation"), type);
     
			System.out.println(operation + " " + expression.information.get("primary") + " " + scopes.get(ctx).information.get("primary") + " " + temp);
			createNode(ctx,"primary", temp);
		}
	}
	@Override
	public void exitPre_expression(MicroParser.Pre_expressionContext ctx) 
	{ 
		Scope parent = scopes.get(ctx.getParent());
	}
	@Override 
	public void exitEveryRule(ParserRuleContext ctx)
	{ 
		ParserRuleContext parent = ctx.getParent();
		if(parent != null)
		{
			Scope parentNode = scopes.get(ctx.getParent());
			parentNode.information.putAll(scopes.get(ctx).information);
		}
	}
	/* @Override
	public void exitAssignment_frame(MicroParser.Assignment_frameContext ctx)
	{ 
		System.out.println("STOREI "+ctx.expression().getText()+" $T"+register_count);
		System.out.println("STOREI $T"+register_count+" "+ctx.name().getText());
		register_count += 1;
	}	
	@Override
	public void exitIf_statement(MicroParser.If_statementContext ctx)
	{ 
		System.out.println("BLOCK " + Integer.toString(register_count));
		register_count += 1;
	}	
	@Override
	public void enterWhile_statement(MicroParser.While_statementContext ctx)
	{ 
		System.out.println("BLOCK " + Integer.toString(register_count));
		register_count += 1;
	}	
	@Override
	public void exitVariable_declaration_list(MicroParser.Variable_declaration_listContext ctx)
	{ 
		String temp = ctx.name_list().getText();
		String[] array = temp.split(",");
		if (ctx.variable_type().getText().equals("INT"))
		{
			for (String var : array)
			{
				System.out.println(var + " INT");
			}
		}
		else
		{
			for (String var : array)
			{
				System.out.println(var + " FLOAT");
			}
		}
	}	
	@Override
	public void enterParameter_declaration(MicroParser.Parameter_declarationContext ctx)
	{ 
		if (ctx.variable_type().getText().equals("INT"))
		{
			System.out.println(ctx.name().getText() + " INT");
		}
		else
		{
			System.out.println(ctx.name().getText() + " FLOAT");
		}
	}
	@Override
	public void enterFunction_declaration(MicroParser.Function_declarationContext ctx)
	{ 
		System.out.println(ctx.name().getText());
		if (ctx.any_type().getText().equalsIgnoreCase("INT"))
		{
			System.out.println(ctx.name().getText() + " INT");
		}
		else if(ctx.any_type().getText().equalsIgnoreCase("FLOAT"))
		{
			System.out.println(ctx.name().getText() + " FLOAT");
		}
		else
		{
		}
	}		
	@Override
	public void enterString_declaration_list(MicroParser.String_declaration_listContext ctx)
	{ 
		System.out.println(ctx.string().getText()+" "+ctx.name().getText() + " INT");
	}	
	@Override
	public void enterElse_portion(MicroParser.Else_portionContext ctx)
	{ 
		if (!ctx.getText().equals(""))
		{
			System.out.println("BLOCK " + Integer.toString(register_count));
			register_count += 1;
		}
	} */
}