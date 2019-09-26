package fr.inria.lille.shexjava.pattern.indications;

import static fr.inria.lille.shexjava.pattern.indications.TestCommon.ex_org_s_iri;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n1;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n1_p_v11;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n1_p_v12;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n1_q_v13;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n2;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n2_p_v21;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n2_r_v23;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n2_s_v22;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n3;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n3_other_v31;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n3_q_v32;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.n3_q_v33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.RDFTerm;
import org.junit.Test;

import fr.inria.lille.shexjava.GlobalFactory;
import fr.inria.lille.shexjava.pattern.abstrt.PTConstr;
import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilter;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilterIRI;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilterPrefix;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelectorListValues;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelectorValueKind;
import fr.inria.lille.shexjava.schema.abstrsynt.ShapeExpr;

public class TestSchemaFromPatternConstructor {

	@Test
	public void testConstruction() throws Exception {
		PredFilter fexOrgPref = new PredFilterPrefix("http://ex.org/");
		PredFilter fexComPref = new PredFilterPrefix("http://ex.com/");
		PredFilter fexOrgIri = new PredFilterIRI(ex_org_s_iri);		
		
		PTConstr exOrgPrefTC = new PTConstr(fexOrgPref, new ValueSelectorValueKind());
		PTConstr exComPrefTC = new PTConstr(fexComPref, new ValueSelectorListValues());
		PTConstr exOrgIriTC  = new PTConstr(fexOrgIri, new ValueSelectorValueKind());
		
		List<PTConstr> l = new ArrayList<>();
		l.add(exOrgPrefTC); l.add(exComPrefTC); l.add(exOrgIriTC);
		
		Pattern pattern = new Pattern(l);
					
		// n1 p v11 ; p v12 ; q v13 .
		// n2 p v21 ; s v22 ; r v23 .
		// n3 o v31 ; q v32 ; q v33 .
			
		Graph graph = GlobalFactory.RDFFactory.createGraph();
		graph.add(n1_p_v11);
		graph.add(n1_p_v12);
		graph.add(n1_q_v13);
		graph.add(n2_p_v21);
		graph.add(n2_s_v22);
		graph.add(n2_r_v23);
		graph.add(n3_other_v31);
		graph.add(n3_q_v32);
		graph.add(n3_q_v33);
		
		List<RDFTerm> sample = Arrays.asList(n1, n2, n3); 
		PatternInstantiation pi = new PatternInstantiation(pattern, sample, graph);
		
		ShexFromPatternConstructor constr = new ShexFromPatternConstructor();
		ShapeExpr se = constr.construct(pi);
		System.out.println(se.toPrettyString());
	}
	
}
