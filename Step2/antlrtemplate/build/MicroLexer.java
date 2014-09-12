// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, KEYWORD=4, IDENTIFIER=5, OPERATOR=6, INTLITERAL=7, 
		FLOATLITERAL=8, SPACE=9, COMMENT=10;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'PROGRAM'", "'BEGIN'", "'END'", "KEYWORD", "IDENTIFIER", "OPERATOR", 
		"INTLITERAL", "FLOATLITERAL", "SPACE", "COMMENT"
	};
	public static final String[] ruleNames = {
		"T__2", "T__1", "T__0", "KEYWORD", "IDENTIFIER", "OPERATOR", "INTLITERAL", 
		"FLOATLITERAL", "SPACE", "COMMENT"
	};


	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

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
		case 8: SPACE_action((RuleContext)_localctx, actionIndex); break;

		case 9: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\f\u00ac\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5u\n\5\3\6\3\6\7\6y\n\6\f\6\16\6|\13\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0087\n\7\3\b\6\b\u008a\n\b\r"+
		"\b\16\b\u008b\3\t\7\t\u008f\n\t\f\t\16\t\u0092\13\t\3\t\3\t\7\t\u0096"+
		"\n\t\f\t\16\t\u0099\13\t\3\n\6\n\u009c\n\n\r\n\16\n\u009d\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\7\13\u00a6\n\13\f\13\16\13\u00a9\13\13\3\13\3\13\2\f\3"+
		"\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\2\25\f\3\3\2\t"+
		"\3\2C|\4\2\62;C|\5\2*/\61\61=@\3\2\62;\4\2))\60\60\5\2\13\f\16\17\"\""+
		"\4\2\f\f\17\17\u00c3\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\3\27\3\2\2\2\5\37\3\2\2\2\7%\3\2\2\2\tt\3\2\2\2\13v\3\2\2\2\r\u0086"+
		"\3\2\2\2\17\u0089\3\2\2\2\21\u0090\3\2\2\2\23\u009b\3\2\2\2\25\u00a1\3"+
		"\2\2\2\27\30\7R\2\2\30\31\7T\2\2\31\32\7Q\2\2\32\33\7I\2\2\33\34\7T\2"+
		"\2\34\35\7C\2\2\35\36\7O\2\2\36\4\3\2\2\2\37 \7D\2\2 !\7G\2\2!\"\7I\2"+
		"\2\"#\7K\2\2#$\7P\2\2$\6\3\2\2\2%&\7G\2\2&\'\7P\2\2\'(\7F\2\2(\b\3\2\2"+
		"\2)*\7R\2\2*+\7T\2\2+,\7Q\2\2,-\7I\2\2-.\7T\2\2./\7C\2\2/u\7O\2\2\60\61"+
		"\7D\2\2\61\62\7G\2\2\62\63\7I\2\2\63\64\7K\2\2\64u\7P\2\2\65\66\7U\2\2"+
		"\66\67\7V\2\2\678\7T\2\289\7K\2\29:\7P\2\2:u\7I\2\2;<\7H\2\2<=\7W\2\2"+
		"=>\7P\2\2>?\7E\2\2?@\7V\2\2@A\7K\2\2AB\7Q\2\2Bu\7P\2\2CD\7K\2\2DE\7P\2"+
		"\2Eu\7V\2\2FG\7K\2\2Gu\7H\2\2HI\7T\2\2IJ\7G\2\2JK\7V\2\2KL\7W\2\2LM\7"+
		"T\2\2Mu\7P\2\2NO\7G\2\2OP\7N\2\2PQ\7U\2\2Qu\7G\2\2RS\7G\2\2ST\7P\2\2T"+
		"U\7F\2\2UV\7K\2\2Vu\7H\2\2WX\7G\2\2XY\7P\2\2Yu\7F\2\2Z[\7X\2\2[\\\7Q\2"+
		"\2\\]\7K\2\2]u\7F\2\2^_\7Y\2\2_`\7T\2\2`a\7K\2\2ab\7V\2\2bu\7G\2\2cd\7"+
		"T\2\2de\7G\2\2ef\7C\2\2fu\7F\2\2gh\7Y\2\2hi\7J\2\2ij\7K\2\2jk\7N\2\2k"+
		"u\7G\2\2lm\7G\2\2mn\7P\2\2no\7F\2\2op\7Y\2\2pq\7J\2\2qr\7K\2\2rs\7N\2"+
		"\2su\7G\2\2t)\3\2\2\2t\60\3\2\2\2t\65\3\2\2\2t;\3\2\2\2tC\3\2\2\2tF\3"+
		"\2\2\2tH\3\2\2\2tN\3\2\2\2tR\3\2\2\2tW\3\2\2\2tZ\3\2\2\2t^\3\2\2\2tc\3"+
		"\2\2\2tg\3\2\2\2tl\3\2\2\2u\n\3\2\2\2vz\t\2\2\2wy\t\3\2\2xw\3\2\2\2y|"+
		"\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\f\3\2\2\2|z\3\2\2\2}\u0087\t\4\2\2~\177"+
		"\7<\2\2\177\u0087\7?\2\2\u0080\u0081\7#\2\2\u0081\u0087\7?\2\2\u0082\u0083"+
		"\7>\2\2\u0083\u0087\7?\2\2\u0084\u0085\7@\2\2\u0085\u0087\7?\2\2\u0086"+
		"}\3\2\2\2\u0086~\3\2\2\2\u0086\u0080\3\2\2\2\u0086\u0082\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0087\16\3\2\2\2\u0088\u008a\t\5\2\2\u0089\u0088\3\2\2"+
		"\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\20"+
		"\3\2\2\2\u008d\u008f\t\5\2\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0093\u0097\t\6\2\2\u0094\u0096\t\5\2\2\u0095\u0094\3\2\2\2\u0096"+
		"\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\22\3\2\2"+
		"\2\u0099\u0097\3\2\2\2\u009a\u009c\t\7\2\2\u009b\u009a\3\2\2\2\u009c\u009d"+
		"\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\b\n\2\2\u00a0\24\3\2\2\2\u00a1\u00a2\7/\2\2\u00a2\u00a3\7/\2\2"+
		"\u00a3\u00a7\3\2\2\2\u00a4\u00a6\n\b\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9"+
		"\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\u00ab\b\13\3\2\u00ab\26\3\2\2\2\13\2tz\u0086\u008b"+
		"\u0090\u0097\u009d\u00a7";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}