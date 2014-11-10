import java.util.LinkedHashMap;
import java.util.Map;

public class MyListener extends MicroBaseListener
{
  MicroParser parser;
  public SymbolTable table;
  int countScope = 1;
  protected Map<String, Integer> functionMap = new LinkedHashMap<String, Integer>();
  
  public MyListener(MicroParser parser)
  {
    this.table = new SymbolTable();
    this.parser = parser;
  }
  
  public void enterFunction_declaration(MicroParser.Function_declarationContext ctx)
  {
    this.table.pushScope(ctx.name().getText());
    if (ctx.any_type().getText().equalsIgnoreCase("INT")) {
      this.functionMap.put(ctx.name().getText(), Integer.valueOf(1));
    } else if (ctx.any_type().getText().equalsIgnoreCase("FLOAT")) {
      this.functionMap.put(ctx.name().getText(), Integer.valueOf(2));
    } else {
      this.functionMap.put(ctx.name().getText(), Integer.valueOf(6));
    }
  }
  
  public void exitString_declaration_list(MicroParser.String_declaration_listContext ctx)
  {
    this.table.currentScope().define(new Quantifier(ctx.string().getText()), ctx.name().getText(), VariableType.STRING);
  }
  
  public void enterProgram(MicroParser.ProgramContext ctx) {}
  
  public void exitVariable_declaration_list(MicroParser.Variable_declaration_listContext ctx)
  {
    String temp = ctx.name_list().getText();
    String[] array = temp.split(",");
    if (ctx.variable_type().getText().equals("INT")) {
      for (String var : array) {
        this.table.currentScope().define(new Quantifier(), var, VariableType.INT);
      }
    } else {
      for (String var : array) {
        this.table.currentScope().define(new Quantifier(), var, VariableType.FLOAT);
      }
    }
  }
  
  public void enterIf_statement(MicroParser.If_statementContext ctx)
  {
    this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
    
    this.countScope += 1;
  }
  
  public void enterElse_portion(MicroParser.Else_portionContext ctx)
  {
    if (!ctx.getText().equals(""))
    {
      this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
      
      this.countScope += 1;
    }
  }
  
  public void enterWhile_statement(MicroParser.While_statementContext ctx)
  {
    this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
    
    this.countScope += 1;
  }
  
  public void enterParameter_declaration(MicroParser.Parameter_declarationContext ctx)
  {
    if (ctx.variable_type().getText().equals("INT")) {
      this.table.currentScope().define(new Quantifier(true), ctx.name().getText(), VariableType.INT);
    } else {
      this.table.currentScope().define(new Quantifier(true), ctx.name().getText(), VariableType.FLOAT);
    }
  }
}
