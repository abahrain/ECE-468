// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MicroParser}.
 */
public interface MicroListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MicroParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void enterPgm_body(@NotNull MicroParser.Pgm_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void exitPgm_body(@NotNull MicroParser.Pgm_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull MicroParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull MicroParser.IdContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#valid}.
	 * @param ctx the parse tree
	 */
	void enterValid(@NotNull MicroParser.ValidContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#valid}.
	 * @param ctx the parse tree
	 */
	void exitValid(@NotNull MicroParser.ValidContext ctx);
}