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
		KEYWORD=1, IDENTIFIER=2, OPERATOR=3, INTLITERAL=4, FLOATLITERAL=5, SPACE=6, 
		COMMENT=7;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"KEYWORD", "IDENTIFIER", "OPERATOR", "INTLITERAL", "FLOATLITERAL", "SPACE", 
		"COMMENT"
	};
	public static final String[] ruleNames = {
		"KEYWORD", "IDENTIFIER", "OPERATOR", "INTLITERAL", "FLOATLITERAL", "SPACE", 
		"COMMENT"
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
		case 5: SPACE_action((RuleContext)_localctx, actionIndex); break;

		case 6: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\t\u0094\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\5\2]\n\2\3\3\3\3\7\3a\n\3\f\3\16\3d\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4o\n\4\3\5\6\5r\n\5\r\5\16\5s\3\6\7\6w\n\6\f\6\16\6z\13\6\3"+
		"\6\3\6\7\6~\n\6\f\6\16\6\u0081\13\6\3\7\6\7\u0084\n\7\r\7\16\7\u0085\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\7\b\u008e\n\b\f\b\16\b\u0091\13\b\3\b\3\b\2\t\3"+
		"\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\2\17\t\3\3\2\t\3\2C|\4\2\62;C|\5\2*"+
		"/\61\61=@\3\2\62;\4\2))\60\60\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u00ab\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\3\\\3\2\2\2\5^\3\2\2\2\7n\3\2\2\2\tq\3\2\2\2\13x\3\2\2"+
		"\2\r\u0083\3\2\2\2\17\u0089\3\2\2\2\21\22\7R\2\2\22\23\7T\2\2\23\24\7"+
		"Q\2\2\24\25\7I\2\2\25\26\7T\2\2\26\27\7C\2\2\27]\7O\2\2\30\31\7D\2\2\31"+
		"\32\7G\2\2\32\33\7I\2\2\33\34\7K\2\2\34]\7P\2\2\35\36\7U\2\2\36\37\7V"+
		"\2\2\37 \7T\2\2 !\7K\2\2!\"\7P\2\2\"]\7I\2\2#$\7H\2\2$%\7W\2\2%&\7P\2"+
		"\2&\'\7E\2\2\'(\7V\2\2()\7K\2\2)*\7Q\2\2*]\7P\2\2+,\7K\2\2,-\7P\2\2-]"+
		"\7V\2\2./\7K\2\2/]\7H\2\2\60\61\7T\2\2\61\62\7G\2\2\62\63\7V\2\2\63\64"+
		"\7W\2\2\64\65\7T\2\2\65]\7P\2\2\66\67\7G\2\2\678\7N\2\289\7U\2\29]\7G"+
		"\2\2:;\7G\2\2;<\7P\2\2<=\7F\2\2=>\7K\2\2>]\7H\2\2?@\7G\2\2@A\7P\2\2A]"+
		"\7F\2\2BC\7X\2\2CD\7Q\2\2DE\7K\2\2E]\7F\2\2FG\7Y\2\2GH\7T\2\2HI\7K\2\2"+
		"IJ\7V\2\2J]\7G\2\2KL\7T\2\2LM\7G\2\2MN\7C\2\2N]\7F\2\2OP\7Y\2\2PQ\7J\2"+
		"\2QR\7K\2\2RS\7N\2\2S]\7G\2\2TU\7G\2\2UV\7P\2\2VW\7F\2\2WX\7Y\2\2XY\7"+
		"J\2\2YZ\7K\2\2Z[\7N\2\2[]\7G\2\2\\\21\3\2\2\2\\\30\3\2\2\2\\\35\3\2\2"+
		"\2\\#\3\2\2\2\\+\3\2\2\2\\.\3\2\2\2\\\60\3\2\2\2\\\66\3\2\2\2\\:\3\2\2"+
		"\2\\?\3\2\2\2\\B\3\2\2\2\\F\3\2\2\2\\K\3\2\2\2\\O\3\2\2\2\\T\3\2\2\2]"+
		"\4\3\2\2\2^b\t\2\2\2_a\t\3\2\2`_\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2"+
		"c\6\3\2\2\2db\3\2\2\2eo\t\4\2\2fg\7<\2\2go\7?\2\2hi\7#\2\2io\7?\2\2jk"+
		"\7>\2\2ko\7?\2\2lm\7@\2\2mo\7?\2\2ne\3\2\2\2nf\3\2\2\2nh\3\2\2\2nj\3\2"+
		"\2\2nl\3\2\2\2o\b\3\2\2\2pr\t\5\2\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3"+
		"\2\2\2t\n\3\2\2\2uw\t\5\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y{"+
		"\3\2\2\2zx\3\2\2\2{\177\t\6\2\2|~\t\5\2\2}|\3\2\2\2~\u0081\3\2\2\2\177"+
		"}\3\2\2\2\177\u0080\3\2\2\2\u0080\f\3\2\2\2\u0081\177\3\2\2\2\u0082\u0084"+
		"\t\7\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\7\2\2\u0088\16\3\2\2"+
		"\2\u0089\u008a\7/\2\2\u008a\u008b\7/\2\2\u008b\u008f\3\2\2\2\u008c\u008e"+
		"\n\b\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0093\b\b"+
		"\3\2\u0093\20\3\2\2\2\13\2\\bnsx\177\u0085\u008f";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}