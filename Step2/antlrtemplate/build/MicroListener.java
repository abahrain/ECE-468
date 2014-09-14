// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MicroParser}.
 */
public interface MicroListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link MicroParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(@NotNull MicroParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(@NotNull MicroParser.IntegerContext ctx);

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
	 * Enter a parse tree produced by {@link MicroParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void enterElse_statement(@NotNull MicroParser.Else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void exitElse_statement(@NotNull MicroParser.Else_statementContext ctx);

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
	 * Enter a parse tree produced by {@link MicroParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(@NotNull MicroParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(@NotNull MicroParser.OperationContext ctx);

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
	 * Enter a parse tree produced by {@link MicroParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void enterWhile_loop(@NotNull MicroParser.While_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void exitWhile_loop(@NotNull MicroParser.While_loopContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(@NotNull MicroParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(@NotNull MicroParser.KeyContext ctx);
}