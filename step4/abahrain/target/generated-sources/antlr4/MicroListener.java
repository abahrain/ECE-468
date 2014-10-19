// Generated from Micro.g4 by ANTLR 4.2.2
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MicroParser}.
 */
public interface MicroListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MicroParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull MicroParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull MicroParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(@NotNull MicroParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(@NotNull MicroParser.Expression_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#basic_statement}.
	 * @param ctx the parse tree
	 */
	void enterBasic_statement(@NotNull MicroParser.Basic_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#basic_statement}.
	 * @param ctx the parse tree
	 */
	void exitBasic_statement(@NotNull MicroParser.Basic_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#function_body}.
	 * @param ctx the parse tree
	 */
	void enterFunction_body(@NotNull MicroParser.Function_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#function_body}.
	 * @param ctx the parse tree
	 */
	void exitFunction_body(@NotNull MicroParser.Function_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#expression_list_repeat}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list_repeat(@NotNull MicroParser.Expression_list_repeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#expression_list_repeat}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list_repeat(@NotNull MicroParser.Expression_list_repeatContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#assignment_frame}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_frame(@NotNull MicroParser.Assignment_frameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#assignment_frame}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_frame(@NotNull MicroParser.Assignment_frameContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterComparison_operator(@NotNull MicroParser.Comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitComparison_operator(@NotNull MicroParser.Comparison_operatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(@NotNull MicroParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(@NotNull MicroParser.If_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#name_list}.
	 * @param ctx the parse tree
	 */
	void enterName_list(@NotNull MicroParser.Name_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#name_list}.
	 * @param ctx the parse tree
	 */
	void exitName_list(@NotNull MicroParser.Name_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statement(@NotNull MicroParser.While_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statement(@NotNull MicroParser.While_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#program_body}.
	 * @param ctx the parse tree
	 */
	void enterProgram_body(@NotNull MicroParser.Program_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#program_body}.
	 * @param ctx the parse tree
	 */
	void exitProgram_body(@NotNull MicroParser.Program_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#any_type}.
	 * @param ctx the parse tree
	 */
	void enterAny_type(@NotNull MicroParser.Any_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#any_type}.
	 * @param ctx the parse tree
	 */
	void exitAny_type(@NotNull MicroParser.Any_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#variable_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration_list(@NotNull MicroParser.Variable_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#variable_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration_list(@NotNull MicroParser.Variable_declaration_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#addition_operation}.
	 * @param ctx the parse tree
	 */
	void enterAddition_operation(@NotNull MicroParser.Addition_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#addition_operation}.
	 * @param ctx the parse tree
	 */
	void exitAddition_operation(@NotNull MicroParser.Addition_operationContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull MicroParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull MicroParser.FunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#parameter_declaration}.
	 * @param ctx the parse tree
	 */
	void enterParameter_declaration(@NotNull MicroParser.Parameter_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#parameter_declaration}.
	 * @param ctx the parse tree
	 */
	void exitParameter_declaration(@NotNull MicroParser.Parameter_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#parameter_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterParameter_declaration_list(@NotNull MicroParser.Parameter_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#parameter_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitParameter_declaration_list(@NotNull MicroParser.Parameter_declaration_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#variable_type}.
	 * @param ctx the parse tree
	 */
	void enterVariable_type(@NotNull MicroParser.Variable_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#variable_type}.
	 * @param ctx the parse tree
	 */
	void exitVariable_type(@NotNull MicroParser.Variable_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(@NotNull MicroParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(@NotNull MicroParser.PrimaryContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(@NotNull MicroParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(@NotNull MicroParser.NameContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#re_turn}.
	 * @param ctx the parse tree
	 */
	void enterRe_turn(@NotNull MicroParser.Re_turnContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#re_turn}.
	 * @param ctx the parse tree
	 */
	void exitRe_turn(@NotNull MicroParser.Re_turnContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#write}.
	 * @param ctx the parse tree
	 */
	void enterWrite(@NotNull MicroParser.WriteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#write}.
	 * @param ctx the parse tree
	 */
	void exitWrite(@NotNull MicroParser.WriteContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#read}.
	 * @param ctx the parse tree
	 */
	void enterRead(@NotNull MicroParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#read}.
	 * @param ctx the parse tree
	 */
	void exitRead(@NotNull MicroParser.ReadContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(@NotNull MicroParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(@NotNull MicroParser.Function_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull MicroParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull MicroParser.DeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#string_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterString_declaration_list(@NotNull MicroParser.String_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#string_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitString_declaration_list(@NotNull MicroParser.String_declaration_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#statement_list}.
	 * @param ctx the parse tree
	 */
	void enterStatement_list(@NotNull MicroParser.Statement_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#statement_list}.
	 * @param ctx the parse tree
	 */
	void exitStatement_list(@NotNull MicroParser.Statement_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull MicroParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull MicroParser.ConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#pre_factor}.
	 * @param ctx the parse tree
	 */
	void enterPre_factor(@NotNull MicroParser.Pre_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#pre_factor}.
	 * @param ctx the parse tree
	 */
	void exitPre_factor(@NotNull MicroParser.Pre_factorContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull MicroParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull MicroParser.FactorContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#name_repeat}.
	 * @param ctx the parse tree
	 */
	void enterName_repeat(@NotNull MicroParser.Name_repeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#name_repeat}.
	 * @param ctx the parse tree
	 */
	void exitName_repeat(@NotNull MicroParser.Name_repeatContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#post_expression}.
	 * @param ctx the parse tree
	 */
	void enterPost_expression(@NotNull MicroParser.Post_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#post_expression}.
	 * @param ctx the parse tree
	 */
	void exitPost_expression(@NotNull MicroParser.Post_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull MicroParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull MicroParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#else_portion}.
	 * @param ctx the parse tree
	 */
	void enterElse_portion(@NotNull MicroParser.Else_portionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#else_portion}.
	 * @param ctx the parse tree
	 */
	void exitElse_portion(@NotNull MicroParser.Else_portionContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#multiplication_operation}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication_operation(@NotNull MicroParser.Multiplication_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#multiplication_operation}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication_operation(@NotNull MicroParser.Multiplication_operationContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(@NotNull MicroParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(@NotNull MicroParser.AssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull MicroParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull MicroParser.ProgramContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull MicroParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull MicroParser.StringContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#expression_call}.
	 * @param ctx the parse tree
	 */
	void enterExpression_call(@NotNull MicroParser.Expression_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#expression_call}.
	 * @param ctx the parse tree
	 */
	void exitExpression_call(@NotNull MicroParser.Expression_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#pre_expression}.
	 * @param ctx the parse tree
	 */
	void enterPre_expression(@NotNull MicroParser.Pre_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#pre_expression}.
	 * @param ctx the parse tree
	 */
	void exitPre_expression(@NotNull MicroParser.Pre_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#parameter_declaration_repeat}.
	 * @param ctx the parse tree
	 */
	void enterParameter_declaration_repeat(@NotNull MicroParser.Parameter_declaration_repeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#parameter_declaration_repeat}.
	 * @param ctx the parse tree
	 */
	void exitParameter_declaration_repeat(@NotNull MicroParser.Parameter_declaration_repeatContext ctx);
}