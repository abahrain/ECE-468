import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.*;

import java.util.LinkedHashMap;
import java.util.Set;

public class MyListener extends MicroBaseListener
{
	
	/*class Scope
	{
		String title = null;
		LinkedHashMap<String, String> information = new LinkedHashMap<String,String>();
    
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
  
	ParseTreeProperty<Scope> scopes = new ParseTreeProperty<>();
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
		
		if(node_properties.information.containsKey("register"))
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
		register_count++;
		//System.out.println(ctx.expression().pre_expression().getText());
		if(ctx.expression().pre_expression().getText().isEmpty())
		{
			System.out.println(store_Operation + " " + scopes.get(ctx).information.get("register") + " $T"+register_count);
		}
		System.out.println(store_Operation + " $T"+register_count + " " + left);
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
		createNode(ctx,"operation",ctx.getText());
	}
	@Override
	public void exitMultiplication_operation(MicroParser.Multiplication_operationContext ctx)
	{
		createNode(ctx,"operation",ctx.getText());
	}
	@Override
	public void exitPrimary(MicroParser.PrimaryContext ctx) 
	{ 
		createNode(ctx,"primary",ctx.getText());
		//System.out.println("primary: "+ctx.getText());
	}
	@Override
	public void exitName(MicroParser.NameContext ctx) 
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
		System.out.println(whichOperation(scopes.get(ctx.getChild(1)).information.get("comparison_operator")) + " " + scopes.get(ctx.getChild(0)).information.get("register") + " " + scopes.get(ctx.getChild(2)).information.get("register") + " " + generateLabel());
	}
	@Override
	public void exitFactor(MicroParser.FactorContext ctx) 
	{
		if(!ctx.pre_factor().getText().isEmpty())
		{
			String left = ctx.pre_factor().post_expression().getText();
			String right = ctx.post_expression().getText();
			String mult_Operation = "ERROR";
			String left_Type = symbolTable.lookup(left).type;
			String right_Type = symbolTable.lookup(right).type;
			if (left_Type.equals("INT") && right_Type.equals("INT"))
			{
				mult_Operation = "MULTI";
			}
			else if(left_Type.equals("FLOAT") || right_Type.equals("FLOAT"))
			{
				mult_Operation = "MULTF";
			}
			MicroParser.Pre_factorContext pre_factor = ctx.pre_factor();
			while(!pre_factor.getText().isEmpty())
			{
				register_count++;
				System.out.println(mult_Operation+" "+left+" "+right+" $T"+register_count);
				pre_factor = pre_factor.pre_factor();
			}
		}
	}
	@Override
	public void exitPre_expression(MicroParser.Pre_expressionContext ctx) 
	{ 
		//Scope parent = scopes.get(ctx.getParent());
		//System.out.println("Pre-e: "+ctx.getText());
		Scope expression = scopes.get(ctx.getParent().getChild(0));
		//System.out.println(expression.toString());
		String operate = "operation";
		if(!expression.toString().isEmpty())
		{
			String type = symbolTable.lookup(scopes.get(ctx).information.get("primary")).type;
			String temp = getNewRegister(type);
			String operation = whichOperator(expression.information.get(operate), type);
			
			System.out.println(operation + " " + expression.information.get("primary") + " " + scopes.get(ctx).information.get("primary") + " " + temp);
			createNode(ctx,"primary", temp);
		}
	}
	@Override 
	public void exitRe_turn(MicroParser.Re_turnContext ctx) 
	{ 
		System.out.println("RET: "+ctx.getText());
		createNode(ctx,"return",ctx.getText());
	}
	@Override 
	public void exitWrite(MicroParser.WriteContext ctx)
	{
		String current_name = ctx.name_list().name().getText();
		String write_Operation = "ERROR";
		String current_name_Type = symbolTable.lookup(current_name).type;
		if (current_name_Type.equals("INT"))
		{
			write_Operation = "WRITEI";
		}
		else if(current_name_Type.equals("FLOAT"))
		{
			write_Operation = "WRITEF";
		}
		else if(current_name_Type.equals("STRING"))
		{
			write_Operation = "WRITES";
		}
		System.out.println(write_Operation+" "+ctx.name_list().name().getText());
		MicroParser.Name_repeatContext name_repeat = ctx.name_list().name_repeat();
		while(name_repeat.name() != null)
		{
			current_name = name_repeat.name().getText();
			write_Operation = "ERROR";
			current_name_Type = symbolTable.lookup(current_name).type;
			if (current_name_Type.equals("INT"))
			{
				write_Operation = "WRITEI";
			}
			else if(current_name_Type.equals("FLOAT"))
			{
				write_Operation = "WRITEF";
			}
			else if(current_name_Type.equals("STRING"))
			{
				write_Operation = "WRITES";
			}
			System.out.println(write_Operation+" "+name_repeat.name().getText());
			name_repeat = name_repeat.name_repeat();
		}
	}
	@Override
	public void exitRead(MicroParser.ReadContext ctx)
	{
		String current_name = ctx.name_list().name().getText();
		String read_operation = "ERROR";
		String current_name_Type = symbolTable.lookup(current_name).type;
		if (current_name_Type.equals("INT"))
		{
			read_operation = "READI";
		}
		else if(current_name_Type.equals("FLOAT"))
		{
			read_operation = "READF";
		}
		System.out.println(read_operation+" "+ctx.name_list().name().getText());
		MicroParser.Name_repeatContext name_repeat = ctx.name_list().name_repeat();
		while(name_repeat.name() != null)
		{
			current_name = ctx.name_list().name().getText();
			read_operation = "ERROR";
			current_name_Type = symbolTable.lookup(current_name).type;
			if (current_name_Type.equals("INT"))
			{
				read_operation = "READI";
			}
			else if(current_name_Type.equals("FLOAT"))
			{
				read_operation = "READF";
			}
			System.out.println(read_operation+" "+name_repeat.name().getText());
			name_repeat = name_repeat.name_repeat();
		}
		//createNode(ctx,"read",ctx.name_list().getText());
		//System.out.println(scopes.get(ctx).information.get("read"));
	}
	@Override 
	public void exitEveryRule(ParserRuleContext ctx)
	{ 
		ParserRuleContext parent = ctx.getParent();
		if(parent != null)
		{
			Scope parentScope = scopes.get(ctx.getParent());
			parentScope.information.putAll(scopes.get(ctx).information);
		}
	}
	@Override
	public void enterEveryRule(ParserRuleContext ctx)
	{
		if(ctx.getText() != null)
		{
			scopes.put(ctx, new Scope(ctx.getText()));
		}
	}*/
}