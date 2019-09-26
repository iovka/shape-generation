package fr.inria.lille.shexjava.pattern;


import java.util.List;

import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.RDFTerm;

import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.pattern.indications.PatternInstantiation;
import fr.inria.lille.shexjava.pattern.indications.ShexFromPatternConstructor;
import fr.inria.lille.shexjava.schema.abstrsynt.ShapeExpr;


public class ShexGenerator {
	
	public static ShapeExpr instantiate (Graph graph, List<RDFTerm> sample, Pattern pattern ) {
		PatternInstantiation pi = new PatternInstantiation(pattern, sample, graph);
		ShexFromPatternConstructor shexConstructor = new ShexFromPatternConstructor();
		return shexConstructor.construct(pi);
	}
	
}
