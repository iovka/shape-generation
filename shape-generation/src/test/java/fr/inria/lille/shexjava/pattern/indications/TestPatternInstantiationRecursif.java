package fr.inria.lille.shexjava.pattern.indications;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.RDFTerm;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.lille.shexjava.GlobalFactory;
import fr.inria.lille.shexjava.pattern.PatternParsing;
import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.schema.Label;
import fr.inria.lille.shexjava.schema.abstrsynt.ShapeExpr;

public class TestPatternInstantiationRecursif {
	public static final IRI RDF_TYPE = GlobalFactory.RDFFactory.createIRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");

	@Test
	public void test_G100_P200_() throws Exception {		
		Graph g = TestUtil.loadData("Recursif-G1.ttl");
		Pattern pattern = parser.getPattern(new ByteArrayInputStream("{ a [__]; ~ __}".getBytes()));
		System.out.println(pattern);

		Set<IRI> types = g.stream(null,RDF_TYPE,null).map(tr->(IRI) tr.getObject()).collect(Collectors.toSet());
		System.out.println(types);
		
		
		Map<IRI,PatternInstantiation> patterns = new HashMap<>();
		for(IRI type:types) {
			List<RDFTerm> sample = g.stream(null,RDF_TYPE,type).map(tr -> tr.getSubject())
					.collect(Collectors.toList());
			patterns.put(type, new PatternInstantiation(pattern, sample, g));
		}
		System.out.println(patterns);
		
		RecursiveShexFromPatternConstructor constructor = new RecursiveShexFromPatternConstructor();
		
		Map<Label, ShapeExpr> rules = constructor.construct(g, patterns);
		System.out.println(rules);
		System.out.println(serializer.ToShexC(rules));
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

		patternPrefixes.put("xsd:", "http://www.w3.org/2001/XMLSchema#");
		patternPrefixes.put("foaf:", "http://xmlns.com/foaf/0.1/");
		
		parser = new PatternParsing();
		parser.setPrefixesMap(patternPrefixes);
		
		serializer = new ShExCSerializer();
		serializer.setPrefixes(patternPrefixes);
	}
	
}
