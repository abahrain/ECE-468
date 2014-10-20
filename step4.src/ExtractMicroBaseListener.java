/*   1:    */ import java.util.LinkedHashMap;
/*   2:    */ import java.util.Map;
/*   3:    */ 
/*   4:    */ public class ExtractMicroBaseListener
/*   5:    */   extends MicroBaseListener
/*   6:    */ {
/*   7:    */   MicroParser parser;
/*   8:    */   public SymbolTable table;
/*   9: 19 */   int countScope = 1;
/*  10: 20 */   protected Map<String, Integer> functionMap = new LinkedHashMap();
/*  11:    */   
/*  12:    */   public ExtractMicroBaseListener(MicroParser parser)
/*  13:    */   {
/*  14: 24 */     this.table = new SymbolTable();
/*  15: 25 */     this.parser = parser;
/*  16:    */   }
/*  17:    */   
/*  18:    */   public void enterFunc_decl(MicroParser.Func_declContext ctx)
/*  19:    */   {
/*  20: 32 */     this.table.pushScope(ctx.id().getText());
/*  21: 33 */     if (ctx.any_type().getText().equalsIgnoreCase("INT")) {
/*  22: 34 */       this.functionMap.put(ctx.id().getText(), Integer.valueOf(1));
/*  23: 36 */     } else if (ctx.any_type().getText().equalsIgnoreCase("FLOAT")) {
/*  24: 37 */       this.functionMap.put(ctx.id().getText(), Integer.valueOf(2));
/*  25:    */     } else {
/*  26: 40 */       this.functionMap.put(ctx.id().getText(), Integer.valueOf(6));
/*  27:    */     }
/*  28:    */   }
/*  29:    */   
/*  30:    */   public void exitString_decl(MicroParser.String_declContext ctx)
/*  31:    */   {
/*  32: 46 */     this.table.currentScope().define(new BaseDescriptor(ctx.str().getText()), ctx.id().getText(), ValueType.STRING);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void enterProgram(MicroParser.ProgramContext ctx) {}
/*  36:    */   
/*  37:    */   public void exitVar_decl(MicroParser.Var_declContext ctx)
/*  38:    */   {
/*  39: 57 */     String temp = ctx.id_list().getText();
/*  40: 58 */     String[] array = temp.split(",");
/*  41: 59 */     if (ctx.var_type().getText().equals("INT")) {
/*  42: 60 */       for (String var : array) {
/*  43: 61 */         this.table.currentScope().define(new BaseDescriptor(), var, ValueType.INT);
/*  44:    */       }
/*  45:    */     } else {
/*  46: 64 */       for (String var : array) {
/*  47: 65 */         this.table.currentScope().define(new BaseDescriptor(), var, ValueType.FLOAT);
/*  48:    */       }
/*  49:    */     }
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void enterIf_stmt(MicroParser.If_stmtContext ctx)
/*  53:    */   {
/*  54: 73 */     this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
/*  55:    */     
/*  56: 75 */     this.countScope += 1;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void enterElse_part(MicroParser.Else_partContext ctx)
/*  60:    */   {
/*  61: 80 */     if (!ctx.getText().equals(""))
/*  62:    */     {
/*  63: 82 */       this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
/*  64:    */       
/*  65: 84 */       this.countScope += 1;
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void enterWhile_stmt(MicroParser.While_stmtContext ctx)
/*  70:    */   {
/*  71: 90 */     this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
/*  72:    */     
/*  73: 92 */     this.countScope += 1;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void enterParam_decl(MicroParser.Param_declContext ctx)
/*  77:    */   {
/*  78: 97 */     if (ctx.var_type().getText().equals("INT")) {
/*  79: 98 */       this.table.currentScope().define(new BaseDescriptor(true), ctx.id().getText(), ValueType.INT);
/*  80:    */     } else {
/*  81:101 */       this.table.currentScope().define(new BaseDescriptor(true), ctx.id().getText(), ValueType.FLOAT);
/*  82:    */     }
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void enterAug_if_stmt(MicroParser.Aug_if_stmtContext ctx)
/*  86:    */   {
/*  87:107 */     this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
/*  88:    */     
/*  89:109 */     this.countScope += 1;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void enterAug_else_part(MicroParser.Aug_else_partContext ctx)
/*  93:    */   {
/*  94:114 */     if (!ctx.getText().equals(""))
/*  95:    */     {
/*  96:116 */       this.table.pushScope("BLOCK " + Integer.toString(this.countScope));
/*  97:    */       
/*  98:118 */       this.countScope += 1;
/*  99:    */     }
/* 100:    */   }
/* 101:    */ }


/* Location:           C:\Users\Adam\Downloads\step4.jar
 * Qualified Name:     ExtractMicroBaseListener
 * JD-Core Version:    0.7.0.1
 */