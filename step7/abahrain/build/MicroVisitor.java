// Generated from Micro.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MicroParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MicroVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MicroParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull MicroParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(@NotNull MicroParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#basic_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasic_statement(@NotNull MicroParser.Basic_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#function_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_body(@NotNull MicroParser.Function_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#expression_list_repeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list_repeat(@NotNull MicroParser.Expression_list_repeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#assignment_frame}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_frame(@NotNull MicroParser.Assignment_frameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#comparison_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison_operator(@NotNull MicroParser.Comparison_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(@NotNull MicroParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#name_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName_list(@NotNull MicroParser.Name_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#while_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statement(@NotNull MicroParser.While_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#program_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_body(@NotNull MicroParser.Program_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#any_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_type(@NotNull MicroParser.Any_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#variable_declaration_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration_list(@NotNull MicroParser.Variable_declaration_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#addition_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition_operation(@NotNull MicroParser.Addition_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull MicroParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#parameter_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_declaration(@NotNull MicroParser.Parameter_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#parameter_declaration_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_declaration_list(@NotNull MicroParser.Parameter_declaration_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#variable_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_type(@NotNull MicroParser.Variable_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(@NotNull MicroParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(@NotNull MicroParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#re_turn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_turn(@NotNull MicroParser.Re_turnContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#write}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite(@NotNull MicroParser.WriteContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#read}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(@NotNull MicroParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(@NotNull MicroParser.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(@NotNull MicroParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#string_declaration_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_declaration_list(@NotNull MicroParser.String_declaration_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#statement_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_list(@NotNull MicroParser.Statement_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(@NotNull MicroParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#pre_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPre_factor(@NotNull MicroParser.Pre_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(@NotNull MicroParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#name_repeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName_repeat(@NotNull MicroParser.Name_repeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#post_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPost_expression(@NotNull MicroParser.Post_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull MicroParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#else_portion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_portion(@NotNull MicroParser.Else_portionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#multiplication_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication_operation(@NotNull MicroParser.Multiplication_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull MicroParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull MicroParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(@NotNull MicroParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#expression_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_call(@NotNull MicroParser.Expression_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#pre_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPre_expression(@NotNull MicroParser.Pre_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#parameter_declaration_repeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_declaration_repeat(@NotNull MicroParser.Parameter_declaration_repeatContext ctx);
}