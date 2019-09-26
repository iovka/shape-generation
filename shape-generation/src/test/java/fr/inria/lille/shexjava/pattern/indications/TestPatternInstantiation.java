package fr.inria.lille.shexjava.pattern.indications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.rdf.api.RDFTerm;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.lille.shexjava.GlobalFactory;
import fr.inria.lille.shexjava.pattern.PatternParsing;

public class TestPatternInstantiation {
	
	@Test
	public void test_G100_P200_() throws Exception {
		List<RDFTerm> sample = new ArrayList<>();
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n1"));
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n2"));
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n3"));
		
		TestUtil._test_with_sample("Test-G100.ttl", sample, "Test-P200.pattern", 
				"Test-Shape-G100-P200.shex", parser,  serializer);
	}
	
	@Test
	public void test_G101_P201_sameTripleInSampleFromTwoNodes () throws Exception {
		List<RDFTerm> sample = new ArrayList<>();
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n1"));
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n2"));
		
		TestUtil._test_with_sample("Test-G101.ttl", sample, "Test-P201.pattern", 
				"Test-Shape-G101-P201.shex", parser, serializer);
	}
	
	@Test
	public void test_G102_P202_literalWherePatternIsToBeInferred () throws Exception {
		List<RDFTerm> sample = new ArrayList<>();
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n1"));
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n2"));
		sample.add(GlobalFactory.RDFFactory.createIRI("http://a.b/n3"));
		
		TestUtil._test_with_sample("Test-G102.ttl", sample, "Test-P201.pattern", 
				"Test-Shape-G102-P201.shex", parser,  serializer);
		
	}
	
	
	
	// -------------------------------------------------------------------------
	// Utilities
	// -------------------------------------------------------------------------
	
	private static Map<String,String> patternPrefixes;
	private static PatternParsing parser;
	private static ShExCSerializer serializer;
	

	@BeforeClass
	public static void setUpClass () {
		patternPrefixes = new HashMap<String, String>();

		patternPrefixes.put("ab:", "http://a.b/");
		patternPrefixes.put("exorg:", "http://ex.org/");
		patternPrefixes.put("excom:", "http://ex.com/");
		patternPrefixes.put("other:", "http://other.com/");
		patternPrefixes.put("p:" ,   "http://pr.op/");
		patternPrefixes.put("xsd:", "http://www.w3.org/2001/XMLSchema#");
		
		parser = new PatternParsing();
		parser.setPrefixesMap(patternPrefixes);
		
		serializer = new ShExCSerializer();
		serializer.setPrefixes(patternPrefixes);
	}
	
}
