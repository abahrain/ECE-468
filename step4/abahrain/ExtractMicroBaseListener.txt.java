import java.util.LinkedHashMap;
import java.util.Map;

public class ExtractMicroBaseListener
  extends MicroBaseListener
{
  MicroParser parser;
  public SymbolTable stack;
  int register_count = 1;
  protected Map<String, Integer> functionMap = new LinkedHashMap();
  
  public ExtractMicroBaseListener(MicroParser parser)
  {
    this.stack = new SymbolTable();
    this.parser = parser;
  }
  
  public void enterFunc_decl(MicroParser.Func_declContext ctx)
  {
    this.stack.pushScope(ctx.name().getText());
    if (ctx.any_type().getText().equalsIgnoreCase("INT")) {
      this.functionMap.put(ctx.name().getText(), Integer.valueOf(1));
    } else if (ctx.any_type().getText().equalsIgnoreCase("FLOAT")) {
      this.functionMap.put(ctx.name().getText(), Integer.valueOf(2));
    } else {
      this.functionMap.put(ctx.name().getText(), Integer.valueOf(6));
    }
  }
  
  public void exitString_decl(MicroParser.String_declContext ctx)
  {
    this.stack.currentScope().define(new BaseDescriptor(ctx.str().getText()), ctx.name().getText(), ValueType.STRING);
  }
  
  public void enterProgram(MicroParser.ProgramContext ctx) {}
  
  public void exitVar_decl(MicroParser.Var_declContext ctx)
  {
    String temp = ctx.name_list().getText();
    String[] array = temp.split(",");
    if (ctx.variable_type().getText().equals("INT")) {
      for (String var : array) {
        this.stack.currentScope().define(new BaseDescriptor(), var, ValueType.INT);
      }
    } else {
      for (String var : array) {
        this.stack.currentScope().define(new BaseDescriptor(), var, ValueType.FLOAT);
      }
    }
  }
  
  public void enterIf_stmt(MicroParser.If_stmtContext ctx)
  {
    this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
    
    this.register_count += 1;
  }
  
  public void enterElse_part(MicroParser.Else_partContext ctx)
  {
    if (!ctx.getText().equals(""))
    {
      this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
      
      this.register_count += 1;
    }
  }
  
  public void enterWhile_stmt(MicroParser.While_stmtContext ctx)
  {
    this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
    this.register_count += 1;
  }
  
  public void enterParam_decl(MicroParser.Param_declContext ctx)
  {
    if (ctx.var_type().getText().equals("INT")) {
      this.stack.currentScope().define(new BaseDescriptor(true), ctx.name().getText(), ValueType.INT);
    } else {
      this.stack.currentScope().define(new BaseDescriptor(true), ctx.name().getText(), ValueType.FLOAT);
    }
  }
  
  public void enterAug_if_stmt(MicroParser.Aug_if_stmtContext ctx)
  {
    this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
    
    this.register_count += 1;
  }
  
  public void enterAug_else_part(MicroParser.Aug_else_partContext ctx)
  {
    if (!ctx.getText().equals(""))
    {
      this.stack.pushScope("BLOCK " + Integer.toString(this.register_count));
      
      this.register_count += 1;
    }
  }
}
