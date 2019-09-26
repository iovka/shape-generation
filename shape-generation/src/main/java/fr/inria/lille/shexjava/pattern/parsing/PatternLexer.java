// Generated from fr/inria/lille/pattern/parsing/Pattern.g4 by ANTLR 4.7.1
package fr.inria.lille.shexjava.pattern.parsing;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PatternLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, KW_IRI=9, 
		KW_PREFIX=10, PASS=11, RDF_TYPE=12, IRIREF=13, PNAME_NS=14, PNAME_LN=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "KW_IRI", 
		"KW_PREFIX", "PASS", "RDF_TYPE", "IRIREF", "PNAME_NS", "PNAME_LN", "UCHAR", 
		"PN_CHARS_BASE", "PN_CHARS_U", "PN_CHARS", "PN_PREFIX", "PN_LOCAL", "PLX", 
		"PERCENT", "HEX", "PN_LOCAL_ESC", "A", "B", "C", "D", "E", "F", "G", "H", 
		"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", 
		"W", "X", "Y", "Z"
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


	public PatternLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Pattern.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u011f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\6\f\u0084\n\f\r\f\16\f"+
		"\u0085\3\f\3\f\3\r\3\r\3\16\3\16\3\16\7\16\u008f\n\16\f\16\16\16\u0092"+
		"\13\16\3\16\3\16\3\17\5\17\u0097\n\17\3\17\3\17\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\5\21\u00b2\n\21\3\22\5\22\u00b5\n\22\3\23\3\23"+
		"\5\23\u00b9\n\23\3\24\3\24\5\24\u00bd\n\24\3\25\3\25\3\25\7\25\u00c2\n"+
		"\25\f\25\16\25\u00c5\13\25\3\25\5\25\u00c8\n\25\3\26\3\26\3\26\5\26\u00cd"+
		"\n\26\3\26\3\26\3\26\7\26\u00d2\n\26\f\26\16\26\u00d5\13\26\3\26\3\26"+
		"\3\26\5\26\u00da\n\26\5\26\u00dc\n\26\3\27\3\27\5\27\u00e0\n\27\3\30\3"+
		"\30\3\30\3\30\3\31\5\31\u00e7\n\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"+
		"&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60"+
		"\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\2\2\65\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\2#\2%\2\'\2)\2+"+
		"\2-\2/\2\61\2\63\2\65\2\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2"+
		"U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2\3\2\"\5\2\13\f\17\17\"\"\t\2\2\"$$>@^^"+
		"``bb}\177\7\2//\62;\u00b9\u00b9\u0302\u0371\u2041\u2042\4\2\60\60<<\5"+
		"\2\62;CHch\t\2##%\61==??ABaa\u0080\u0080\4\2CCcc\4\2DDdd\4\2EEee\4\2F"+
		"Fff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4"+
		"\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWw"+
		"w\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\3\20\2C\2\\\2c\2|\2\u00c2"+
		"\2\u00d8\2\u00da\2\u00f8\2\u00fa\2\u0301\2\u0372\2\u037f\2\u0381\2\u2001"+
		"\2\u200e\2\u200f\2\u2072\2\u2191\2\u2c02\2\u2ff1\2\u3003\2\ud801\2\uf902"+
		"\2\ufdd1\2\ufdf2\2\uffff\2\2\3\uffff\20\u010d\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\3i\3\2\2\2\5k\3\2\2\2\7m\3\2\2\2\to\3\2\2"+
		"\2\13q\3\2\2\2\rs\3\2\2\2\17u\3\2\2\2\21w\3\2\2\2\23y\3\2\2\2\25{\3\2"+
		"\2\2\27\u0083\3\2\2\2\31\u0089\3\2\2\2\33\u008b\3\2\2\2\35\u0096\3\2\2"+
		"\2\37\u009a\3\2\2\2!\u00b1\3\2\2\2#\u00b4\3\2\2\2%\u00b8\3\2\2\2\'\u00bc"+
		"\3\2\2\2)\u00be\3\2\2\2+\u00cc\3\2\2\2-\u00df\3\2\2\2/\u00e1\3\2\2\2\61"+
		"\u00e6\3\2\2\2\63\u00e8\3\2\2\2\65\u00eb\3\2\2\2\67\u00ed\3\2\2\29\u00ef"+
		"\3\2\2\2;\u00f1\3\2\2\2=\u00f3\3\2\2\2?\u00f5\3\2\2\2A\u00f7\3\2\2\2C"+
		"\u00f9\3\2\2\2E\u00fb\3\2\2\2G\u00fd\3\2\2\2I\u00ff\3\2\2\2K\u0101\3\2"+
		"\2\2M\u0103\3\2\2\2O\u0105\3\2\2\2Q\u0107\3\2\2\2S\u0109\3\2\2\2U\u010b"+
		"\3\2\2\2W\u010d\3\2\2\2Y\u010f\3\2\2\2[\u0111\3\2\2\2]\u0113\3\2\2\2_"+
		"\u0115\3\2\2\2a\u0117\3\2\2\2c\u0119\3\2\2\2e\u011b\3\2\2\2g\u011d\3\2"+
		"\2\2ij\7}\2\2j\4\3\2\2\2kl\7=\2\2l\6\3\2\2\2mn\7\177\2\2n\b\3\2\2\2op"+
		"\7B\2\2p\n\3\2\2\2qr\7\60\2\2r\f\3\2\2\2st\7]\2\2t\16\3\2\2\2uv\7a\2\2"+
		"v\20\3\2\2\2wx\7_\2\2x\22\3\2\2\2yz\7\u0080\2\2z\24\3\2\2\2{|\5S*\2|}"+
		"\5W,\2}~\5=\37\2~\177\5? \2\177\u0080\5E#\2\u0080\u0081\5c\62\2\u0081"+
		"\26\3\2\2\2\u0082\u0084\t\2\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2"+
		"\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088"+
		"\b\f\2\2\u0088\30\3\2\2\2\u0089\u008a\7c\2\2\u008a\32\3\2\2\2\u008b\u0090"+
		"\7>\2\2\u008c\u008f\n\3\2\2\u008d\u008f\5!\21\2\u008e\u008c\3\2\2\2\u008e"+
		"\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7@\2\2\u0094"+
		"\34\3\2\2\2\u0095\u0097\5)\25\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2"+
		"\2\u0097\u0098\3\2\2\2\u0098\u0099\7<\2\2\u0099\36\3\2\2\2\u009a\u009b"+
		"\5\35\17\2\u009b\u009c\5+\26\2\u009c \3\2\2\2\u009d\u009e\7^\2\2\u009e"+
		"\u009f\7w\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\5\61\31\2\u00a1\u00a2\5"+
		"\61\31\2\u00a2\u00a3\5\61\31\2\u00a3\u00a4\5\61\31\2\u00a4\u00b2\3\2\2"+
		"\2\u00a5\u00a6\7^\2\2\u00a6\u00a7\7W\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9"+
		"\5\61\31\2\u00a9\u00aa\5\61\31\2\u00aa\u00ab\5\61\31\2\u00ab\u00ac\5\61"+
		"\31\2\u00ac\u00ad\5\61\31\2\u00ad\u00ae\5\61\31\2\u00ae\u00af\5\61\31"+
		"\2\u00af\u00b0\5\61\31\2\u00b0\u00b2\3\2\2\2\u00b1\u009d\3\2\2\2\u00b1"+
		"\u00a5\3\2\2\2\u00b2\"\3\2\2\2\u00b3\u00b5\t\"\2\2\u00b4\u00b3\3\2\2\2"+
		"\u00b5$\3\2\2\2\u00b6\u00b9\5#\22\2\u00b7\u00b9\7a\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b8\u00b7\3\2\2\2\u00b9&\3\2\2\2\u00ba\u00bd\5%\23\2\u00bb\u00bd"+
		"\t\4\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd(\3\2\2\2\u00be"+
		"\u00c7\5#\22\2\u00bf\u00c2\5\'\24\2\u00c0\u00c2\7\60\2\2\u00c1\u00bf\3"+
		"\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c8\5\'"+
		"\24\2\u00c7\u00c3\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8*\3\2\2\2\u00c9\u00cd"+
		"\5%\23\2\u00ca\u00cd\4\62<\2\u00cb\u00cd\5-\27\2\u00cc\u00c9\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00db\3\2\2\2\u00ce\u00d2\5\'"+
		"\24\2\u00cf\u00d2\t\5\2\2\u00d0\u00d2\5-\27\2\u00d1\u00ce\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2"+
		"\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d9\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6"+
		"\u00da\5\'\24\2\u00d7\u00da\7<\2\2\u00d8\u00da\5-\27\2\u00d9\u00d6\3\2"+
		"\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\u00dc\3\2\2\2\u00db"+
		"\u00d3\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc,\3\2\2\2\u00dd\u00e0\5/\30\2"+
		"\u00de\u00e0\5\63\32\2\u00df\u00dd\3\2\2\2\u00df\u00de\3\2\2\2\u00e0."+
		"\3\2\2\2\u00e1\u00e2\7\'\2\2\u00e2\u00e3\5\61\31\2\u00e3\u00e4\5\61\31"+
		"\2\u00e4\60\3\2\2\2\u00e5\u00e7\t\6\2\2\u00e6\u00e5\3\2\2\2\u00e7\62\3"+
		"\2\2\2\u00e8\u00e9\7^\2\2\u00e9\u00ea\t\7\2\2\u00ea\64\3\2\2\2\u00eb\u00ec"+
		"\t\b\2\2\u00ec\66\3\2\2\2\u00ed\u00ee\t\t\2\2\u00ee8\3\2\2\2\u00ef\u00f0"+
		"\t\n\2\2\u00f0:\3\2\2\2\u00f1\u00f2\t\13\2\2\u00f2<\3\2\2\2\u00f3\u00f4"+
		"\t\f\2\2\u00f4>\3\2\2\2\u00f5\u00f6\t\r\2\2\u00f6@\3\2\2\2\u00f7\u00f8"+
		"\t\16\2\2\u00f8B\3\2\2\2\u00f9\u00fa\t\17\2\2\u00faD\3\2\2\2\u00fb\u00fc"+
		"\t\20\2\2\u00fcF\3\2\2\2\u00fd\u00fe\t\21\2\2\u00feH\3\2\2\2\u00ff\u0100"+
		"\t\22\2\2\u0100J\3\2\2\2\u0101\u0102\t\23\2\2\u0102L\3\2\2\2\u0103\u0104"+
		"\t\24\2\2\u0104N\3\2\2\2\u0105\u0106\t\25\2\2\u0106P\3\2\2\2\u0107\u0108"+
		"\t\26\2\2\u0108R\3\2\2\2\u0109\u010a\t\27\2\2\u010aT\3\2\2\2\u010b\u010c"+
		"\t\30\2\2\u010cV\3\2\2\2\u010d\u010e\t\31\2\2\u010eX\3\2\2\2\u010f\u0110"+
		"\t\32\2\2\u0110Z\3\2\2\2\u0111\u0112\t\33\2\2\u0112\\\3\2\2\2\u0113\u0114"+
		"\t\34\2\2\u0114^\3\2\2\2\u0115\u0116\t\35\2\2\u0116`\3\2\2\2\u0117\u0118"+
		"\t\36\2\2\u0118b\3\2\2\2\u0119\u011a\t\37\2\2\u011ad\3\2\2\2\u011b\u011c"+
		"\t \2\2\u011cf\3\2\2\2\u011d\u011e\t!\2\2\u011eh\3\2\2\2\25\2\u0085\u008e"+
		"\u0090\u0096\u00b1\u00b4\u00b8\u00bc\u00c1\u00c3\u00c7\u00cc\u00d1\u00d3"+
		"\u00d9\u00db\u00df\u00e6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}