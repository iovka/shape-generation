package fr.inria.lille.shexjava.pattern.indications;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.RDFTerm;
import org.apache.commons.rdf.api.Triple;
import org.apache.commons.rdf.rdf4j.RDF4J;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import fr.inria.lille.shexjava.GlobalFactory;
import fr.inria.lille.shexjava.pattern.PatternParsing;
import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.schema.ShexSchema;
import fr.inria.lille.shexjava.schema.abstrsynt.ShapeExpr;
import fr.inria.lille.shexjava.schema.parsing.GenParser;

public class TestUtil {

	public static Graph loadData (String resourceName) throws IOException {
		URL file = TestUtil.class.getResource(resourceName);
		InputStream inputStream = file.openStream();
		Model model = Rio.parse(inputStream, file.toString(), RDFFormat.TURTLE);
		return new RDF4J().asGraph(model);
	}
	
	private static Pattern getPattern (String resourceName, PatternParsing parser) throws Exception {
		URL file = TestUtil.class.getResource(resourceName);
		InputStream inputStream = file.openStream();
		return parser.getPattern(inputStream);
	}
	
	private static List<RDFTerm> getSample (Graph graph, String rdfType) {
		List<RDFTerm> sample = new ArrayList<>();
		for (Triple t : graph.iterate(null, GlobalFactory.RDFFactory.createIRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"),
				GlobalFactory.RDFFactory.createIRI(rdfType))) {
			sample.add(t.getSubject());
		}
		return sample;
	}
	
	private static ShapeExpr constructShape (Graph graph, Pattern pattern, List<RDFTerm> sample) {
		PatternInstantiation inst = new PatternInstantiation(pattern, sample , graph);

		ShexFromPatternConstructor constr = new ShexFromPatternConstructor();
		return constr.construct(inst);
	}
	
	/** Returns the first ShapeExpr of the schema in the given file. */
	private static ShapeExpr getExpectedShape (String resourceName) throws URISyntaxException, Exception {
		
		URL file = TestUtil.class.getResource(resourceName);
		
		ShexSchema sch = GenParser.parseSchema(Paths.get(file.toURI()));
		return sch.getRules().values().iterator().next();
	}
	
	public static void _test_with_rdftype (String graphResource, String rdfType, 
			String patternResource, String expectedSchemaResource,
			PatternParsing parser, ShExCSerializer serializer) throws Exception {
		Graph G1 = loadData(graphResource);
		List<RDFTerm> sample = getSample(G1, rdfType);
		
		_test_common(G1, sample, patternResource, expectedSchemaResource, parser, serializer);
	}
	
	public static void _test_with_sample (String graphResource, List<RDFTerm> sample, 
			String patternResource, String expectedSchemaResource,
			PatternParsing parser, ShExCSerializer serializer) throws Exception {
		Graph G1 = loadData(graphResource);
		
		_test_common(G1, sample, patternResource, expectedSchemaResource, parser, serializer);
	}
	
	private static void _test_common (Graph G1, List<RDFTerm> sample,
			String patternResource, String expectedSchemaResource,
			PatternParsing parser, ShExCSerializer serializer) throws Exception {
		
		Pattern P1 = getPattern(patternResource, parser);
		ShapeExpr expected = getExpectedShape(expectedSchemaResource);

		ShapeExpr extracted = constructShape(G1, P1, sample);
		
		System.out.println(serializer.convertShapeExpr(extracted));
		
		if (! SchemaEquality.areEqualsShapeExpr(extracted, expected)) {
			fail("Extracted shape differs from expected shape." 
					+ "\n**Extracted: " 
					+ serializer.convertShapeExpr(extracted) 
					+ "\n**Expected: "
					+ serializer.convertShapeExpr(expected));
		}		

		
	}
	
}
