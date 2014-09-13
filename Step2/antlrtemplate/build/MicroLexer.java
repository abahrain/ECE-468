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
		T__2=1, T__1=2, T__0=3, IDENTIFIER=4, OPERATOR=5, INTLITERAL=6, FLOATLITERAL=7, 
		STRINGLITERAL=8, SPACE=9, COMMENT=10;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'PROGRAM'", "'BEGIN'", "'END'", "IDENTIFIER", "OPERATOR", "INTLITERAL", 
		"FLOATLITERAL", "STRINGLITERAL", "SPACE", "COMMENT"
	};
	public static final String[] ruleNames = {
		"T__2", "T__1", "T__0", "IDENTIFIER", "OPERATOR", "INTLITERAL", "FLOATLITERAL", 
		"STRINGLITERAL", "SPACE", "COMMENT"
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\fh\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\7\5,\n\5\f\5\16\5/\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\5\6:\n\6\3\7\6\7=\n\7\r\7\16\7>\3\b\7\bB\n\b\f\b\16\bE\13\b\3\b\3"+
		"\b\7\bI\n\b\f\b\16\bL\13\b\3\t\3\t\7\tP\n\t\f\t\16\tS\13\t\3\t\3\t\3\n"+
		"\6\nX\n\n\r\n\16\nY\3\n\3\n\3\13\3\13\3\13\3\13\7\13b\n\13\f\13\16\13"+
		"e\13\13\3\13\3\13\2\f\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n"+
		"\1\23\13\2\25\f\3\3\2\t\3\2C|\4\2\62;C|\5\2*/\61\61=@\3\2\62;\4\2))\60"+
		"\60\4\2\f\f\17\17\5\2\13\f\16\17\"\"r\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\37\3\2\2\2\7%\3\2\2\2\t)\3\2\2"+
		"\2\139\3\2\2\2\r<\3\2\2\2\17C\3\2\2\2\21M\3\2\2\2\23W\3\2\2\2\25]\3\2"+
		"\2\2\27\30\7R\2\2\30\31\7T\2\2\31\32\7Q\2\2\32\33\7I\2\2\33\34\7T\2\2"+
		"\34\35\7C\2\2\35\36\7O\2\2\36\4\3\2\2\2\37 \7D\2\2 !\7G\2\2!\"\7I\2\2"+
		"\"#\7K\2\2#$\7P\2\2$\6\3\2\2\2%&\7G\2\2&\'\7P\2\2\'(\7F\2\2(\b\3\2\2\2"+
		")-\t\2\2\2*,\t\3\2\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\n\3\2\2"+
		"\2/-\3\2\2\2\60:\t\4\2\2\61\62\7<\2\2\62:\7?\2\2\63\64\7#\2\2\64:\7?\2"+
		"\2\65\66\7>\2\2\66:\7?\2\2\678\7@\2\28:\7?\2\29\60\3\2\2\29\61\3\2\2\2"+
		"9\63\3\2\2\29\65\3\2\2\29\67\3\2\2\2:\f\3\2\2\2;=\t\5\2\2<;\3\2\2\2=>"+
		"\3\2\2\2><\3\2\2\2>?\3\2\2\2?\16\3\2\2\2@B\t\5\2\2A@\3\2\2\2BE\3\2\2\2"+
		"CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FJ\t\6\2\2GI\t\5\2\2HG\3\2\2\2"+
		"IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\20\3\2\2\2LJ\3\2\2\2MQ\7$\2\2NP\n\7\2"+
		"\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7$\2"+
		"\2U\22\3\2\2\2VX\t\b\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z[\3\2"+
		"\2\2[\\\b\n\2\2\\\24\3\2\2\2]^\7/\2\2^_\7/\2\2_c\3\2\2\2`b\n\7\2\2a`\3"+
		"\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\b\13\3\2g\26"+
		"\3\2\2\2\13\2-9>CJQYc";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}