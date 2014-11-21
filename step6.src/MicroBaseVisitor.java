/*   1:    */ import org.antlr.v4.runtime.misc.NotNull;
/*   2:    */ import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
/*   3:    */ 
/*   4:    */ public class MicroBaseVisitor<T>
/*   5:    */   extends AbstractParseTreeVisitor<T>
/*   6:    */   implements MicroVisitor<T>
/*   7:    */ {
/*   8:    */   public T visitDecl(@NotNull MicroParser.DeclContext ctx)
/*   9:    */   {
/*  10: 20 */     return visitChildren(ctx);
/*  11:    */   }
/*  12:    */   
/*  13:    */   public T visitAny_type(@NotNull MicroParser.Any_typeContext ctx)
/*  14:    */   {
/*  15: 28 */     return visitChildren(ctx);
/*  16:    */   }
/*  17:    */   
/*  18:    */   public T visitFunc_declarations(@NotNull MicroParser.Func_declarationsContext ctx)
/*  19:    */   {
/*  20: 36 */     return visitChildren(ctx);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public T visitElse_part(@NotNull MicroParser.Else_partContext ctx)
/*  24:    */   {
/*  25: 44 */     return visitChildren(ctx);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public T visitProgram(@NotNull MicroParser.ProgramContext ctx)
/*  29:    */   {
/*  30: 52 */     return visitChildren(ctx);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public T visitCond(@NotNull MicroParser.CondContext ctx)
/*  34:    */   {
/*  35: 60 */     return visitChildren(ctx);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public T visitCompop(@NotNull MicroParser.CompopContext ctx)
/*  39:    */   {
/*  40: 68 */     return visitChildren(ctx);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public T visitMulop(@NotNull MicroParser.MulopContext ctx)
/*  44:    */   {
/*  45: 76 */     return visitChildren(ctx);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public T visitExpr_list(@NotNull MicroParser.Expr_listContext ctx)
/*  49:    */   {
/*  50: 84 */     return visitChildren(ctx);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public T visitStmt_list(@NotNull MicroParser.Stmt_listContext ctx)
/*  54:    */   {
/*  55: 92 */     return visitChildren(ctx);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public T visitId(@NotNull MicroParser.IdContext ctx)
/*  59:    */   {
/*  60:100 */     return visitChildren(ctx);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public T visitAssign_stmt(@NotNull MicroParser.Assign_stmtContext ctx)
/*  64:    */   {
/*  65:108 */     return visitChildren(ctx);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public T visitReturn_stmt(@NotNull MicroParser.Return_stmtContext ctx)
/*  69:    */   {
/*  70:116 */     return visitChildren(ctx);
/*  71:    */   }
/*  72:    */   
/*  73:    */   public T visitAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext ctx)
/*  74:    */   {
/*  75:124 */     return visitChildren(ctx);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public T visitWhile_stmt(@NotNull MicroParser.While_stmtContext ctx)
/*  79:    */   {
/*  80:132 */     return visitChildren(ctx);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public T visitCall_expr(@NotNull MicroParser.Call_exprContext ctx)
/*  84:    */   {
/*  85:140 */     return visitChildren(ctx);
/*  86:    */   }
/*  87:    */   
/*  88:    */   public T visitStr(@NotNull MicroParser.StrContext ctx)
/*  89:    */   {
/*  90:148 */     return visitChildren(ctx);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public T visitFunc_decl(@NotNull MicroParser.Func_declContext ctx)
/*  94:    */   {
/*  95:156 */     return visitChildren(ctx);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public T visitFactor_prefix(@NotNull MicroParser.Factor_prefixContext ctx)
/*  99:    */   {
/* 100:164 */     return visitChildren(ctx);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public T visitVar_decl(@NotNull MicroParser.Var_declContext ctx)
/* 104:    */   {
/* 105:172 */     return visitChildren(ctx);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public T visitParam_decl_tail(@NotNull MicroParser.Param_decl_tailContext ctx)
/* 109:    */   {
/* 110:180 */     return visitChildren(ctx);
/* 111:    */   }
/* 112:    */   
/* 113:    */   public T visitWrite_stmt(@NotNull MicroParser.Write_stmtContext ctx)
/* 114:    */   {
/* 115:188 */     return visitChildren(ctx);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public T visitPrimary(@NotNull MicroParser.PrimaryContext ctx)
/* 119:    */   {
/* 120:196 */     return visitChildren(ctx);
/* 121:    */   }
/* 122:    */   
/* 123:    */   public T visitExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext ctx)
/* 124:    */   {
/* 125:204 */     return visitChildren(ctx);
/* 126:    */   }
/* 127:    */   
/* 128:    */   public T visitId_list(@NotNull MicroParser.Id_listContext ctx)
/* 129:    */   {
/* 130:212 */     return visitChildren(ctx);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public T visitAug_break(@NotNull MicroParser.Aug_breakContext ctx)
/* 134:    */   {
/* 135:220 */     return visitChildren(ctx);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public T visitParam_decl_list(@NotNull MicroParser.Param_decl_listContext ctx)
/* 139:    */   {
/* 140:228 */     return visitChildren(ctx);
/* 141:    */   }
/* 142:    */   
/* 143:    */   public T visitAssign_expr(@NotNull MicroParser.Assign_exprContext ctx)
/* 144:    */   {
/* 145:236 */     return visitChildren(ctx);
/* 146:    */   }
/* 147:    */   
/* 148:    */   public T visitPostfix_expr(@NotNull MicroParser.Postfix_exprContext ctx)
/* 149:    */   {
/* 150:244 */     return visitChildren(ctx);
/* 151:    */   }
/* 152:    */   
/* 153:    */   public T visitAddop(@NotNull MicroParser.AddopContext ctx)
/* 154:    */   {
/* 155:252 */     return visitChildren(ctx);
/* 156:    */   }
/* 157:    */   
/* 158:    */   public T visitIf_stmt(@NotNull MicroParser.If_stmtContext ctx)
/* 159:    */   {
/* 160:260 */     return visitChildren(ctx);
/* 161:    */   }
/* 162:    */   
/* 163:    */   public T visitAug_continue(@NotNull MicroParser.Aug_continueContext ctx)
/* 164:    */   {
/* 165:268 */     return visitChildren(ctx);
/* 166:    */   }
/* 167:    */   
/* 168:    */   public T visitBase_stmt(@NotNull MicroParser.Base_stmtContext ctx)
/* 169:    */   {
/* 170:276 */     return visitChildren(ctx);
/* 171:    */   }
/* 172:    */   
/* 173:    */   public T visitFunc_body(@NotNull MicroParser.Func_bodyContext ctx)
/* 174:    */   {
/* 175:284 */     return visitChildren(ctx);
/* 176:    */   }
/* 177:    */   
/* 178:    */   public T visitVar_type(@NotNull MicroParser.Var_typeContext ctx)
/* 179:    */   {
/* 180:292 */     return visitChildren(ctx);
/* 181:    */   }
/* 182:    */   
/* 183:    */   public T visitCond_expr1(@NotNull MicroParser.Cond_expr1Context ctx)
/* 184:    */   {
/* 185:300 */     return visitChildren(ctx);
/* 186:    */   }
/* 187:    */   
/* 188:    */   public T visitExpr(@NotNull MicroParser.ExprContext ctx)
/* 189:    */   {
/* 190:308 */     return visitChildren(ctx);
/* 191:    */   }
/* 192:    */   
/* 193:    */   public T visitFactor(@NotNull MicroParser.FactorContext ctx)
/* 194:    */   {
/* 195:316 */     return visitChildren(ctx);
/* 196:    */   }
/* 197:    */   
/* 198:    */   public T visitAug_else_part(@NotNull MicroParser.Aug_else_partContext ctx)
/* 199:    */   {
/* 200:324 */     return visitChildren(ctx);
/* 201:    */   }
/* 202:    */   
/* 203:    */   public T visitPgm_body(@NotNull MicroParser.Pgm_bodyContext ctx)
/* 204:    */   {
/* 205:332 */     return visitChildren(ctx);
/* 206:    */   }
/* 207:    */   
/* 208:    */   public T visitParam_decl(@NotNull MicroParser.Param_declContext ctx)
/* 209:    */   {
/* 210:340 */     return visitChildren(ctx);
/* 211:    */   }
/* 212:    */   
/* 213:    */   public T visitCond_expr(@NotNull MicroParser.Cond_exprContext ctx)
/* 214:    */   {
/* 215:348 */     return visitChildren(ctx);
/* 216:    */   }
/* 217:    */   
/* 218:    */   public T visitString_decl(@NotNull MicroParser.String_declContext ctx)
/* 219:    */   {
/* 220:356 */     return visitChildren(ctx);
/* 221:    */   }
/* 222:    */   
/* 223:    */   public T visitAug_stmt(@NotNull MicroParser.Aug_stmtContext ctx)
/* 224:    */   {
/* 225:364 */     return visitChildren(ctx);
/* 226:    */   }
/* 227:    */   
/* 228:    */   public T visitAug_stmt_list(@NotNull MicroParser.Aug_stmt_listContext ctx)
/* 229:    */   {
/* 230:372 */     return visitChildren(ctx);
/* 231:    */   }
/* 232:    */   
/* 233:    */   public T visitRead_stmt(@NotNull MicroParser.Read_stmtContext ctx)
/* 234:    */   {
/* 235:380 */     return visitChildren(ctx);
/* 236:    */   }
/* 237:    */   
/* 238:    */   public T visitId_tail(@NotNull MicroParser.Id_tailContext ctx)
/* 239:    */   {
/* 240:388 */     return visitChildren(ctx);
/* 241:    */   }
/* 242:    */   
/* 243:    */   public T visitStmt(@NotNull MicroParser.StmtContext ctx)
/* 244:    */   {
/* 245:396 */     return visitChildren(ctx);
/* 246:    */   }
/* 247:    */   
/* 248:    */   public T visitExpr_prefix(@NotNull MicroParser.Expr_prefixContext ctx)
/* 249:    */   {
/* 250:404 */     return visitChildren(ctx);
/* 251:    */   }
/* 252:    */ }


/* Location:           C:\Users\Adam\Downloads\step6.jar
 * Qualified Name:     MicroBaseVisitor
 * JD-Core Version:    0.7.0.1
 */