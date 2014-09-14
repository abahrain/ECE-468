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
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, KEYWORD=18, IDENTIFIER=19, OPERATOR=20, INTLITERAL=21, FLOATLITERAL=22, 
		STRINGLITERAL=23, SPACE=24, COMMENT=25;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "','", "'('", "'PROGRAM'", "'WHILE'", "'ENDWILE'", 
		"'END'", "';'", "'FUNCTION'", "'ELSE'", "'ENDIF'", "'FLOAT'", "'STRING'", 
		"'RETURN'", "'IF'", "'BEGIN'", "'INT'", "KEYWORD", "IDENTIFIER", "OPERATOR", 
		"INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", "SPACE", "COMMENT"
	};
	public static final int
		RULE_program = 0, RULE_name = 1, RULE_key = 2, RULE_integer = 3, RULE_program_body = 4, 
		RULE_declaration = 5, RULE_function_declaration = 6, RULE_string_declaration_list = 7, 
		RULE_variable_declaration_list = 8, RULE_function_body = 9, RULE_statement = 10, 
		RULE_if_statement = 11, RULE_else_statement = 12, RULE_while_loop = 13, 
		RULE_operation = 14;
	public static final String[] ruleNames = {
		"program", "name", "key", "integer", "program_body", "declaration", "function_declaration", 
		"string_declaration_list", "variable_declaration_list", "function_body", 
		"statement", "if_statement", "else_statement", "while_loop", "operation"
	};

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

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
			setState(30); match(4);
			setState(31); name();
			setState(32); match(16);
			setState(33); program_body();
			setState(34); match(7);
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
			setState(36); match(IDENTIFIER);
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

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode KEYWORD() { return getToken(MicroParser.KEYWORD, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitKey(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); match(KEYWORD);
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

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode INTLITERAL() { return getToken(MicroParser.INTLITERAL, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitInteger(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_integer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); match(INTLITERAL);
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
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
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
		enterRule(_localctx, 8, RULE_program_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << 12) | (1L << 13) | (1L << 17))) != 0)) {
				{
				setState(42); declaration();
				setState(43); function_declaration();
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
		enterRule(_localctx, 10, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 12) | (1L << 13) | (1L << 17))) != 0)) {
				{
				setState(49);
				switch (_input.LA(1)) {
				case 13:
					{
					setState(47); string_declaration_list();
					}
					break;
				case 12:
				case 17:
					{
					setState(48); variable_declaration_list();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(53);
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

	public static class Function_declarationContext extends ParserRuleContext {
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public Function_bodyContext function_body() {
			return getRuleContext(Function_bodyContext.class,0);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
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
		enterRule(_localctx, 12, RULE_function_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); match(9);
			setState(55); key();
			setState(56); name();
			setState(57); match(3);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEYWORD) {
				{
				{
				setState(58); key();
				setState(59); name();
				setState(61);
				_la = _input.LA(1);
				if (_la==2) {
					{
					setState(60); match(2);
					}
				}

				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68); match(1);
			setState(69); match(16);
			setState(70); function_body();
			setState(71); match(7);
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
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode STRINGLITERAL() { return getToken(MicroParser.STRINGLITERAL, 0); }
		public TerminalNode OPERATOR() { return getToken(MicroParser.OPERATOR, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
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
		enterRule(_localctx, 14, RULE_string_declaration_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73); match(13);
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74); name();
				setState(76);
				_la = _input.LA(1);
				if (_la==2) {
					{
					setState(75); match(2);
					}
				}

				}
				}
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(84);
			_la = _input.LA(1);
			if (_la==OPERATOR) {
				{
				setState(82); match(OPERATOR);
				setState(83); match(STRINGLITERAL);
				}
			}

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

	public static class Variable_declaration_listContext extends ParserRuleContext {
		public TerminalNode FLOATLITERAL() { return getToken(MicroParser.FLOATLITERAL, 0); }
		public TerminalNode INTLITERAL() { return getToken(MicroParser.INTLITERAL, 0); }
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode OPERATOR() { return getToken(MicroParser.OPERATOR, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
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
		enterRule(_localctx, 16, RULE_variable_declaration_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			switch (_input.LA(1)) {
			case 17:
				{
				{
				setState(88); match(17);
				setState(93); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(89); name();
					setState(91);
					_la = _input.LA(1);
					if (_la==2) {
						{
						setState(90); match(2);
						}
					}

					}
					}
					setState(95); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				setState(99);
				_la = _input.LA(1);
				if (_la==OPERATOR) {
					{
					setState(97); match(OPERATOR);
					setState(98); match(INTLITERAL);
					}
				}

				}
				}
				break;
			case 12:
				{
				{
				setState(101); match(12);
				setState(106); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(102); name();
					setState(104);
					_la = _input.LA(1);
					if (_la==2) {
						{
						setState(103); match(2);
						}
					}

					}
					}
					setState(108); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				setState(112);
				_la = _input.LA(1);
				if (_la==OPERATOR) {
					{
					setState(110); match(OPERATOR);
					setState(111); match(FLOATLITERAL);
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(116); match(8);
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
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
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
		enterRule(_localctx, 18, RULE_function_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 12) | (1L << 13) | (1L << 15) | (1L << 17) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(118); declaration();
				setState(119); statement();
				setState(124);
				_la = _input.LA(1);
				if (_la==14) {
					{
					setState(120); match(14);
					setState(122);
					_la = _input.LA(1);
					if (_la==IDENTIFIER) {
						{
						setState(121); operation();
						}
					}

					}
				}

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
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public List<While_loopContext> while_loop() {
			return getRuleContexts(While_loopContext.class);
		}
		public If_statementContext if_statement(int i) {
			return getRuleContext(If_statementContext.class,i);
		}
		public While_loopContext while_loop(int i) {
			return getRuleContext(While_loopContext.class,i);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public List<If_statementContext> if_statement() {
			return getRuleContexts(If_statementContext.class);
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
		enterRule(_localctx, 20, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(131);
				switch (_input.LA(1)) {
				case 15:
					{
					setState(128); if_statement();
					}
					break;
				case 5:
					{
					setState(129); while_loop();
					}
					break;
				case IDENTIFIER:
					{
					setState(130); operation();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 15) | (1L << IDENTIFIER))) != 0) );
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
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Else_statementContext else_statement() {
			return getRuleContext(Else_statementContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
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
		enterRule(_localctx, 22, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); match(15);
			setState(136); match(3);
			setState(137); operation();
			setState(138); match(1);
			setState(139); statement();
			setState(141);
			_la = _input.LA(1);
			if (_la==10) {
				{
				setState(140); else_statement();
				}
			}

			setState(143); match(11);
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

	public static class Else_statementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Else_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterElse_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitElse_statement(this);
		}
	}

	public final Else_statementContext else_statement() throws RecognitionException {
		Else_statementContext _localctx = new Else_statementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); match(10);
			setState(146); statement();
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

	public static class While_loopContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public While_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterWhile_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitWhile_loop(this);
		}
	}

	public final While_loopContext while_loop() throws RecognitionException {
		While_loopContext _localctx = new While_loopContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); match(5);
			setState(149); match(3);
			setState(150); operation();
			setState(151); match(1);
			setState(152); statement();
			setState(153); match(6);
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

	public static class OperationContext extends ParserRuleContext {
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode OPERATOR(int i) {
			return getToken(MicroParser.OPERATOR, i);
		}
		public List<TerminalNode> OPERATOR() { return getTokens(MicroParser.OPERATOR); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitOperation(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(155); name();
			setState(160); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(160);
				switch (_input.LA(1)) {
				case OPERATOR:
					{
					setState(156); match(OPERATOR);
					}
					break;
				case IDENTIFIER:
					{
					setState(157); name();
					}
					break;
				case 3:
					{
					setState(158); match(3);
					}
					break;
				case 1:
					{
					setState(159); match(1);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 3) | (1L << IDENTIFIER) | (1L << OPERATOR))) != 0) );
			}
			setState(164); match(8);
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

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\33\u00a9\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\5\6\60\n\6\3\7\3\7\7\7\64\n"+
		"\7\f\7\16\7\67\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b@\n\b\7\bB\n\b\f\b"+
		"\16\bE\13\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\5\tO\n\t\6\tQ\n\t\r\t\16\t"+
		"R\3\t\3\t\5\tW\n\t\3\t\3\t\3\n\3\n\3\n\5\n^\n\n\6\n`\n\n\r\n\16\na\3\n"+
		"\3\n\5\nf\n\n\3\n\3\n\3\n\5\nk\n\n\6\nm\n\n\r\n\16\nn\3\n\3\n\5\ns\n\n"+
		"\5\nu\n\n\3\n\3\n\3\13\3\13\3\13\3\13\5\13}\n\13\5\13\177\n\13\5\13\u0081"+
		"\n\13\3\f\3\f\3\f\6\f\u0086\n\f\r\f\16\f\u0087\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\5\r\u0090\n\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\6\20\u00a3\n\20\r\20\16\20\u00a4\3\20\3\20"+
		"\3\20\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\u00b3\2 \3\2\2\2"+
		"\4&\3\2\2\2\6(\3\2\2\2\b*\3\2\2\2\n/\3\2\2\2\f\65\3\2\2\2\168\3\2\2\2"+
		"\20K\3\2\2\2\22t\3\2\2\2\24\u0080\3\2\2\2\26\u0085\3\2\2\2\30\u0089\3"+
		"\2\2\2\32\u0093\3\2\2\2\34\u0096\3\2\2\2\36\u009d\3\2\2\2 !\7\6\2\2!\""+
		"\5\4\3\2\"#\7\22\2\2#$\5\n\6\2$%\7\t\2\2%\3\3\2\2\2&\'\7\25\2\2\'\5\3"+
		"\2\2\2()\7\24\2\2)\7\3\2\2\2*+\7\27\2\2+\t\3\2\2\2,-\5\f\7\2-.\5\16\b"+
		"\2.\60\3\2\2\2/,\3\2\2\2/\60\3\2\2\2\60\13\3\2\2\2\61\64\5\20\t\2\62\64"+
		"\5\22\n\2\63\61\3\2\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66"+
		"\3\2\2\2\66\r\3\2\2\2\67\65\3\2\2\289\7\13\2\29:\5\6\4\2:;\5\4\3\2;C\7"+
		"\5\2\2<=\5\6\4\2=?\5\4\3\2>@\7\4\2\2?>\3\2\2\2?@\3\2\2\2@B\3\2\2\2A<\3"+
		"\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7\3\2\2GH\7"+
		"\22\2\2HI\5\24\13\2IJ\7\t\2\2J\17\3\2\2\2KP\7\17\2\2LN\5\4\3\2MO\7\4\2"+
		"\2NM\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PL\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2"+
		"\2SV\3\2\2\2TU\7\26\2\2UW\7\31\2\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\7\n"+
		"\2\2Y\21\3\2\2\2Z_\7\23\2\2[]\5\4\3\2\\^\7\4\2\2]\\\3\2\2\2]^\3\2\2\2"+
		"^`\3\2\2\2_[\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2\2cd\7\26\2"+
		"\2df\7\27\2\2ec\3\2\2\2ef\3\2\2\2fu\3\2\2\2gl\7\16\2\2hj\5\4\3\2ik\7\4"+
		"\2\2ji\3\2\2\2jk\3\2\2\2km\3\2\2\2lh\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2"+
		"\2\2or\3\2\2\2pq\7\26\2\2qs\7\30\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tZ\3"+
		"\2\2\2tg\3\2\2\2uv\3\2\2\2vw\7\n\2\2w\23\3\2\2\2xy\5\f\7\2y~\5\26\f\2"+
		"z|\7\20\2\2{}\5\36\20\2|{\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~z\3\2\2\2~\177"+
		"\3\2\2\2\177\u0081\3\2\2\2\u0080x\3\2\2\2\u0080\u0081\3\2\2\2\u0081\25"+
		"\3\2\2\2\u0082\u0086\5\30\r\2\u0083\u0086\5\34\17\2\u0084\u0086\5\36\20"+
		"\2\u0085\u0082\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0084\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\27\3\2\2\2\u0089"+
		"\u008a\7\21\2\2\u008a\u008b\7\5\2\2\u008b\u008c\5\36\20\2\u008c\u008d"+
		"\7\3\2\2\u008d\u008f\5\26\f\2\u008e\u0090\5\32\16\2\u008f\u008e\3\2\2"+
		"\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\7\r\2\2\u0092\31"+
		"\3\2\2\2\u0093\u0094\7\f\2\2\u0094\u0095\5\26\f\2\u0095\33\3\2\2\2\u0096"+
		"\u0097\7\7\2\2\u0097\u0098\7\5\2\2\u0098\u0099\5\36\20\2\u0099\u009a\7"+
		"\3\2\2\u009a\u009b\5\26\f\2\u009b\u009c\7\b\2\2\u009c\35\3\2\2\2\u009d"+
		"\u00a2\5\4\3\2\u009e\u00a3\7\26\2\2\u009f\u00a3\5\4\3\2\u00a0\u00a3\7"+
		"\5\2\2\u00a1\u00a3\7\3\2\2\u00a2\u009e\3\2\2\2\u00a2\u009f\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\7\n\2\2\u00a7"+
		"\37\3\2\2\2\31/\63\65?CNRV]aejnrt|~\u0080\u0085\u0087\u008f\u00a2\u00a4";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}