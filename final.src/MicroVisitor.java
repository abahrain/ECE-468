import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public abstract interface MicroVisitor<T>
  extends ParseTreeVisitor<T>
{
  public abstract T visitDecl(@NotNull MicroParser.DeclContext paramDeclContext);
  
  public abstract T visitAny_type(@NotNull MicroParser.Any_typeContext paramAny_typeContext);
  
  public abstract T visitFunc_declarations(@NotNull MicroParser.Func_declarationsContext paramFunc_declarationsContext);
  
  public abstract T visitElse_part(@NotNull MicroParser.Else_partContext paramElse_partContext);
  
  public abstract T visitProgram(@NotNull MicroParser.ProgramContext paramProgramContext);
  
  public abstract T visitCond(@NotNull MicroParser.CondContext paramCondContext);
  
  public abstract T visitCompop(@NotNull MicroParser.CompopContext paramCompopContext);
  
  public abstract T visitMulop(@NotNull MicroParser.MulopContext paramMulopContext);
  
  public abstract T visitExpr_list(@NotNull MicroParser.Expr_listContext paramExpr_listContext);
  
  public abstract T visitStmt_list(@NotNull MicroParser.Stmt_listContext paramStmt_listContext);
  
  public abstract T visitId(@NotNull MicroParser.IdContext paramIdContext);
  
  public abstract T visitAssign_stmt(@NotNull MicroParser.Assign_stmtContext paramAssign_stmtContext);
  
  public abstract T visitReturn_stmt(@NotNull MicroParser.Return_stmtContext paramReturn_stmtContext);
  
  public abstract T visitAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext paramAug_if_stmtContext);
  
  public abstract T visitWhile_stmt(@NotNull MicroParser.While_stmtContext paramWhile_stmtContext);
  
  public abstract T visitCall_expr(@NotNull MicroParser.Call_exprContext paramCall_exprContext);
  
  public abstract T visitStr(@NotNull MicroParser.StrContext paramStrContext);
  
  public abstract T visitFunc_decl(@NotNull MicroParser.Func_declContext paramFunc_declContext);
  
  public abstract T visitFactor_prefix(@NotNull MicroParser.Factor_prefixContext paramFactor_prefixContext);
  
  public abstract T visitVar_decl(@NotNull MicroParser.Var_declContext paramVar_declContext);
  
  public abstract T visitParam_decl_tail(@NotNull MicroParser.Param_decl_tailContext paramParam_decl_tailContext);
  
  public abstract T visitWrite_stmt(@NotNull MicroParser.Write_stmtContext paramWrite_stmtContext);
  
  public abstract T visitPrimary(@NotNull MicroParser.PrimaryContext paramPrimaryContext);
  
  public abstract T visitExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext paramExpr_list_tailContext);
  
  public abstract T visitId_list(@NotNull MicroParser.Id_listContext paramId_listContext);
  
  public abstract T visitAug_break(@NotNull MicroParser.Aug_breakContext paramAug_breakContext);
  
  public abstract T visitParam_decl_list(@NotNull MicroParser.Param_decl_listContext paramParam_decl_listContext);
  
  public abstract T visitAssign_expr(@NotNull MicroParser.Assign_exprContext paramAssign_exprContext);
  
  public abstract T visitPostfix_expr(@NotNull MicroParser.Postfix_exprContext paramPostfix_exprContext);
  
  public abstract T visitAddop(@NotNull MicroParser.AddopContext paramAddopContext);
  
  public abstract T visitIf_stmt(@NotNull MicroParser.If_stmtContext paramIf_stmtContext);
  
  public abstract T visitAug_continue(@NotNull MicroParser.Aug_continueContext paramAug_continueContext);
  
  public abstract T visitBase_stmt(@NotNull MicroParser.Base_stmtContext paramBase_stmtContext);
  
  public abstract T visitFunc_body(@NotNull MicroParser.Func_bodyContext paramFunc_bodyContext);
  
  public abstract T visitVar_type(@NotNull MicroParser.Var_typeContext paramVar_typeContext);
  
  public abstract T visitCond_expr1(@NotNull MicroParser.Cond_expr1Context paramCond_expr1Context);
  
  public abstract T visitExpr(@NotNull MicroParser.ExprContext paramExprContext);
  
  public abstract T visitFactor(@NotNull MicroParser.FactorContext paramFactorContext);
  
  public abstract T visitAug_else_part(@NotNull MicroParser.Aug_else_partContext paramAug_else_partContext);
  
  public abstract T visitPgm_body(@NotNull MicroParser.Pgm_bodyContext paramPgm_bodyContext);
  
  public abstract T visitParam_decl(@NotNull MicroParser.Param_declContext paramParam_declContext);
  
  public abstract T visitCond_expr(@NotNull MicroParser.Cond_exprContext paramCond_exprContext);
  
  public abstract T visitString_decl(@NotNull MicroParser.String_declContext paramString_declContext);
  
  public abstract T visitAug_stmt(@NotNull MicroParser.Aug_stmtContext paramAug_stmtContext);
  
  public abstract T visitAug_stmt_list(@NotNull MicroParser.Aug_stmt_listContext paramAug_stmt_listContext);
  
  public abstract T visitRead_stmt(@NotNull MicroParser.Read_stmtContext paramRead_stmtContext);
  
  public abstract T visitId_tail(@NotNull MicroParser.Id_tailContext paramId_tailContext);
  
  public abstract T visitStmt(@NotNull MicroParser.StmtContext paramStmtContext);
  
  public abstract T visitExpr_prefix(@NotNull MicroParser.Expr_prefixContext paramExpr_prefixContext);
}


/* Location:           C:\Users\Adam\Downloads\final.jar
 * Qualified Name:     MicroVisitor
 * JD-Core Version:    0.7.0.1
 */