package fr.inria.lille.shexjava.pattern;


import java.util.List;

import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.RDFTerm;
import org.eclipse.rdf4j.model.Model;

import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.pattern.indications.PatternInstantiation;
import fr.inria.lille.shexjava.pattern.indications.SHACLFromPatternConstructor;


public class ShaclGenerator {
	
	public static Model instantiate (Graph graph, List<RDFTerm> sample, Pattern pattern, String baseIRI ) {
		PatternInstantiation pi = new PatternInstantiation(pattern, sample, graph);
		SHACLFromPatternConstructor shexConstructor = new SHACLFromPatternConstructor(baseIRI);
		return shexConstructor.construct(pi);
	}
	
}
