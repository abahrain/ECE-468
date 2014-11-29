import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public abstract interface MicroListener
  extends ParseTreeListener
{
  public abstract void enterDecl(@NotNull MicroParser.DeclContext paramDeclContext);
  
  public abstract void exitDecl(@NotNull MicroParser.DeclContext paramDeclContext);
  
  public abstract void enterAny_type(@NotNull MicroParser.Any_typeContext paramAny_typeContext);
  
  public abstract void exitAny_type(@NotNull MicroParser.Any_typeContext paramAny_typeContext);
  
  public abstract void enterFunc_declarations(@NotNull MicroParser.Func_declarationsContext paramFunc_declarationsContext);
  
  public abstract void exitFunc_declarations(@NotNull MicroParser.Func_declarationsContext paramFunc_declarationsContext);
  
  public abstract void enterElse_part(@NotNull MicroParser.Else_partContext paramElse_partContext);
  
  public abstract void exitElse_part(@NotNull MicroParser.Else_partContext paramElse_partContext);
  
  public abstract void enterProgram(@NotNull MicroParser.ProgramContext paramProgramContext);
  
  public abstract void exitProgram(@NotNull MicroParser.ProgramContext paramProgramContext);
  
  public abstract void enterCond(@NotNull MicroParser.CondContext paramCondContext);
  
  public abstract void exitCond(@NotNull MicroParser.CondContext paramCondContext);
  
  public abstract void enterCompop(@NotNull MicroParser.CompopContext paramCompopContext);
  
  public abstract void exitCompop(@NotNull MicroParser.CompopContext paramCompopContext);
  
  public abstract void enterMulop(@NotNull MicroParser.MulopContext paramMulopContext);
  
  public abstract void exitMulop(@NotNull MicroParser.MulopContext paramMulopContext);
  
  public abstract void enterExpr_list(@NotNull MicroParser.Expr_listContext paramExpr_listContext);
  
  public abstract void exitExpr_list(@NotNull MicroParser.Expr_listContext paramExpr_listContext);
  
  public abstract void enterStmt_list(@NotNull MicroParser.Stmt_listContext paramStmt_listContext);
  
  public abstract void exitStmt_list(@NotNull MicroParser.Stmt_listContext paramStmt_listContext);
  
  public abstract void enterId(@NotNull MicroParser.IdContext paramIdContext);
  
  public abstract void exitId(@NotNull MicroParser.IdContext paramIdContext);
  
  public abstract void enterAssign_stmt(@NotNull MicroParser.Assign_stmtContext paramAssign_stmtContext);
  
  public abstract void exitAssign_stmt(@NotNull MicroParser.Assign_stmtContext paramAssign_stmtContext);
  
  public abstract void enterReturn_stmt(@NotNull MicroParser.Return_stmtContext paramReturn_stmtContext);
  
  public abstract void exitReturn_stmt(@NotNull MicroParser.Return_stmtContext paramReturn_stmtContext);
  
  public abstract void enterAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext paramAug_if_stmtContext);
  
  public abstract void exitAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext paramAug_if_stmtContext);
  
  public abstract void enterWhile_stmt(@NotNull MicroParser.While_stmtContext paramWhile_stmtContext);
  
  public abstract void exitWhile_stmt(@NotNull MicroParser.While_stmtContext paramWhile_stmtContext);
  
  public abstract void enterCall_expr(@NotNull MicroParser.Call_exprContext paramCall_exprContext);
  
  public abstract void exitCall_expr(@NotNull MicroParser.Call_exprContext paramCall_exprContext);
  
  public abstract void enterStr(@NotNull MicroParser.StrContext paramStrContext);
  
  public abstract void exitStr(@NotNull MicroParser.StrContext paramStrContext);
  
  public abstract void enterFunc_decl(@NotNull MicroParser.Func_declContext paramFunc_declContext);
  
  public abstract void exitFunc_decl(@NotNull MicroParser.Func_declContext paramFunc_declContext);
  
  public abstract void enterFactor_prefix(@NotNull MicroParser.Factor_prefixContext paramFactor_prefixContext);
  
  public abstract void exitFactor_prefix(@NotNull MicroParser.Factor_prefixContext paramFactor_prefixContext);
  
  public abstract void enterVar_decl(@NotNull MicroParser.Var_declContext paramVar_declContext);
  
  public abstract void exitVar_decl(@NotNull MicroParser.Var_declContext paramVar_declContext);
  
  public abstract void enterParam_decl_tail(@NotNull MicroParser.Param_decl_tailContext paramParam_decl_tailContext);
  
  public abstract void exitParam_decl_tail(@NotNull MicroParser.Param_decl_tailContext paramParam_decl_tailContext);
  
  public abstract void enterWrite_stmt(@NotNull MicroParser.Write_stmtContext paramWrite_stmtContext);
  
  public abstract void exitWrite_stmt(@NotNull MicroParser.Write_stmtContext paramWrite_stmtContext);
  
  public abstract void enterPrimary(@NotNull MicroParser.PrimaryContext paramPrimaryContext);
  
  public abstract void exitPrimary(@NotNull MicroParser.PrimaryContext paramPrimaryContext);
  
  public abstract void enterExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext paramExpr_list_tailContext);
  
  public abstract void exitExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext paramExpr_list_tailContext);
  
  public abstract void enterId_list(@NotNull MicroParser.Id_listContext paramId_listContext);
  
  public abstract void exitId_list(@NotNull MicroParser.Id_listContext paramId_listContext);
  
  public abstract void enterAug_break(@NotNull MicroParser.Aug_breakContext paramAug_breakContext);
  
  public abstract void exitAug_break(@NotNull MicroParser.Aug_breakContext paramAug_breakContext);
  
  public abstract void enterParam_decl_list(@NotNull MicroParser.Param_decl_listContext paramParam_decl_listContext);
  
  public abstract void exitParam_decl_list(@NotNull MicroParser.Param_decl_listContext paramParam_decl_listContext);
  
  public abstract void enterAssign_expr(@NotNull MicroParser.Assign_exprContext paramAssign_exprContext);
  
  public abstract void exitAssign_expr(@NotNull MicroParser.Assign_exprContext paramAssign_exprContext);
  
  public abstract void enterPostfix_expr(@NotNull MicroParser.Postfix_exprContext paramPostfix_exprContext);
  
  public abstract void exitPostfix_expr(@NotNull MicroParser.Postfix_exprContext paramPostfix_exprContext);
  
  public abstract void enterAddop(@NotNull MicroParser.AddopContext paramAddopContext);
  
  public abstract void exitAddop(@NotNull MicroParser.AddopContext paramAddopContext);
  
  public abstract void enterIf_stmt(@NotNull MicroParser.If_stmtContext paramIf_stmtContext);
  
  public abstract void exitIf_stmt(@NotNull MicroParser.If_stmtContext paramIf_stmtContext);
  
  public abstract void enterAug_continue(@NotNull MicroParser.Aug_continueContext paramAug_continueContext);
  
  public abstract void exitAug_continue(@NotNull MicroParser.Aug_continueContext paramAug_continueContext);
  
  public abstract void enterBase_stmt(@NotNull MicroParser.Base_stmtContext paramBase_stmtContext);
  
  public abstract void exitBase_stmt(@NotNull MicroParser.Base_stmtContext paramBase_stmtContext);
  
  public abstract void enterFunc_body(@NotNull MicroParser.Func_bodyContext paramFunc_bodyContext);
  
  public abstract void exitFunc_body(@NotNull MicroParser.Func_bodyContext paramFunc_bodyContext);
  
  public abstract void enterVar_type(@NotNull MicroParser.Var_typeContext paramVar_typeContext);
  
  public abstract void exitVar_type(@NotNull MicroParser.Var_typeContext paramVar_typeContext);
  
  public abstract void enterCond_expr1(@NotNull MicroParser.Cond_expr1Context paramCond_expr1Context);
  
  public abstract void exitCond_expr1(@NotNull MicroParser.Cond_expr1Context paramCond_expr1Context);
  
  public abstract void enterExpr(@NotNull MicroParser.ExprContext paramExprContext);
  
  public abstract void exitExpr(@NotNull MicroParser.ExprContext paramExprContext);
  
  public abstract void enterFactor(@NotNull MicroParser.FactorContext paramFactorContext);
  
  public abstract void exitFactor(@NotNull MicroParser.FactorContext paramFactorContext);
  
  public abstract void enterAug_else_part(@NotNull MicroParser.Aug_else_partContext paramAug_else_partContext);
  
  public abstract void exitAug_else_part(@NotNull MicroParser.Aug_else_partContext paramAug_else_partContext);
  
  public abstract void enterPgm_body(@NotNull MicroParser.Pgm_bodyContext paramPgm_bodyContext);
  
  public abstract void exitPgm_body(@NotNull MicroParser.Pgm_bodyContext paramPgm_bodyContext);
  
  public abstract void enterParam_decl(@NotNull MicroParser.Param_declContext paramParam_declContext);
  
  public abstract void exitParam_decl(@NotNull MicroParser.Param_declContext paramParam_declContext);
  
  public abstract void enterCond_expr(@NotNull MicroParser.Cond_exprContext paramCond_exprContext);
  
  public abstract void exitCond_expr(@NotNull MicroParser.Cond_exprContext paramCond_exprContext);
  
  public abstract void enterString_decl(@NotNull MicroParser.String_declContext paramString_declContext);
  
  public abstract void exitString_decl(@NotNull MicroParser.String_declContext paramString_declContext);
  
  public abstract void enterAug_stmt(@NotNull MicroParser.Aug_stmtContext paramAug_stmtContext);
  
  public abstract void exitAug_stmt(@NotNull MicroParser.Aug_stmtContext paramAug_stmtContext);
  
  public abstract void enterAug_stmt_list(@NotNull MicroParser.Aug_stmt_listContext paramAug_stmt_listContext);
  
  public abstract void exitAug_stmt_list(@NotNull MicroParser.Aug_stmt_listContext paramAug_stmt_listContext);
  
  public abstract void enterRead_stmt(@NotNull MicroParser.Read_stmtContext paramRead_stmtContext);
  
  public abstract void exitRead_stmt(@NotNull MicroParser.Read_stmtContext paramRead_stmtContext);
  
  public abstract void enterId_tail(@NotNull MicroParser.Id_tailContext paramId_tailContext);
  
  public abstract void exitId_tail(@NotNull MicroParser.Id_tailContext paramId_tailContext);
  
  public abstract void enterStmt(@NotNull MicroParser.StmtContext paramStmtContext);
  
  public abstract void exitStmt(@NotNull MicroParser.StmtContext paramStmtContext);
  
  public abstract void enterExpr_prefix(@NotNull MicroParser.Expr_prefixContext paramExpr_prefixContext);
  
  public abstract void exitExpr_prefix(@NotNull MicroParser.Expr_prefixContext paramExpr_prefixContext);
}


/* Location:           C:\Users\Adam\Downloads\final.jar
 * Qualified Name:     MicroListener
 * JD-Core Version:    0.7.0.1
 */