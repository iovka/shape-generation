package fr.inria.lille.shexjava.pattern.indications;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.lille.shexjava.pattern.PatternParsing;

public class TestTutorialExamples {
	
	@Test
	public void test_G1_P1_QUser() throws Exception {
		TestUtil._test_with_rdftype("Tutorial-G1.ttl", "http://ex.org/User", "Tutorial-P1.pattern", "Tutorial-Shape-G1-P1-QUser.shex", parser, serializer);
	}
	
	@Test
	public void test_G1_P1_QTopic() throws Exception {
		TestUtil._test_with_rdftype("Tutorial-G1.ttl", "http://ex.org/Topic", "Tutorial-P1.pattern", "Tutorial-Shape-G1-P1-QTopic.shex", parser, serializer);
	}

	@Test
	public void test_G1_P2_QUser() throws Exception {
		TestUtil._test_with_rdftype("Tutorial-G1.ttl", "http://ex.org/User", "Tutorial-P2.pattern", "Tutorial-Shape-G1-P2-QUser.shex", parser, serializer);
	}
	
	@Test
	public void test_G1_P2_QTopic() throws Exception {
		TestUtil._test_with_rdftype("Tutorial-G1.ttl", "http://ex.org/Topic", "Tutorial-P2.pattern", "Tutorial-Shape-G1-P2-QTopic.shex", parser, serializer);
	}
	
	@Test
	public void test_G1_P5_QUser() throws Exception {
		TestUtil._test_with_rdftype("Tutorial-G1.ttl", "http://ex.org/User", "Tutorial-P5.pattern", "Tutorial-Shape-G1-P5-QUser.shex", parser, serializer);
	}

	@Test
	public void test_G1_P6_QUser() throws Exception {
		TestUtil._test_with_rdftype("Tutorial-G1.ttl", "http://ex.org/User", "Tutorial-P6.pattern", "Tutorial-Shape-G1-P6-QUser.shex", parser, serializer);
	}
	
	@Test
	public void test_G1_P7_QUser() throws Exception {
		TestUtil._test_with_rdftype("Tutorial-G1.ttl", "http://ex.org/User", "Tutorial-P7.pattern", "Tutorial-Shape-G1-P7-QUser.shex", parser, serializer);
	}
	
	
	// -------------------------------------------------------------------------
	// Utilities
	// -------------------------------------------------------------------------
	
	private static Map<String,String> patternPrefixes = new HashMap<String, String>();
	private static PatternParsing parser;
	private static ShExCSerializer serializer;
	
	@BeforeClass
	public static void setUpClass () {
		patternPrefixes = new HashMap<String, String>();
		parser = new PatternParsing();
		
		patternPrefixes.put("ex:", "http://ex.org/");
		patternPrefixes.put("foaf:", "http://xmlns.com/foaf/0.1/");
		patternPrefixes.put("p:" ,   "http://ex.org/p");
		patternPrefixes.put("xsd:" , "http://www.w3.org/2001/XMLSchema#");
		patternPrefixes.put("rdf:" , "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		parser.setPrefixesMap(patternPrefixes);
		
		serializer = new ShExCSerializer();
		serializer.setPrefixes(patternPrefixes);
	}
	
}
