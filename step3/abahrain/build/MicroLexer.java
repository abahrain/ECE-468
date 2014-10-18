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
		T__30=1, T__29=2, T__28=3, T__27=4, T__26=5, T__25=6, T__24=7, T__23=8, 
		T__22=9, T__21=10, T__20=11, T__19=12, T__18=13, T__17=14, T__16=15, T__15=16, 
		T__14=17, T__13=18, T__12=19, T__11=20, T__10=21, T__9=22, T__8=23, T__7=24, 
		T__6=25, T__5=26, T__4=27, T__3=28, T__2=29, T__1=30, T__0=31, KEYWORD=32, 
		IDENTIFIER=33, OPERATOR=34, INTLITERAL=35, FLOATLITERAL=36, STRINGLITERAL=37, 
		SPACE=38, COMMENT=39;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"','", "'*'", "'-'", "'PROGRAM'", "'('", "'READ'", "'<'", "'END'", "'!='", 
		"'<='", "'ELSE'", "'ENDIF'", "'VOID'", "'STRING'", "'IF'", "'WRITE'", 
		"')'", "'+'", "'WHILE'", "'='", "';'", "'FUNCTION'", "'>'", "'FLOAT'", 
		"':='", "'RETURN'", "'/'", "'BEGIN'", "'>='", "'INT'", "'ENDWHILE'", "KEYWORD", 
		"IDENTIFIER", "OPERATOR", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", 
		"SPACE", "COMMENT"
	};
	public static final String[] ruleNames = {
		"T__30", "T__29", "T__28", "T__27", "T__26", "T__25", "T__24", "T__23", 
		"T__22", "T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", 
		"T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", 
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "KEYWORD", "IDENTIFIER", 
		"OPERATOR", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", "SPACE", "COMMENT"
	};


		public MakeSymbolTable tree = new MakeSymbolTable();


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
		case 37: SPACE_action((RuleContext)_localctx, actionIndex); break;

		case 38: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2)\u0171\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\5!\u0131\n!\3\"\3\"\7\"\u0135\n\"\f\"\16\"\u0138\13\""+
		"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0143\n#\3$\6$\u0146\n$\r$\16$\u0147\3"+
		"%\7%\u014b\n%\f%\16%\u014e\13%\3%\3%\7%\u0152\n%\f%\16%\u0155\13%\3&\3"+
		"&\7&\u0159\n&\f&\16&\u015c\13&\3&\3&\3\'\6\'\u0161\n\'\r\'\16\'\u0162"+
		"\3\'\3\'\3(\3(\3(\3(\7(\u016b\n(\f(\16(\u016e\13(\3(\3(\2)\3\3\1\5\4\1"+
		"\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1"+
		"\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31"+
		"\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%"+
		"\1I&\1K\'\1M(\2O)\3\3\2\n\3\2C|\4\2\62;C|\5\2*/\61\61=@\3\2\62;\4\2))"+
		"\60\60\5\2\f\f\17\17$$\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u018c\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\3Q\3\2\2\2\5S\3\2\2\2\7U\3\2\2"+
		"\2\tW\3\2\2\2\13_\3\2\2\2\ra\3\2\2\2\17f\3\2\2\2\21h\3\2\2\2\23l\3\2\2"+
		"\2\25o\3\2\2\2\27r\3\2\2\2\31w\3\2\2\2\33}\3\2\2\2\35\u0082\3\2\2\2\37"+
		"\u0089\3\2\2\2!\u008c\3\2\2\2#\u0092\3\2\2\2%\u0094\3\2\2\2\'\u0096\3"+
		"\2\2\2)\u009c\3\2\2\2+\u009e\3\2\2\2-\u00a0\3\2\2\2/\u00a9\3\2\2\2\61"+
		"\u00ab\3\2\2\2\63\u00b1\3\2\2\2\65\u00b4\3\2\2\2\67\u00bb\3\2\2\29\u00bd"+
		"\3\2\2\2;\u00c3\3\2\2\2=\u00c6\3\2\2\2?\u00ca\3\2\2\2A\u0130\3\2\2\2C"+
		"\u0132\3\2\2\2E\u0142\3\2\2\2G\u0145\3\2\2\2I\u014c\3\2\2\2K\u0156\3\2"+
		"\2\2M\u0160\3\2\2\2O\u0166\3\2\2\2QR\7.\2\2R\4\3\2\2\2ST\7,\2\2T\6\3\2"+
		"\2\2UV\7/\2\2V\b\3\2\2\2WX\7R\2\2XY\7T\2\2YZ\7Q\2\2Z[\7I\2\2[\\\7T\2\2"+
		"\\]\7C\2\2]^\7O\2\2^\n\3\2\2\2_`\7*\2\2`\f\3\2\2\2ab\7T\2\2bc\7G\2\2c"+
		"d\7C\2\2de\7F\2\2e\16\3\2\2\2fg\7>\2\2g\20\3\2\2\2hi\7G\2\2ij\7P\2\2j"+
		"k\7F\2\2k\22\3\2\2\2lm\7#\2\2mn\7?\2\2n\24\3\2\2\2op\7>\2\2pq\7?\2\2q"+
		"\26\3\2\2\2rs\7G\2\2st\7N\2\2tu\7U\2\2uv\7G\2\2v\30\3\2\2\2wx\7G\2\2x"+
		"y\7P\2\2yz\7F\2\2z{\7K\2\2{|\7H\2\2|\32\3\2\2\2}~\7X\2\2~\177\7Q\2\2\177"+
		"\u0080\7K\2\2\u0080\u0081\7F\2\2\u0081\34\3\2\2\2\u0082\u0083\7U\2\2\u0083"+
		"\u0084\7V\2\2\u0084\u0085\7T\2\2\u0085\u0086\7K\2\2\u0086\u0087\7P\2\2"+
		"\u0087\u0088\7I\2\2\u0088\36\3\2\2\2\u0089\u008a\7K\2\2\u008a\u008b\7"+
		"H\2\2\u008b \3\2\2\2\u008c\u008d\7Y\2\2\u008d\u008e\7T\2\2\u008e\u008f"+
		"\7K\2\2\u008f\u0090\7V\2\2\u0090\u0091\7G\2\2\u0091\"\3\2\2\2\u0092\u0093"+
		"\7+\2\2\u0093$\3\2\2\2\u0094\u0095\7-\2\2\u0095&\3\2\2\2\u0096\u0097\7"+
		"Y\2\2\u0097\u0098\7J\2\2\u0098\u0099\7K\2\2\u0099\u009a\7N\2\2\u009a\u009b"+
		"\7G\2\2\u009b(\3\2\2\2\u009c\u009d\7?\2\2\u009d*\3\2\2\2\u009e\u009f\7"+
		"=\2\2\u009f,\3\2\2\2\u00a0\u00a1\7H\2\2\u00a1\u00a2\7W\2\2\u00a2\u00a3"+
		"\7P\2\2\u00a3\u00a4\7E\2\2\u00a4\u00a5\7V\2\2\u00a5\u00a6\7K\2\2\u00a6"+
		"\u00a7\7Q\2\2\u00a7\u00a8\7P\2\2\u00a8.\3\2\2\2\u00a9\u00aa\7@\2\2\u00aa"+
		"\60\3\2\2\2\u00ab\u00ac\7H\2\2\u00ac\u00ad\7N\2\2\u00ad\u00ae\7Q\2\2\u00ae"+
		"\u00af\7C\2\2\u00af\u00b0\7V\2\2\u00b0\62\3\2\2\2\u00b1\u00b2\7<\2\2\u00b2"+
		"\u00b3\7?\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\7T\2\2\u00b5\u00b6\7G\2\2\u00b6"+
		"\u00b7\7V\2\2\u00b7\u00b8\7W\2\2\u00b8\u00b9\7T\2\2\u00b9\u00ba\7P\2\2"+
		"\u00ba\66\3\2\2\2\u00bb\u00bc\7\61\2\2\u00bc8\3\2\2\2\u00bd\u00be\7D\2"+
		"\2\u00be\u00bf\7G\2\2\u00bf\u00c0\7I\2\2\u00c0\u00c1\7K\2\2\u00c1\u00c2"+
		"\7P\2\2\u00c2:\3\2\2\2\u00c3\u00c4\7@\2\2\u00c4\u00c5\7?\2\2\u00c5<\3"+
		"\2\2\2\u00c6\u00c7\7K\2\2\u00c7\u00c8\7P\2\2\u00c8\u00c9\7V\2\2\u00c9"+
		">\3\2\2\2\u00ca\u00cb\7G\2\2\u00cb\u00cc\7P\2\2\u00cc\u00cd\7F\2\2\u00cd"+
		"\u00ce\7Y\2\2\u00ce\u00cf\7J\2\2\u00cf\u00d0\7K\2\2\u00d0\u00d1\7N\2\2"+
		"\u00d1\u00d2\7G\2\2\u00d2@\3\2\2\2\u00d3\u00d4\7R\2\2\u00d4\u00d5\7T\2"+
		"\2\u00d5\u00d6\7Q\2\2\u00d6\u00d7\7I\2\2\u00d7\u00d8\7T\2\2\u00d8\u00d9"+
		"\7C\2\2\u00d9\u0131\7O\2\2\u00da\u00db\7D\2\2\u00db\u00dc\7G\2\2\u00dc"+
		"\u00dd\7I\2\2\u00dd\u00de\7K\2\2\u00de\u0131\7P\2\2\u00df\u00e0\7U\2\2"+
		"\u00e0\u00e1\7V\2\2\u00e1\u00e2\7T\2\2\u00e2\u00e3\7K\2\2\u00e3\u00e4"+
		"\7P\2\2\u00e4\u0131\7I\2\2\u00e5\u00e6\7H\2\2\u00e6\u00e7\7W\2\2\u00e7"+
		"\u00e8\7P\2\2\u00e8\u00e9\7E\2\2\u00e9\u00ea\7V\2\2\u00ea\u00eb\7K\2\2"+
		"\u00eb\u00ec\7Q\2\2\u00ec\u0131\7P\2\2\u00ed\u00ee\7K\2\2\u00ee\u00ef"+
		"\7P\2\2\u00ef\u0131\7V\2\2\u00f0\u00f1\7K\2\2\u00f1\u0131\7H\2\2\u00f2"+
		"\u00f3\7T\2\2\u00f3\u00f4\7G\2\2\u00f4\u00f5\7V\2\2\u00f5\u00f6\7W\2\2"+
		"\u00f6\u00f7\7T\2\2\u00f7\u0131\7P\2\2\u00f8\u00f9\7G\2\2\u00f9\u00fa"+
		"\7N\2\2\u00fa\u00fb\7U\2\2\u00fb\u0131\7G\2\2\u00fc\u00fd\7G\2\2\u00fd"+
		"\u00fe\7P\2\2\u00fe\u00ff\7F\2\2\u00ff\u0100\7K\2\2\u0100\u0131\7H\2\2"+
		"\u0101\u0102\7G\2\2\u0102\u0103\7P\2\2\u0103\u0131\7F\2\2\u0104\u0105"+
		"\7X\2\2\u0105\u0106\7Q\2\2\u0106\u0107\7K\2\2\u0107\u0131\7F\2\2\u0108"+
		"\u0109\7Y\2\2\u0109\u010a\7T\2\2\u010a\u010b\7K\2\2\u010b\u010c\7V\2\2"+
		"\u010c\u0131\7G\2\2\u010d\u010e\7T\2\2\u010e\u010f\7G\2\2\u010f\u0110"+
		"\7C\2\2\u0110\u0131\7F\2\2\u0111\u0112\7Y\2\2\u0112\u0113\7J\2\2\u0113"+
		"\u0114\7K\2\2\u0114\u0115\7N\2\2\u0115\u0131\7G\2\2\u0116\u0117\7G\2\2"+
		"\u0117\u0118\7P\2\2\u0118\u0119\7F\2\2\u0119\u011a\7Y\2\2\u011a\u011b"+
		"\7J\2\2\u011b\u011c\7K\2\2\u011c\u011d\7N\2\2\u011d\u0131\7G\2\2\u011e"+
		"\u011f\7H\2\2\u011f\u0120\7N\2\2\u0120\u0121\7Q\2\2\u0121\u0122\7C\2\2"+
		"\u0122\u0131\7V\2\2\u0123\u0124\7E\2\2\u0124\u0125\7Q\2\2\u0125\u0126"+
		"\7P\2\2\u0126\u0127\7V\2\2\u0127\u0128\7K\2\2\u0128\u0129\7P\2\2\u0129"+
		"\u012a\7W\2\2\u012a\u0131\7G\2\2\u012b\u012c\7D\2\2\u012c\u012d\7T\2\2"+
		"\u012d\u012e\7G\2\2\u012e\u012f\7C\2\2\u012f\u0131\7M\2\2\u0130\u00d3"+
		"\3\2\2\2\u0130\u00da\3\2\2\2\u0130\u00df\3\2\2\2\u0130\u00e5\3\2\2\2\u0130"+
		"\u00ed\3\2\2\2\u0130\u00f0\3\2\2\2\u0130\u00f2\3\2\2\2\u0130\u00f8\3\2"+
		"\2\2\u0130\u00fc\3\2\2\2\u0130\u0101\3\2\2\2\u0130\u0104\3\2\2\2\u0130"+
		"\u0108\3\2\2\2\u0130\u010d\3\2\2\2\u0130\u0111\3\2\2\2\u0130\u0116\3\2"+
		"\2\2\u0130\u011e\3\2\2\2\u0130\u0123\3\2\2\2\u0130\u012b\3\2\2\2\u0131"+
		"B\3\2\2\2\u0132\u0136\t\2\2\2\u0133\u0135\t\3\2\2\u0134\u0133\3\2\2\2"+
		"\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137D\3"+
		"\2\2\2\u0138\u0136\3\2\2\2\u0139\u0143\t\4\2\2\u013a\u013b\7<\2\2\u013b"+
		"\u0143\7?\2\2\u013c\u013d\7#\2\2\u013d\u0143\7?\2\2\u013e\u013f\7>\2\2"+
		"\u013f\u0143\7?\2\2\u0140\u0141\7@\2\2\u0141\u0143\7?\2\2\u0142\u0139"+
		"\3\2\2\2\u0142\u013a\3\2\2\2\u0142\u013c\3\2\2\2\u0142\u013e\3\2\2\2\u0142"+
		"\u0140\3\2\2\2\u0143F\3\2\2\2\u0144\u0146\t\5\2\2\u0145\u0144\3\2\2\2"+
		"\u0146\u0147\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148H\3"+
		"\2\2\2\u0149\u014b\t\5\2\2\u014a\u0149\3\2\2\2\u014b\u014e\3\2\2\2\u014c"+
		"\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2\2\2\u014e\u014c\3\2"+
		"\2\2\u014f\u0153\t\6\2\2\u0150\u0152\t\5\2\2\u0151\u0150\3\2\2\2\u0152"+
		"\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154J\3\2\2\2"+
		"\u0155\u0153\3\2\2\2\u0156\u015a\7$\2\2\u0157\u0159\n\7\2\2\u0158\u0157"+
		"\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b"+
		"\u015d\3\2\2\2\u015c\u015a\3\2\2\2\u015d\u015e\7$\2\2\u015eL\3\2\2\2\u015f"+
		"\u0161\t\b\2\2\u0160\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0160\3\2"+
		"\2\2\u0162\u0163\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165\b\'\2\2\u0165"+
		"N\3\2\2\2\u0166\u0167\7/\2\2\u0167\u0168\7/\2\2\u0168\u016c\3\2\2\2\u0169"+
		"\u016b\n\t\2\2\u016a\u0169\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2"+
		"\2\2\u016c\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2\2\2\u016f"+
		"\u0170\b(\3\2\u0170P\3\2\2\2\f\2\u0130\u0136\u0142\u0147\u014c\u0153\u015a"+
		"\u0162\u016c";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}