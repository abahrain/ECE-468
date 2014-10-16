import java.util.LinkedHashMap;
import java.util.Map;

public class MyListener extends MicroBaseListener
{
	Microparser parser;
	public SymbolBlocks stack;
	int register_count = 1;
	protected Map<String, Integer> function = new LinkedHashMap();
	
	public MyListener(MicroParser parser)
	{
		this.stack = new SymbolBlocks();
		this.parser = parser;
	}
	
	//come on... do stuff	
	@Override
	public void exitAssignment_frame(MicroParser.Assignment_frameContext ctx)
	{ 
		System.out.println("STOREI "+ctx.expression().getText()+" $T"+register_count);
		System.out.println("STOREI $T"+register_count+" "+ctx.name().getText());
	}	
	@Override
	public void exitIf_statement(MicroParser.If_statementContext ctx)
	{ 
		this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
		this.register_count += 1;
	}	
	@Override
	public void enterWhile_statement(MicroParser.While_statementContext ctx)
	{ 
		this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
		this.register_count += 1;
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
				this.stack.currentScope().define(new BaseDescriptor(), var, ValueType.INT);
			}
		}
		else
		{
			for (String var : array)
			{
				this.stack.currentScope().define(new BaseDescriptor(), var, ValueType.FLOAT);
			}
		}
	}	
	@Override
	public void enterParameter_declaration(MicroParser.Parameter_declarationContext ctx)
	{ 
		if (ctx.variable_type().getText().equals("INT"))
		{
			this.stack.currentScope().define(new BaseDescriptor(true), ctx.name().getText(), ValueType.INT);
		}
		else
		{
			this.stack.currentScope().define(new BaseDescriptor(true), ctx.name().getText(), ValueType.FLOAT);
		}
	}
	@Override
	public void enterFunction_declaration(MicroParser.Function_declarationContext ctx)
	{ 
		this.stack.pushScope(ctx.name().getText());
		if (ctx.any_type().getText().equalsIgnoreCase("INT"))
		{
			this.function.put(ctx.name().getText(), Integer.valueOf(1));
		}
		else if(ctx.any_type().getText().equalsIgnoreCase("FLOAT"))
		{
			this.function.put(ctx.name().getText(), Integer.valueOf(2));
		}
		else
		{
			this.function.put(ctx.name().getText(), Integer.valueOf(6));
		}
	}		
	@Override
	public void enterString_declaration_list(MicroParser.String_declaration_listContext ctx)
	{ 
		this.stack.currentScope().define(new BaseDescriptor(ctx.string().getText()), ctx.name().getText(), ValueType.STRING);
	}	
	@Override
	public void enterElse_portion(MicroParser.Else_portionContext ctx)
	{ 
		if (!ctx.getText().equals(""))
		{
			this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
			this.register_count += 1;
		}
	}
}
