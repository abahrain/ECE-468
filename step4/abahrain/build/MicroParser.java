// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroParser extends Parser {
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
	public static final String[] tokenNames = {
		"<INVALID>", "','", "'*'", "'-'", "'PROGRAM'", "'('", "'READ'", "'<'", 
		"'END'", "'!='", "'<='", "'ELSE'", "'ENDIF'", "'VOID'", "'STRING'", "'IF'", 
		"'WRITE'", "')'", "'+'", "'WHILE'", "'='", "';'", "'FUNCTION'", "'>'", 
		"'FLOAT'", "':='", "'RETURN'", "'/'", "'BEGIN'", "'>='", "'INT'", "'ENDWHILE'", 
		"KEYWORD", "IDENTIFIER", "OPERATOR", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", 
		"SPACE", "COMMENT"
	};
	public static final int
		RULE_program = 0, RULE_name = 1, RULE_program_body = 2, RULE_declaration = 3, 
		RULE_string_declaration_list = 4, RULE_string = 5, RULE_variable_declaration_list = 6, 
		RULE_variable_type = 7, RULE_any_type = 8, RULE_name_list = 9, RULE_name_repeat = 10, 
		RULE_parameter_declaration_list = 11, RULE_parameter_declaration = 12, 
		RULE_parameter_declaration_repeat = 13, RULE_function = 14, RULE_function_declaration = 15, 
		RULE_function_body = 16, RULE_statement_list = 17, RULE_statement = 18, 
		RULE_basic_statement = 19, RULE_assignment = 20, RULE_assignment_frame = 21, 
		RULE_read = 22, RULE_write = 23, RULE_re_turn = 24, RULE_expression = 25, 
		RULE_pre_expression = 26, RULE_factor = 27, RULE_pre_factor = 28, RULE_post_expression = 29, 
		RULE_expression_call = 30, RULE_expression_list = 31, RULE_expression_list_repeat = 32, 
		RULE_primary = 33, RULE_addition_operation = 34, RULE_multiplication_operation = 35, 
		RULE_if_statement = 36, RULE_else_portion = 37, RULE_while_statement = 38, 
		RULE_condition = 39, RULE_comparison_operator = 40;
	public static final String[] ruleNames = {
		"program", "name", "program_body", "declaration", "string_declaration_list", 
		"string", "variable_declaration_list", "variable_type", "any_type", "name_list", 
		"name_repeat", "parameter_declaration_list", "parameter_declaration", 
		"parameter_declaration_repeat", "function", "function_declaration", "function_body", 
		"statement_list", "statement", "basic_statement", "assignment", "assignment_frame", 
		"read", "write", "re_turn", "expression", "pre_expression", "factor", 
		"pre_factor", "post_expression", "expression_call", "expression_list", 
		"expression_list_repeat", "primary", "addition_operation", "multiplication_operation", 
		"if_statement", "else_portion", "while_statement", "condition", "comparison_operator"
	};

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


		public MakeSymbolTable tree = new MakeSymbolTable();

	public MicroParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public Program_bodyContext program_body() {
			return getRuleContext(Program_bodyContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(4);
			setState(83); name();
			setState(84); match(28);
			setState(85); program_body();
			setState(86); match(8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public String identifier;
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(MicroParser.IDENTIFIER, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); ((NameContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			((NameContext)_localctx).identifier =  (String)(((NameContext)_localctx).IDENTIFIER!=null?((NameContext)_localctx).IDENTIFIER.getText():null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Program_bodyContext extends ParserRuleContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public Program_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterProgram_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitProgram_body(this);
		}
	}

	public final Program_bodyContext program_body() throws RecognitionException {
		Program_bodyContext _localctx = new Program_bodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_program_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); declaration();
			setState(92); function();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public List<String_declaration_listContext> string_declaration_list() {
			return getRuleContexts(String_declaration_listContext.class);
		}
		public Variable_declaration_listContext variable_declaration_list(int i) {
			return getRuleContext(Variable_declaration_listContext.class,i);
		}
		public String_declaration_listContext string_declaration_list(int i) {
			return getRuleContext(String_declaration_listContext.class,i);
		}
		public List<Variable_declaration_listContext> variable_declaration_list() {
			return getRuleContexts(Variable_declaration_listContext.class);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 14) | (1L << 24) | (1L << 30))) != 0)) {
				{
				setState(96);
				switch (_input.LA(1)) {
				case 14:
					{
					setState(94); string_declaration_list();
					}
					break;
				case 24:
				case 30:
					{
					setState(95); variable_declaration_list();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_declaration_listContext extends ParserRuleContext {
		public NameContext name;
		public StringContext string;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public String_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_declaration_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterString_declaration_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitString_declaration_list(this);
		}
	}

	public final String_declaration_listContext string_declaration_list() throws RecognitionException {
		String_declaration_listContext _localctx = new String_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_string_declaration_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(14);
			setState(102); ((String_declaration_listContext)_localctx).name = name();
			setState(103); match(25);
			setState(104); ((String_declaration_listContext)_localctx).string = string();
			setState(105); match(21);
			tree.insertString((((String_declaration_listContext)_localctx).name!=null?_input.getText(((String_declaration_listContext)_localctx).name.start,((String_declaration_listContext)_localctx).name.stop):null),(((String_declaration_listContext)_localctx).string!=null?_input.getText(((String_declaration_listContext)_localctx).string.start,((String_declaration_listContext)_localctx).string.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(MicroParser.STRINGLITERAL, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); match(STRINGLITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_declaration_listContext extends ParserRuleContext {
		public Variable_typeContext variable_type;
		public Name_listContext name_list;
		public Name_listContext name_list() {
			return getRuleContext(Name_listContext.class,0);
		}
		public Variable_typeContext variable_type() {
			return getRuleContext(Variable_typeContext.class,0);
		}
		public Variable_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVariable_declaration_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVariable_declaration_list(this);
		}
	}

	public final Variable_declaration_listContext variable_declaration_list() throws RecognitionException {
		Variable_declaration_listContext _localctx = new Variable_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable_declaration_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); ((Variable_declaration_listContext)_localctx).variable_type = variable_type();
			setState(111); ((Variable_declaration_listContext)_localctx).name_list = name_list();
			setState(112); match(21);
			tree.addListOfVariables(((Variable_declaration_listContext)_localctx).name_list.names,(((Variable_declaration_listContext)_localctx).variable_type!=null?_input.getText(((Variable_declaration_listContext)_localctx).variable_type.start,((Variable_declaration_listContext)_localctx).variable_type.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_typeContext extends ParserRuleContext {
		public Variable_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVariable_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVariable_type(this);
		}
	}

	public final Variable_typeContext variable_type() throws RecognitionException {
		Variable_typeContext _localctx = new Variable_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variable_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_la = _input.LA(1);
			if ( !(_la==24 || _la==30) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Any_typeContext extends ParserRuleContext {
		public Variable_typeContext variable_type() {
			return getRuleContext(Variable_typeContext.class,0);
		}
		public Any_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAny_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAny_type(this);
		}
	}

	public final Any_typeContext any_type() throws RecognitionException {
		Any_typeContext _localctx = new Any_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_any_type);
		try {
			setState(119);
			switch (_input.LA(1)) {
			case 24:
			case 30:
				enterOuterAlt(_localctx, 1);
				{
				setState(117); variable_type();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 2);
				{
				setState(118); match(13);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Name_listContext extends ParserRuleContext {
		public ArrayList<String> names;
		public NameContext name;
		public Name_repeatContext name_repeat;
		public Name_repeatContext name_repeat() {
			return getRuleContext(Name_repeatContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Name_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterName_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitName_list(this);
		}
	}

	public final Name_listContext name_list() throws RecognitionException {
		Name_listContext _localctx = new Name_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_name_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); ((Name_listContext)_localctx).name = name();
			setState(122); ((Name_listContext)_localctx).name_repeat = name_repeat();
			((Name_listContext)_localctx).names =  ((Name_listContext)_localctx).name_repeat.names_list; _localctx.names.add(0,(((Name_listContext)_localctx).name!=null?_input.getText(((Name_listContext)_localctx).name.start,((Name_listContext)_localctx).name.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Name_repeatContext extends ParserRuleContext {
		public ArrayList<String> names_list;
		public NameContext name;
		public Name_repeatContext named_list;
		public Name_repeatContext name_repeat() {
			return getRuleContext(Name_repeatContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Name_repeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_repeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterName_repeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitName_repeat(this);
		}
	}

	public final Name_repeatContext name_repeat() throws RecognitionException {
		Name_repeatContext _localctx = new Name_repeatContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_name_repeat);
		try {
			setState(131);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125); match(1);
				setState(126); ((Name_repeatContext)_localctx).name = name();
				setState(127); ((Name_repeatContext)_localctx).named_list = name_repeat();
				((Name_repeatContext)_localctx).names_list =  ((Name_repeatContext)_localctx).named_list.names_list; _localctx.names_list.add(0,(((Name_repeatContext)_localctx).name!=null?_input.getText(((Name_repeatContext)_localctx).name.start,((Name_repeatContext)_localctx).name.stop):null));
				}
				break;
			case 17:
			case 21:
				enterOuterAlt(_localctx, 2);
				{
				((Name_repeatContext)_localctx).names_list =  new ArrayList<String>();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_declaration_listContext extends ParserRuleContext {
		public Parameter_declaration_repeatContext parameter_declaration_repeat() {
			return getRuleContext(Parameter_declaration_repeatContext.class,0);
		}
		public Parameter_declarationContext parameter_declaration() {
			return getRuleContext(Parameter_declarationContext.class,0);
		}
		public Parameter_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_declaration_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParameter_declaration_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParameter_declaration_list(this);
		}
	}

	public final Parameter_declaration_listContext parameter_declaration_list() throws RecognitionException {
		Parameter_declaration_listContext _localctx = new Parameter_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_parameter_declaration_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_la = _input.LA(1);
			if (_la==24 || _la==30) {
				{
				setState(133); parameter_declaration();
				setState(134); parameter_declaration_repeat();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_declarationContext extends ParserRuleContext {
		public Variable_typeContext variable_type;
		public NameContext name;
		public Variable_typeContext variable_type() {
			return getRuleContext(Variable_typeContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Parameter_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParameter_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParameter_declaration(this);
		}
	}

	public final Parameter_declarationContext parameter_declaration() throws RecognitionException {
		Parameter_declarationContext _localctx = new Parameter_declarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parameter_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); ((Parameter_declarationContext)_localctx).variable_type = variable_type();
			setState(139); ((Parameter_declarationContext)_localctx).name = name();
			tree.addSingleVariable((((Parameter_declarationContext)_localctx).name!=null?_input.getText(((Parameter_declarationContext)_localctx).name.start,((Parameter_declarationContext)_localctx).name.stop):null),(((Parameter_declarationContext)_localctx).variable_type!=null?_input.getText(((Parameter_declarationContext)_localctx).variable_type.start,((Parameter_declarationContext)_localctx).variable_type.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_declaration_repeatContext extends ParserRuleContext {
		public List<Parameter_declaration_repeatContext> parameter_declaration_repeat() {
			return getRuleContexts(Parameter_declaration_repeatContext.class);
		}
		public Parameter_declarationContext parameter_declaration(int i) {
			return getRuleContext(Parameter_declarationContext.class,i);
		}
		public List<Parameter_declarationContext> parameter_declaration() {
			return getRuleContexts(Parameter_declarationContext.class);
		}
		public Parameter_declaration_repeatContext parameter_declaration_repeat(int i) {
			return getRuleContext(Parameter_declaration_repeatContext.class,i);
		}
		public Parameter_declaration_repeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_declaration_repeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParameter_declaration_repeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParameter_declaration_repeat(this);
		}
	}

	public final Parameter_declaration_repeatContext parameter_declaration_repeat() throws RecognitionException {
		Parameter_declaration_repeatContext _localctx = new Parameter_declaration_repeatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_parameter_declaration_repeat);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(142); match(1);
					setState(143); parameter_declaration();
					setState(144); parameter_declaration_repeat();
					}
					} 
				}
				setState(150);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_la = _input.LA(1);
			if (_la==22) {
				{
				setState(151); function_declaration();
				setState(152); function();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_declarationContext extends ParserRuleContext {
		public NameContext name;
		public Parameter_declaration_listContext parameter_declaration_list() {
			return getRuleContext(Parameter_declaration_listContext.class,0);
		}
		public Any_typeContext any_type() {
			return getRuleContext(Any_typeContext.class,0);
		}
		public Function_bodyContext function_body() {
			return getRuleContext(Function_bodyContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunction_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunction_declaration(this);
		}
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_function_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156); match(22);
			setState(157); any_type();
			setState(158); ((Function_declarationContext)_localctx).name = name();
			tree.openScope((((Function_declarationContext)_localctx).name!=null?_input.getText(((Function_declarationContext)_localctx).name.start,((Function_declarationContext)_localctx).name.stop):null));
			setState(160); match(5);
			setState(161); parameter_declaration_list();
			setState(162); match(17);
			setState(163); match(28);
			setState(164); function_body();
			setState(165); match(8);
			tree.closeScope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_bodyContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public Function_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunction_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunction_body(this);
		}
	}

	public final Function_bodyContext function_body() throws RecognitionException {
		Function_bodyContext _localctx = new Function_bodyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_function_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); declaration();
			setState(169); statement_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Statement_listContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public Statement_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStatement_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStatement_list(this);
		}
	}

	public final Statement_listContext statement_list() throws RecognitionException {
		Statement_listContext _localctx = new Statement_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_statement_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 15) | (1L << 16) | (1L << 19) | (1L << 26) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(171); statement();
				setState(172); statement_list();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Basic_statementContext basic_statement() {
			return getRuleContext(Basic_statementContext.class,0);
		}
		public While_statementContext while_statement() {
			return getRuleContext(While_statementContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_statement);
		try {
			setState(179);
			switch (_input.LA(1)) {
			case 6:
			case 16:
			case 26:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(176); basic_statement();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 2);
				{
				setState(177); if_statement();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 3);
				{
				setState(178); while_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Basic_statementContext extends ParserRuleContext {
		public WriteContext write() {
			return getRuleContext(WriteContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Re_turnContext re_turn() {
			return getRuleContext(Re_turnContext.class,0);
		}
		public ReadContext read() {
			return getRuleContext(ReadContext.class,0);
		}
		public Basic_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basic_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterBasic_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitBasic_statement(this);
		}
	}

	public final Basic_statementContext basic_statement() throws RecognitionException {
		Basic_statementContext _localctx = new Basic_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_basic_statement);
		try {
			setState(185);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(181); assignment();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 2);
				{
				setState(182); read();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 3);
				{
				setState(183); write();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 4);
				{
				setState(184); re_turn();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Assignment_frameContext assignment_frame() {
			return getRuleContext(Assignment_frameContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); assignment_frame();
			setState(188); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assignment_frameContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Assignment_frameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_frame; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssignment_frame(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssignment_frame(this);
		}
	}

	public final Assignment_frameContext assignment_frame() throws RecognitionException {
		Assignment_frameContext _localctx = new Assignment_frameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assignment_frame);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190); name();
			setState(191); match(25);
			setState(192); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReadContext extends ParserRuleContext {
		public Name_listContext name_list() {
			return getRuleContext(Name_listContext.class,0);
		}
		public ReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitRead(this);
		}
	}

	public final ReadContext read() throws RecognitionException {
		ReadContext _localctx = new ReadContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_read);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194); match(6);
			setState(195); match(5);
			setState(196); name_list();
			setState(197); match(17);
			setState(198); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WriteContext extends ParserRuleContext {
		public Name_listContext name_list() {
			return getRuleContext(Name_listContext.class,0);
		}
		public WriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitWrite(this);
		}
	}

	public final WriteContext write() throws RecognitionException {
		WriteContext _localctx = new WriteContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_write);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200); match(16);
			setState(201); match(5);
			setState(202); name_list();
			setState(203); match(17);
			setState(204); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Re_turnContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Re_turnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_turn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterRe_turn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitRe_turn(this);
		}
	}

	public final Re_turnContext re_turn() throws RecognitionException {
		Re_turnContext _localctx = new Re_turnContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_re_turn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(26);
			setState(207); expression();
			setState(208); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Pre_expressionContext pre_expression() {
			return getRuleContext(Pre_expressionContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); pre_expression(0);
			setState(211); factor();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pre_expressionContext extends ParserRuleContext {
		public int _p;
		public Pre_expressionContext pre_expression() {
			return getRuleContext(Pre_expressionContext.class,0);
		}
		public Addition_operationContext addition_operation() {
			return getRuleContext(Addition_operationContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public Pre_expressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Pre_expressionContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_pre_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPre_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPre_expression(this);
		}
	}

	public final Pre_expressionContext pre_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Pre_expressionContext _localctx = new Pre_expressionContext(_ctx, _parentState, _p);
		Pre_expressionContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, RULE_pre_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(220);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Pre_expressionContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_pre_expression);
					setState(214);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(215); factor();
					setState(216); addition_operation();
					}
					} 
				}
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Pre_factorContext pre_factor() {
			return getRuleContext(Pre_factorContext.class,0);
		}
		public Post_expressionContext post_expression() {
			return getRuleContext(Post_expressionContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223); pre_factor(0);
			setState(224); post_expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pre_factorContext extends ParserRuleContext {
		public int _p;
		public Pre_factorContext pre_factor() {
			return getRuleContext(Pre_factorContext.class,0);
		}
		public Multiplication_operationContext multiplication_operation() {
			return getRuleContext(Multiplication_operationContext.class,0);
		}
		public Post_expressionContext post_expression() {
			return getRuleContext(Post_expressionContext.class,0);
		}
		public Pre_factorContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Pre_factorContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_pre_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPre_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPre_factor(this);
		}
	}

	public final Pre_factorContext pre_factor(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Pre_factorContext _localctx = new Pre_factorContext(_ctx, _parentState, _p);
		Pre_factorContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, RULE_pre_factor);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(233);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Pre_factorContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_pre_factor);
					setState(227);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(228); post_expression();
					setState(229); multiplication_operation();
					}
					} 
				}
				setState(235);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Post_expressionContext extends ParserRuleContext {
		public Expression_callContext expression_call() {
			return getRuleContext(Expression_callContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public Post_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_post_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPost_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPost_expression(this);
		}
	}

	public final Post_expressionContext post_expression() throws RecognitionException {
		Post_expressionContext _localctx = new Post_expressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_post_expression);
		try {
			setState(238);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236); primary();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(237); expression_call();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_callContext extends ParserRuleContext {
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Expression_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpression_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpression_call(this);
		}
	}

	public final Expression_callContext expression_call() throws RecognitionException {
		Expression_callContext _localctx = new Expression_callContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); name();
			setState(241); match(5);
			setState(242); expression_list();
			setState(243); match(17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_listContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Expression_list_repeatContext expression_list_repeat(int i) {
			return getRuleContext(Expression_list_repeatContext.class,i);
		}
		public List<Expression_list_repeatContext> expression_list_repeat() {
			return getRuleContexts(Expression_list_repeatContext.class);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpression_list(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expression_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(245); expression();
					setState(246); expression_list_repeat();
					}
					} 
				}
				setState(252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_list_repeatContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Expression_list_repeatContext expression_list_repeat(int i) {
			return getRuleContext(Expression_list_repeatContext.class,i);
		}
		public List<Expression_list_repeatContext> expression_list_repeat() {
			return getRuleContexts(Expression_list_repeatContext.class);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expression_list_repeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list_repeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpression_list_repeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpression_list_repeat(this);
		}
	}

	public final Expression_list_repeatContext expression_list_repeat() throws RecognitionException {
		Expression_list_repeatContext _localctx = new Expression_list_repeatContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expression_list_repeat);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(253); match(1);
					setState(254); expression();
					setState(255); expression_list_repeat();
					}
					} 
				}
				setState(261);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode FLOATLITERAL() { return getToken(MicroParser.FLOATLITERAL, 0); }
		public TerminalNode INTLITERAL() { return getToken(MicroParser.INTLITERAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_primary);
		try {
			setState(269);
			switch (_input.LA(1)) {
			case 5:
				enterOuterAlt(_localctx, 1);
				{
				setState(262); match(5);
				setState(263); expression();
				setState(264); match(17);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(266); name();
				}
				break;
			case INTLITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(267); match(INTLITERAL);
				}
				break;
			case FLOATLITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(268); match(FLOATLITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Addition_operationContext extends ParserRuleContext {
		public Addition_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addition_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAddition_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAddition_operation(this);
		}
	}

	public final Addition_operationContext addition_operation() throws RecognitionException {
		Addition_operationContext _localctx = new Addition_operationContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_addition_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_la = _input.LA(1);
			if ( !(_la==3 || _la==18) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multiplication_operationContext extends ParserRuleContext {
		public Multiplication_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplication_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterMultiplication_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitMultiplication_operation(this);
		}
	}

	public final Multiplication_operationContext multiplication_operation() throws RecognitionException {
		Multiplication_operationContext _localctx = new Multiplication_operationContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_multiplication_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_la = _input.LA(1);
			if ( !(_la==2 || _la==27) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_statementContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public Else_portionContext else_portion() {
			return getRuleContext(Else_portionContext.class,0);
		}
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitIf_statement(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_if_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(15);
			tree.openScope();
			setState(277); match(5);
			setState(278); condition();
			setState(279); match(17);
			setState(280); declaration();
			setState(281); statement_list();
			tree.closeScope();
			setState(283); else_portion();
			setState(284); match(12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Else_portionContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public Else_portionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_portion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterElse_portion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitElse_portion(this);
		}
	}

	public final Else_portionContext else_portion() throws RecognitionException {
		Else_portionContext _localctx = new Else_portionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_else_portion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			_la = _input.LA(1);
			if (_la==11) {
				{
				setState(286); match(11);
				tree.openScope();
				setState(288); declaration();
				setState(289); statement_list();
				tree.closeScope();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_statementContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public While_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterWhile_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitWhile_statement(this);
		}
	}

	public final While_statementContext while_statement() throws RecognitionException {
		While_statementContext _localctx = new While_statementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294); match(19);
			tree.openScope();
			setState(296); match(5);
			setState(297); condition();
			setState(298); match(17);
			setState(299); declaration();
			setState(300); statement_list();
			setState(301); match(31);
			tree.closeScope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Comparison_operatorContext comparison_operator() {
			return getRuleContext(Comparison_operatorContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); expression();
			setState(305); comparison_operator();
			setState(306); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comparison_operatorContext extends ParserRuleContext {
		public Comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterComparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitComparison_operator(this);
		}
	}

	public final Comparison_operatorContext comparison_operator() throws RecognitionException {
		Comparison_operatorContext _localctx = new Comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 9) | (1L << 10) | (1L << 20) | (1L << 23) | (1L << 29))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26: return pre_expression_sempred((Pre_expressionContext)_localctx, predIndex);

		case 28: return pre_factor_sempred((Pre_factorContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean pre_factor_sempred(Pre_factorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean pre_expression_sempred(Pre_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3)\u0139\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\7\5c\n\5\f\5\16\5f\13"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\5\nz\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0086\n"+
		"\f\3\r\3\r\3\r\5\r\u008b\n\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\7"+
		"\17\u0095\n\17\f\17\16\17\u0098\13\17\3\20\3\20\3\20\5\20\u009d\n\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\5\23\u00b1\n\23\3\24\3\24\3\24\5\24\u00b6\n\24\3\25"+
		"\3\25\3\25\3\25\5\25\u00bc\n\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\7\34\u00dd\n\34\f\34\16"+
		"\34\u00e0\13\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\7\36\u00ea\n\36"+
		"\f\36\16\36\u00ed\13\36\3\37\3\37\5\37\u00f1\n\37\3 \3 \3 \3 \3 \3!\3"+
		"!\3!\7!\u00fb\n!\f!\16!\u00fe\13!\3\"\3\"\3\"\3\"\7\"\u0104\n\"\f\"\16"+
		"\"\u0107\13\"\3#\3#\3#\3#\3#\3#\3#\5#\u0110\n#\3$\3$\3%\3%\3&\3&\3&\3"+
		"&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u0127\n\'\3(\3(\3("+
		"\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\2+\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\6\4\2\32\32  \4\2"+
		"\5\5\24\24\4\2\4\4\35\35\7\2\t\t\13\f\26\26\31\31\37\37\u0125\2T\3\2\2"+
		"\2\4Z\3\2\2\2\6]\3\2\2\2\bd\3\2\2\2\ng\3\2\2\2\fn\3\2\2\2\16p\3\2\2\2"+
		"\20u\3\2\2\2\22y\3\2\2\2\24{\3\2\2\2\26\u0085\3\2\2\2\30\u008a\3\2\2\2"+
		"\32\u008c\3\2\2\2\34\u0096\3\2\2\2\36\u009c\3\2\2\2 \u009e\3\2\2\2\"\u00aa"+
		"\3\2\2\2$\u00b0\3\2\2\2&\u00b5\3\2\2\2(\u00bb\3\2\2\2*\u00bd\3\2\2\2,"+
		"\u00c0\3\2\2\2.\u00c4\3\2\2\2\60\u00ca\3\2\2\2\62\u00d0\3\2\2\2\64\u00d4"+
		"\3\2\2\2\66\u00d7\3\2\2\28\u00e1\3\2\2\2:\u00e4\3\2\2\2<\u00f0\3\2\2\2"+
		">\u00f2\3\2\2\2@\u00fc\3\2\2\2B\u0105\3\2\2\2D\u010f\3\2\2\2F\u0111\3"+
		"\2\2\2H\u0113\3\2\2\2J\u0115\3\2\2\2L\u0126\3\2\2\2N\u0128\3\2\2\2P\u0132"+
		"\3\2\2\2R\u0136\3\2\2\2TU\7\6\2\2UV\5\4\3\2VW\7\36\2\2WX\5\6\4\2XY\7\n"+
		"\2\2Y\3\3\2\2\2Z[\7#\2\2[\\\b\3\1\2\\\5\3\2\2\2]^\5\b\5\2^_\5\36\20\2"+
		"_\7\3\2\2\2`c\5\n\6\2ac\5\16\b\2b`\3\2\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2"+
		"\2de\3\2\2\2e\t\3\2\2\2fd\3\2\2\2gh\7\20\2\2hi\5\4\3\2ij\7\33\2\2jk\5"+
		"\f\7\2kl\7\27\2\2lm\b\6\1\2m\13\3\2\2\2no\7\'\2\2o\r\3\2\2\2pq\5\20\t"+
		"\2qr\5\24\13\2rs\7\27\2\2st\b\b\1\2t\17\3\2\2\2uv\t\2\2\2v\21\3\2\2\2"+
		"wz\5\20\t\2xz\7\17\2\2yw\3\2\2\2yx\3\2\2\2z\23\3\2\2\2{|\5\4\3\2|}\5\26"+
		"\f\2}~\b\13\1\2~\25\3\2\2\2\177\u0080\7\3\2\2\u0080\u0081\5\4\3\2\u0081"+
		"\u0082\5\26\f\2\u0082\u0083\b\f\1\2\u0083\u0086\3\2\2\2\u0084\u0086\b"+
		"\f\1\2\u0085\177\3\2\2\2\u0085\u0084\3\2\2\2\u0086\27\3\2\2\2\u0087\u0088"+
		"\5\32\16\2\u0088\u0089\5\34\17\2\u0089\u008b\3\2\2\2\u008a\u0087\3\2\2"+
		"\2\u008a\u008b\3\2\2\2\u008b\31\3\2\2\2\u008c\u008d\5\20\t\2\u008d\u008e"+
		"\5\4\3\2\u008e\u008f\b\16\1\2\u008f\33\3\2\2\2\u0090\u0091\7\3\2\2\u0091"+
		"\u0092\5\32\16\2\u0092\u0093\5\34\17\2\u0093\u0095\3\2\2\2\u0094\u0090"+
		"\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\35\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\5 \21\2\u009a\u009b\5\36\20"+
		"\2\u009b\u009d\3\2\2\2\u009c\u0099\3\2\2\2\u009c\u009d\3\2\2\2\u009d\37"+
		"\3\2\2\2\u009e\u009f\7\30\2\2\u009f\u00a0\5\22\n\2\u00a0\u00a1\5\4\3\2"+
		"\u00a1\u00a2\b\21\1\2\u00a2\u00a3\7\7\2\2\u00a3\u00a4\5\30\r\2\u00a4\u00a5"+
		"\7\23\2\2\u00a5\u00a6\7\36\2\2\u00a6\u00a7\5\"\22\2\u00a7\u00a8\7\n\2"+
		"\2\u00a8\u00a9\b\21\1\2\u00a9!\3\2\2\2\u00aa\u00ab\5\b\5\2\u00ab\u00ac"+
		"\5$\23\2\u00ac#\3\2\2\2\u00ad\u00ae\5&\24\2\u00ae\u00af\5$\23\2\u00af"+
		"\u00b1\3\2\2\2\u00b0\u00ad\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1%\3\2\2\2"+
		"\u00b2\u00b6\5(\25\2\u00b3\u00b6\5J&\2\u00b4\u00b6\5N(\2\u00b5\u00b2\3"+
		"\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b4\3\2\2\2\u00b6\'\3\2\2\2\u00b7\u00bc"+
		"\5*\26\2\u00b8\u00bc\5.\30\2\u00b9\u00bc\5\60\31\2\u00ba\u00bc\5\62\32"+
		"\2\u00bb\u00b7\3\2\2\2\u00bb\u00b8\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00ba"+
		"\3\2\2\2\u00bc)\3\2\2\2\u00bd\u00be\5,\27\2\u00be\u00bf\7\27\2\2\u00bf"+
		"+\3\2\2\2\u00c0\u00c1\5\4\3\2\u00c1\u00c2\7\33\2\2\u00c2\u00c3\5\64\33"+
		"\2\u00c3-\3\2\2\2\u00c4\u00c5\7\b\2\2\u00c5\u00c6\7\7\2\2\u00c6\u00c7"+
		"\5\24\13\2\u00c7\u00c8\7\23\2\2\u00c8\u00c9\7\27\2\2\u00c9/\3\2\2\2\u00ca"+
		"\u00cb\7\22\2\2\u00cb\u00cc\7\7\2\2\u00cc\u00cd\5\24\13\2\u00cd\u00ce"+
		"\7\23\2\2\u00ce\u00cf\7\27\2\2\u00cf\61\3\2\2\2\u00d0\u00d1\7\34\2\2\u00d1"+
		"\u00d2\5\64\33\2\u00d2\u00d3\7\27\2\2\u00d3\63\3\2\2\2\u00d4\u00d5\5\66"+
		"\34\2\u00d5\u00d6\58\35\2\u00d6\65\3\2\2\2\u00d7\u00de\b\34\1\2\u00d8"+
		"\u00d9\6\34\2\3\u00d9\u00da\58\35\2\u00da\u00db\5F$\2\u00db\u00dd\3\2"+
		"\2\2\u00dc\u00d8\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de"+
		"\u00df\3\2\2\2\u00df\67\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e2\5:\36"+
		"\2\u00e2\u00e3\5<\37\2\u00e39\3\2\2\2\u00e4\u00eb\b\36\1\2\u00e5\u00e6"+
		"\6\36\3\3\u00e6\u00e7\5<\37\2\u00e7\u00e8\5H%\2\u00e8\u00ea\3\2\2\2\u00e9"+
		"\u00e5\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2"+
		"\2\2\u00ec;\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f1\5D#\2\u00ef\u00f1"+
		"\5> \2\u00f0\u00ee\3\2\2\2\u00f0\u00ef\3\2\2\2\u00f1=\3\2\2\2\u00f2\u00f3"+
		"\5\4\3\2\u00f3\u00f4\7\7\2\2\u00f4\u00f5\5@!\2\u00f5\u00f6\7\23\2\2\u00f6"+
		"?\3\2\2\2\u00f7\u00f8\5\64\33\2\u00f8\u00f9\5B\"\2\u00f9\u00fb\3\2\2\2"+
		"\u00fa\u00f7\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fdA\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0100\7\3\2\2\u0100"+
		"\u0101\5\64\33\2\u0101\u0102\5B\"\2\u0102\u0104\3\2\2\2\u0103\u00ff\3"+
		"\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"C\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u0109\7\7\2\2\u0109\u010a\5\64\33"+
		"\2\u010a\u010b\7\23\2\2\u010b\u0110\3\2\2\2\u010c\u0110\5\4\3\2\u010d"+
		"\u0110\7%\2\2\u010e\u0110\7&\2\2\u010f\u0108\3\2\2\2\u010f\u010c\3\2\2"+
		"\2\u010f\u010d\3\2\2\2\u010f\u010e\3\2\2\2\u0110E\3\2\2\2\u0111\u0112"+
		"\t\3\2\2\u0112G\3\2\2\2\u0113\u0114\t\4\2\2\u0114I\3\2\2\2\u0115\u0116"+
		"\7\21\2\2\u0116\u0117\b&\1\2\u0117\u0118\7\7\2\2\u0118\u0119\5P)\2\u0119"+
		"\u011a\7\23\2\2\u011a\u011b\5\b\5\2\u011b\u011c\5$\23\2\u011c\u011d\b"+
		"&\1\2\u011d\u011e\5L\'\2\u011e\u011f\7\16\2\2\u011fK\3\2\2\2\u0120\u0121"+
		"\7\r\2\2\u0121\u0122\b\'\1\2\u0122\u0123\5\b\5\2\u0123\u0124\5$\23\2\u0124"+
		"\u0125\b\'\1\2\u0125\u0127\3\2\2\2\u0126\u0120\3\2\2\2\u0126\u0127\3\2"+
		"\2\2\u0127M\3\2\2\2\u0128\u0129\7\25\2\2\u0129\u012a\b(\1\2\u012a\u012b"+
		"\7\7\2\2\u012b\u012c\5P)\2\u012c\u012d\7\23\2\2\u012d\u012e\5\b\5\2\u012e"+
		"\u012f\5$\23\2\u012f\u0130\7!\2\2\u0130\u0131\b(\1\2\u0131O\3\2\2\2\u0132"+
		"\u0133\5\64\33\2\u0133\u0134\5R*\2\u0134\u0135\5\64\33\2\u0135Q\3\2\2"+
		"\2\u0136\u0137\t\5\2\2\u0137S\3\2\2\2\23bdy\u0085\u008a\u0096\u009c\u00b0"+
		"\u00b5\u00bb\u00de\u00eb\u00f0\u00fc\u0105\u010f\u0126";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}