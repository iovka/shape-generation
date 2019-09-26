package fr.inria.lille.shexjava.pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.inria.lille.shexjava.pattern.abstrt.Pattern;

public class PatternParsingTest {
	private PatternParsing parser = new PatternParsing();
	private Map<String,String> prefixes;
	
	public PatternParsingTest() {
		prefixes = new HashMap<String, String>();
		prefixes.put("p:","http://prefix-p.org/");
		prefixes.put("pqr:","http://prefix-pqr.org/");
		prefixes.put("rdf:","http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		parser.setPrefixesMap(prefixes);
	}
	
	@Test
	public void testSimpleParsingPattern() {
		String pattern = "{ p:~ [__] }";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ http://prefix-p.org/~ [__] }");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error when parsing");
		}
	}
	
	
	@Test
	public void testSimpleParsingPattern2() {
		String pattern = "{ p:~ __ }";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ http://prefix-p.org/~ __ }");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error when parsing");
		}
	}
	
	
	@Test
	public void testPatternParsing() {
		String pattern = "{ p:~ __ ; p:P31 [__] }";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ http://prefix-p.org/~ __; <http://prefix-p.org/P31> [__] }");
		} catch (Exception e) {
			fail("Error when parsing");
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testRecursionPatternParsing() {
		String pattern = "{ p:~ {pqr:~ __ } }";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ http://prefix-p.org/~ { http://prefix-pqr.org/~ __ } }");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error when parsing");
		}
	}
	
	
	
	@Test
	public void testPrefixedPatternParsing() {		
		String pattern = "PREFIX new: <http://new.io/> { new:P31 __ ; }";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ <http://new.io/P31> __ }");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error when parsing");
		}
	}
	
	@Test
	public void testPrefixedPatternParsing2() {		
		String pattern = "@ PREFIX new: <http://new.io/> . { new:P31 __ ; }";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ <http://new.io/P31> __ }");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error when parsing");
		}
	}
	
	@Test
	public void testPatternWithRDFTypeParsing() {		
		String pattern = "{rdf:type [__] ; ~ __}";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> [__]; ~ __ }");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error when parsing");
		}
	}
	
	@Test
	public void testPatternWithRDFTypeParsing2() {		
		String pattern = "{a [__] ; ~ __}";
		try {
			Pattern result = parser.getPattern(new ByteArrayInputStream(pattern.getBytes()));
			assertEquals(result.toString(), "{ <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> [__]; ~ __ }");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error when parsing");
		}
	}
}
