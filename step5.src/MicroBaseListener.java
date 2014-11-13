import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MicroBaseListener
  implements MicroListener
{
  public void enterDecl(@NotNull MicroParser.DeclContext ctx) {}
  
  public void exitDecl(@NotNull MicroParser.DeclContext ctx) {}
  
  public void enterAny_type(@NotNull MicroParser.Any_typeContext ctx) {}
  
  public void exitAny_type(@NotNull MicroParser.Any_typeContext ctx) {}
  
  public void enterFunc_declarations(@NotNull MicroParser.Func_declarationsContext ctx) {}
  
  public void exitFunc_declarations(@NotNull MicroParser.Func_declarationsContext ctx) {}
  
  public void enterElse_part(@NotNull MicroParser.Else_partContext ctx) {}
  
  public void exitElse_part(@NotNull MicroParser.Else_partContext ctx) {}
  
  public void enterProgram(@NotNull MicroParser.ProgramContext ctx) {}
  
  public void exitProgram(@NotNull MicroParser.ProgramContext ctx) {}
  
  public void enterCond(@NotNull MicroParser.CondContext ctx) {}
  
  public void exitCond(@NotNull MicroParser.CondContext ctx) {}
  
  public void enterCompop(@NotNull MicroParser.CompopContext ctx) {}
  
  public void exitCompop(@NotNull MicroParser.CompopContext ctx) {}
  
  public void enterMulop(@NotNull MicroParser.MulopContext ctx) {}
  
  public void exitMulop(@NotNull MicroParser.MulopContext ctx) {}
  
  public void enterExpr_list(@NotNull MicroParser.Expr_listContext ctx) {}
  
  public void exitExpr_list(@NotNull MicroParser.Expr_listContext ctx) {}
  
  public void enterStmt_list(@NotNull MicroParser.Stmt_listContext ctx) {}
  
  public void exitStmt_list(@NotNull MicroParser.Stmt_listContext ctx) {}
  
  public void enterId(@NotNull MicroParser.IdContext ctx) {}
  
  public void exitId(@NotNull MicroParser.IdContext ctx) {}
  
  public void enterAssign_stmt(@NotNull MicroParser.Assign_stmtContext ctx) {}
  
  public void exitAssign_stmt(@NotNull MicroParser.Assign_stmtContext ctx) {}
  
  public void enterReturn_stmt(@NotNull MicroParser.Return_stmtContext ctx) {}
  
  public void exitReturn_stmt(@NotNull MicroParser.Return_stmtContext ctx) {}
  
  public void enterAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext ctx) {}
  
  public void exitAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext ctx) {}
  
  public void enterWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) {}
  
  public void exitWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) {}
  
  public void enterCall_expr(@NotNull MicroParser.Call_exprContext ctx) {}
  
  public void exitCall_expr(@NotNull MicroParser.Call_exprContext ctx) {}
  
  public void enterStr(@NotNull MicroParser.StrContext ctx) {}
  
  public void exitStr(@NotNull MicroParser.StrContext ctx) {}
  
  public void enterFunc_decl(@NotNull MicroParser.Func_declContext ctx) {}
  
  public void exitFunc_decl(@NotNull MicroParser.Func_declContext ctx) {}
  
  public void enterFactor_prefix(@NotNull MicroParser.Factor_prefixContext ctx) {}
  
  public void exitFactor_prefix(@NotNull MicroParser.Factor_prefixContext ctx) {}
  
  public void enterVar_decl(@NotNull MicroParser.Var_declContext ctx) {}
  
  public void exitVar_decl(@NotNull MicroParser.Var_declContext ctx) {}
  
  public void enterParam_decl_tail(@NotNull MicroParser.Param_decl_tailContext ctx) {}
  
  public void exitParam_decl_tail(@NotNull MicroParser.Param_decl_tailContext ctx) {}
  
  public void enterWrite_stmt(@NotNull MicroParser.Write_stmtContext ctx) {}
  
  public void exitWrite_stmt(@NotNull MicroParser.Write_stmtContext ctx) {}
  
  public void enterPrimary(@NotNull MicroParser.PrimaryContext ctx) {}
  
  public void exitPrimary(@NotNull MicroParser.PrimaryContext ctx) {}
  
  public void enterExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext ctx) {}
  
  public void exitExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext ctx) {}
  
  public void enterId_list(@NotNull MicroParser.Id_listContext ctx) {}
  
  public void exitId_list(@NotNull MicroParser.Id_listContext ctx) {}
  
  public void enterAug_break(@NotNull MicroParser.Aug_breakContext ctx) {}
  
  public void exitAug_break(@NotNull MicroParser.Aug_breakContext ctx) {}
  
  public void enterParam_decl_list(@NotNull MicroParser.Param_decl_listContext ctx) {}
  
  public void exitParam_decl_list(@NotNull MicroParser.Param_decl_listContext ctx) {}
  
  public void enterAssign_expr(@NotNull MicroParser.Assign_exprContext ctx) {}
  
  public void exitAssign_expr(@NotNull MicroParser.Assign_exprContext ctx) {}
  
  public void enterPostfix_expr(@NotNull MicroParser.Postfix_exprContext ctx) {}
  
  public void exitPostfix_expr(@NotNull MicroParser.Postfix_exprContext ctx) {}
  
  public void enterAddop(@NotNull MicroParser.AddopContext ctx) {}
  
  public void exitAddop(@NotNull MicroParser.AddopContext ctx) {}
  
  public void enterIf_stmt(@NotNull MicroParser.If_stmtContext ctx) {}
  
  public void exitIf_stmt(@NotNull MicroParser.If_stmtContext ctx) {}
  
  public void enterAug_continue(@NotNull MicroParser.Aug_continueContext ctx) {}
  
  public void exitAug_continue(@NotNull MicroParser.Aug_continueContext ctx) {}
  
  public void enterBase_stmt(@NotNull MicroParser.Base_stmtContext ctx) {}
  
  public void exitBase_stmt(@NotNull MicroParser.Base_stmtContext ctx) {}
  
  public void enterFunc_body(@NotNull MicroParser.Func_bodyContext ctx) {}
  
  public void exitFunc_body(@NotNull MicroParser.Func_bodyContext ctx) {}
  
  public void enterVar_type(@NotNull MicroParser.Var_typeContext ctx) {}
  
  public void exitVar_type(@NotNull MicroParser.Var_typeContext ctx) {}
  
  public void enterCond_expr1(@NotNull MicroParser.Cond_expr1Context ctx) {}
  
  public void exitCond_expr1(@NotNull MicroParser.Cond_expr1Context ctx) {}
  
  public void enterExpr(@NotNull MicroParser.ExprContext ctx) {}
  
  public void exitExpr(@NotNull MicroParser.ExprContext ctx) {}
  
  public void enterFactor(@NotNull MicroParser.FactorContext ctx) {}
  
  public void exitFactor(@NotNull MicroParser.FactorContext ctx) {}
  
  public void enterAug_else_part(@NotNull MicroParser.Aug_else_partContext ctx) {}
  
  public void exitAug_else_part(@NotNull MicroParser.Aug_else_partContext ctx) {}
  
  public void enterPgm_body(@NotNull MicroParser.Pgm_bodyContext ctx) {}
  
  public void exitPgm_body(@NotNull MicroParser.Pgm_bodyContext ctx) {}
  
  public void enterParam_decl(@NotNull MicroParser.Param_declContext ctx) {}
  
  public void exitParam_decl(@NotNull MicroParser.Param_declContext ctx) {}
  
  public void enterCond_expr(@NotNull MicroParser.Cond_exprContext ctx) {}
  
  public void exitCond_expr(@NotNull MicroParser.Cond_exprContext ctx) {}
  
  public void enterString_decl(@NotNull MicroParser.String_declContext ctx) {}
  
  public void exitString_decl(@NotNull MicroParser.String_declContext ctx) {}
  
  public void enterAug_stmt(@NotNull MicroParser.Aug_stmtContext ctx) {}
  
  public void exitAug_stmt(@NotNull MicroParser.Aug_stmtContext ctx) {}
  
  public void enterAug_stmt_list(@NotNull MicroParser.Aug_stmt_listContext ctx) {}
  
  public void exitAug_stmt_list(@NotNull MicroParser.Aug_stmt_listContext ctx) {}
  
  public void enterRead_stmt(@NotNull MicroParser.Read_stmtContext ctx) {}
  
  public void exitRead_stmt(@NotNull MicroParser.Read_stmtContext ctx) {}
  
  public void enterId_tail(@NotNull MicroParser.Id_tailContext ctx) {}
  
  public void exitId_tail(@NotNull MicroParser.Id_tailContext ctx) {}
  
  public void enterStmt(@NotNull MicroParser.StmtContext ctx) {}
  
  public void exitStmt(@NotNull MicroParser.StmtContext ctx) {}
  
  public void enterExpr_prefix(@NotNull MicroParser.Expr_prefixContext ctx) {}
  
  public void exitExpr_prefix(@NotNull MicroParser.Expr_prefixContext ctx) {}
  
  public void enterEveryRule(@NotNull ParserRuleContext ctx) {}
  
  public void exitEveryRule(@NotNull ParserRuleContext ctx) {}
  
  public void visitTerminal(@NotNull TerminalNode node) {}
  
  public void visitErrorNode(@NotNull ErrorNode node) {}
}


/* Location:           C:\Users\Adam\Downloads\step5.jar
 * Qualified Name:     MicroBaseListener
 * JD-Core Version:    0.7.0.1
 */