// Generated from fr/inria/lille/pattern/parsing/Pattern.g4 by ANTLR 4.7.1
package fr.inria.lille.shexjava.pattern.parsing;
import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PatternParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, KW_IRI=9, 
		KW_PREFIX=10, PASS=11, RDF_TYPE=12, IRIREF=13, PNAME_NS=14, PNAME_LN=15;
	public static final int
		RULE_pattern = 0, RULE_prefixDecl = 1, RULE_pTConstr = 2, RULE_predFilter = 3, 
		RULE_valueSelector = 4, RULE_listValues = 5, RULE_valueKind = 6, RULE_valueKindStem = 7, 
		RULE_filter = 8, RULE_predicate = 9, RULE_rdfType = 10, RULE_iri = 11, 
		RULE_prefixedName = 12, RULE_prefix = 13;
	public static final String[] ruleNames = {
		"pattern", "prefixDecl", "pTConstr", "predFilter", "valueSelector", "listValues", 
		"valueKind", "valueKindStem", "filter", "predicate", "rdfType", "iri", 
		"prefixedName", "prefix"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "';'", "'}'", "'@'", "'.'", "'['", "'_'", "']'", "'~'", null, 
		null, "'a'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "KW_IRI", "KW_PREFIX", 
		"PASS", "RDF_TYPE", "IRIREF", "PNAME_NS", "PNAME_LN"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Pattern.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PatternParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PatternContext extends ParserRuleContext {
		public List<PTConstrContext> pTConstr() {
			return getRuleContexts(PTConstrContext.class);
		}
		public PTConstrContext pTConstr(int i) {
			return getRuleContext(PTConstrContext.class,i);
		}
		public List<PrefixDeclContext> prefixDecl() {
			return getRuleContexts(PrefixDeclContext.class);
		}
		public PrefixDeclContext prefixDecl(int i) {
			return getRuleContext(PrefixDeclContext.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_pattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==KW_PREFIX) {
				{
				{
				setState(28);
				prefixDecl();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			match(T__0);
			setState(35);
			pTConstr();
			setState(40);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(36);
					match(T__1);
					setState(37);
					pTConstr();
					}
					} 
				}
				setState(42);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(43);
				match(T__1);
				}
			}

			setState(46);
			match(T__2);
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

	public static class PrefixDeclContext extends ParserRuleContext {
		public TerminalNode KW_PREFIX() { return getToken(PatternParser.KW_PREFIX, 0); }
		public TerminalNode PNAME_NS() { return getToken(PatternParser.PNAME_NS, 0); }
		public TerminalNode IRIREF() { return getToken(PatternParser.IRIREF, 0); }
		public PrefixDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitPrefixDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixDeclContext prefixDecl() throws RecognitionException {
		PrefixDeclContext _localctx = new PrefixDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prefixDecl);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_PREFIX:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(KW_PREFIX);
				setState(49);
				match(PNAME_NS);
				setState(50);
				match(IRIREF);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(T__3);
				setState(52);
				match(KW_PREFIX);
				setState(53);
				match(PNAME_NS);
				setState(54);
				match(IRIREF);
				setState(55);
				match(T__4);
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

	public static class PTConstrContext extends ParserRuleContext {
		public PredFilterContext predFilter() {
			return getRuleContext(PredFilterContext.class,0);
		}
		public ValueSelectorContext valueSelector() {
			return getRuleContext(ValueSelectorContext.class,0);
		}
		public PTConstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pTConstr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitPTConstr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PTConstrContext pTConstr() throws RecognitionException {
		PTConstrContext _localctx = new PTConstrContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pTConstr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			predFilter();
			setState(59);
			valueSelector();
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

	public static class PredFilterContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public PredFilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predFilter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitPredFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredFilterContext predFilter() throws RecognitionException {
		PredFilterContext _localctx = new PredFilterContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_predFilter);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				predicate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				filter();
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

	public static class ValueSelectorContext extends ParserRuleContext {
		public ListValuesContext listValues() {
			return getRuleContext(ListValuesContext.class,0);
		}
		public ValueKindContext valueKind() {
			return getRuleContext(ValueKindContext.class,0);
		}
		public ValueKindStemContext valueKindStem() {
			return getRuleContext(ValueKindStemContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ValueSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueSelector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitValueSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueSelectorContext valueSelector() throws RecognitionException {
		ValueSelectorContext _localctx = new ValueSelectorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_valueSelector);
		try {
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				listValues();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				valueKind();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				valueKindStem();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				pattern();
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

	public static class ListValuesContext extends ParserRuleContext {
		public ListValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listValues; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitListValues(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListValuesContext listValues() throws RecognitionException {
		ListValuesContext _localctx = new ListValuesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_listValues);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__5);
			setState(72);
			match(T__6);
			setState(73);
			match(T__6);
			setState(74);
			match(T__7);
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

	public static class ValueKindContext extends ParserRuleContext {
		public ValueKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueKind; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitValueKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueKindContext valueKind() throws RecognitionException {
		ValueKindContext _localctx = new ValueKindContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_valueKind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(T__6);
			setState(77);
			match(T__6);
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

	public static class ValueKindStemContext extends ParserRuleContext {
		public ValueKindStemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueKindStem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitValueKindStem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueKindStemContext valueKindStem() throws RecognitionException {
		ValueKindStemContext _localctx = new ValueKindStemContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_valueKindStem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__6);
			setState(80);
			match(KW_IRI);
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

	public static class FilterContext extends ParserRuleContext {
		public TerminalNode KW_IRI() { return getToken(PatternParser.KW_IRI, 0); }
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PNAME_NS:
				{
				setState(82);
				prefix();
				}
				break;
			case RDF_TYPE:
			case IRIREF:
			case PNAME_LN:
				{
				setState(83);
				predicate();
				}
				break;
			case KW_IRI:
				break;
			default:
				break;
			}
			setState(86);
			match(KW_IRI);
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

	public static class PredicateContext extends ParserRuleContext {
		public IriContext iri() {
			return getRuleContext(IriContext.class,0);
		}
		public RdfTypeContext rdfType() {
			return getRuleContext(RdfTypeContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_predicate);
		try {
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IRIREF:
			case PNAME_LN:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				iri();
				}
				break;
			case RDF_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				rdfType();
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

	public static class RdfTypeContext extends ParserRuleContext {
		public TerminalNode RDF_TYPE() { return getToken(PatternParser.RDF_TYPE, 0); }
		public RdfTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rdfType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitRdfType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RdfTypeContext rdfType() throws RecognitionException {
		RdfTypeContext _localctx = new RdfTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_rdfType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(RDF_TYPE);
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

	public static class IriContext extends ParserRuleContext {
		public TerminalNode IRIREF() { return getToken(PatternParser.IRIREF, 0); }
		public PrefixedNameContext prefixedName() {
			return getRuleContext(PrefixedNameContext.class,0);
		}
		public IriContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iri; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitIri(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IriContext iri() throws RecognitionException {
		IriContext _localctx = new IriContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_iri);
		try {
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IRIREF:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(IRIREF);
				}
				break;
			case PNAME_LN:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				prefixedName();
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

	public static class PrefixedNameContext extends ParserRuleContext {
		public TerminalNode PNAME_LN() { return getToken(PatternParser.PNAME_LN, 0); }
		public PrefixedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixedName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitPrefixedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixedNameContext prefixedName() throws RecognitionException {
		PrefixedNameContext _localctx = new PrefixedNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_prefixedName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(PNAME_LN);
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

	public static class PrefixContext extends ParserRuleContext {
		public TerminalNode PNAME_NS() { return getToken(PatternParser.PNAME_NS, 0); }
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PatternVisitor ) return ((PatternVisitor<? extends T>)visitor).visitPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_prefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(PNAME_NS);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21i\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\7\2 \n\2\f\2\16\2#\13\2\3\2\3\2"+
		"\3\2\3\2\7\2)\n\2\f\2\16\2,\13\2\3\2\5\2/\n\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\5\3;\n\3\3\4\3\4\3\4\3\5\3\5\5\5B\n\5\3\6\3\6\3\6\3\6"+
		"\5\6H\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\5\nW\n\n"+
		"\3\n\3\n\3\13\3\13\5\13]\n\13\3\f\3\f\3\r\3\r\5\rc\n\r\3\16\3\16\3\17"+
		"\3\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\2\2f\2!\3\2\2"+
		"\2\4:\3\2\2\2\6<\3\2\2\2\bA\3\2\2\2\nG\3\2\2\2\fI\3\2\2\2\16N\3\2\2\2"+
		"\20Q\3\2\2\2\22V\3\2\2\2\24\\\3\2\2\2\26^\3\2\2\2\30b\3\2\2\2\32d\3\2"+
		"\2\2\34f\3\2\2\2\36 \5\4\3\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3"+
		"\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\7\3\2\2%*\5\6\4\2&\'\7\4\2\2\')\5\6\4\2"+
		"(&\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+.\3\2\2\2,*\3\2\2\2-/\7\4\2\2"+
		".-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\5\2\2\61\3\3\2\2\2\62\63\7\f"+
		"\2\2\63\64\7\20\2\2\64;\7\17\2\2\65\66\7\6\2\2\66\67\7\f\2\2\678\7\20"+
		"\2\289\7\17\2\29;\7\7\2\2:\62\3\2\2\2:\65\3\2\2\2;\5\3\2\2\2<=\5\b\5\2"+
		"=>\5\n\6\2>\7\3\2\2\2?B\5\24\13\2@B\5\22\n\2A?\3\2\2\2A@\3\2\2\2B\t\3"+
		"\2\2\2CH\5\f\7\2DH\5\16\b\2EH\5\20\t\2FH\5\2\2\2GC\3\2\2\2GD\3\2\2\2G"+
		"E\3\2\2\2GF\3\2\2\2H\13\3\2\2\2IJ\7\b\2\2JK\7\t\2\2KL\7\t\2\2LM\7\n\2"+
		"\2M\r\3\2\2\2NO\7\t\2\2OP\7\t\2\2P\17\3\2\2\2QR\7\t\2\2RS\7\13\2\2S\21"+
		"\3\2\2\2TW\5\34\17\2UW\5\24\13\2VT\3\2\2\2VU\3\2\2\2VW\3\2\2\2WX\3\2\2"+
		"\2XY\7\13\2\2Y\23\3\2\2\2Z]\5\30\r\2[]\5\26\f\2\\Z\3\2\2\2\\[\3\2\2\2"+
		"]\25\3\2\2\2^_\7\16\2\2_\27\3\2\2\2`c\7\17\2\2ac\5\32\16\2b`\3\2\2\2b"+
		"a\3\2\2\2c\31\3\2\2\2de\7\21\2\2e\33\3\2\2\2fg\7\20\2\2g\35\3\2\2\2\13"+
		"!*.:AGV\\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}