// Generated from Micro_scan.g4 by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Micro_scan extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KEYWORD=1, IDENTIFIER=2, OPERATOR=3, STRINGLITERAL=4, INTLITERAL=5, FLOATLITERAL=6, 
		SPACE=7, COMMENT=8;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"KEYWORD", "IDENTIFIER", "OPERATOR", "STRINGLITERAL", "INTLITERAL", "FLOATLITERAL", 
		"SPACE", "COMMENT"
	};
	public static final String[] ruleNames = {
		"KEYWORD", "IDENTIFIER", "OPERATOR", "STRINGLITERAL", "INTLITERAL", "FLOATLITERAL", 
		"SPACE", "COMMENT"
	};


	public Micro_scan(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro_scan.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 6: SPACE_action((RuleContext)_localctx, actionIndex); break;

		case 7: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void SPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\n\u00b1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\5\2q\n\2\3\3\3\3\7\3u\n\3\f\3\16\3x\13\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\4\u0083\n\4\3\5\3\5\7\5\u0087\n\5\f\5\16\5\u008a"+
		"\13\5\3\5\3\5\3\6\6\6\u008f\n\6\r\6\16\6\u0090\3\7\7\7\u0094\n\7\f\7\16"+
		"\7\u0097\13\7\3\7\3\7\7\7\u009b\n\7\f\7\16\7\u009e\13\7\3\b\6\b\u00a1"+
		"\n\b\r\b\16\b\u00a2\3\b\3\b\3\t\3\t\3\t\3\t\7\t\u00ab\n\t\f\t\16\t\u00ae"+
		"\13\t\3\t\3\t\2\n\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\2\21\n\3\3"+
		"\2\t\3\2C|\4\2\62;C|\5\2*/\61\61=@\4\2\f\f\17\17\3\2\62;\4\2))\60\60\5"+
		"\2\13\f\16\17\"\"\u00cc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3p\3\2\2\2\5r\3"+
		"\2\2\2\7\u0082\3\2\2\2\t\u0084\3\2\2\2\13\u008e\3\2\2\2\r\u0095\3\2\2"+
		"\2\17\u00a0\3\2\2\2\21\u00a6\3\2\2\2\23\24\7R\2\2\24\25\7T\2\2\25\26\7"+
		"Q\2\2\26\27\7I\2\2\27\30\7T\2\2\30\31\7C\2\2\31q\7O\2\2\32\33\7D\2\2\33"+
		"\34\7G\2\2\34\35\7I\2\2\35\36\7K\2\2\36q\7P\2\2\37 \7U\2\2 !\7V\2\2!\""+
		"\7T\2\2\"#\7K\2\2#$\7P\2\2$q\7I\2\2%&\7H\2\2&\'\7W\2\2\'(\7P\2\2()\7E"+
		"\2\2)*\7V\2\2*+\7K\2\2+,\7Q\2\2,q\7P\2\2-.\7K\2\2./\7P\2\2/q\7V\2\2\60"+
		"\61\7K\2\2\61q\7H\2\2\62\63\7T\2\2\63\64\7G\2\2\64\65\7V\2\2\65\66\7W"+
		"\2\2\66\67\7T\2\2\67q\7P\2\289\7G\2\29:\7N\2\2:;\7U\2\2;q\7G\2\2<=\7G"+
		"\2\2=>\7P\2\2>?\7F\2\2?@\7K\2\2@q\7H\2\2AB\7G\2\2BC\7P\2\2Cq\7F\2\2DE"+
		"\7X\2\2EF\7Q\2\2FG\7K\2\2Gq\7F\2\2HI\7Y\2\2IJ\7T\2\2JK\7K\2\2KL\7V\2\2"+
		"Lq\7G\2\2MN\7T\2\2NO\7G\2\2OP\7C\2\2Pq\7F\2\2QR\7Y\2\2RS\7J\2\2ST\7K\2"+
		"\2TU\7N\2\2Uq\7G\2\2VW\7G\2\2WX\7P\2\2XY\7F\2\2YZ\7Y\2\2Z[\7J\2\2[\\\7"+
		"K\2\2\\]\7N\2\2]q\7G\2\2^_\7H\2\2_`\7N\2\2`a\7Q\2\2ab\7C\2\2bq\7V\2\2"+
		"cd\7E\2\2de\7Q\2\2ef\7P\2\2fg\7V\2\2gh\7K\2\2hi\7P\2\2ij\7W\2\2jq\7G\2"+
		"\2kl\7D\2\2lm\7T\2\2mn\7G\2\2no\7C\2\2oq\7M\2\2p\23\3\2\2\2p\32\3\2\2"+
		"\2p\37\3\2\2\2p%\3\2\2\2p-\3\2\2\2p\60\3\2\2\2p\62\3\2\2\2p8\3\2\2\2p"+
		"<\3\2\2\2pA\3\2\2\2pD\3\2\2\2pH\3\2\2\2pM\3\2\2\2pQ\3\2\2\2pV\3\2\2\2"+
		"p^\3\2\2\2pc\3\2\2\2pk\3\2\2\2q\4\3\2\2\2rv\t\2\2\2su\t\3\2\2ts\3\2\2"+
		"\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\6\3\2\2\2xv\3\2\2\2y\u0083\t\4\2\2z"+
		"{\7<\2\2{\u0083\7?\2\2|}\7#\2\2}\u0083\7?\2\2~\177\7>\2\2\177\u0083\7"+
		"?\2\2\u0080\u0081\7@\2\2\u0081\u0083\7?\2\2\u0082y\3\2\2\2\u0082z\3\2"+
		"\2\2\u0082|\3\2\2\2\u0082~\3\2\2\2\u0082\u0080\3\2\2\2\u0083\b\3\2\2\2"+
		"\u0084\u0088\7$\2\2\u0085\u0087\n\5\2\2\u0086\u0085\3\2\2\2\u0087\u008a"+
		"\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008b\u008c\7$\2\2\u008c\n\3\2\2\2\u008d\u008f\t\6\2\2"+
		"\u008e\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091"+
		"\3\2\2\2\u0091\f\3\2\2\2\u0092\u0094\t\6\2\2\u0093\u0092\3\2\2\2\u0094"+
		"\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0098\u009c\t\7\2\2\u0099\u009b\t\6\2\2\u009a"+
		"\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\16\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a1\t\b\2\2\u00a0\u009f"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a5\b\b\2\2\u00a5\20\3\2\2\2\u00a6\u00a7\7/\2\2"+
		"\u00a7\u00a8\7/\2\2\u00a8\u00ac\3\2\2\2\u00a9\u00ab\n\5\2\2\u00aa\u00a9"+
		"\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\b\t\3\2\u00b0\22\3\2\2"+
		"\2\f\2pv\u0082\u0088\u0090\u0095\u009c\u00a2\u00ac";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}