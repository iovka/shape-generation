package fr.inria.lille.shexjava.pattern.indications;

import static fr.inria.lille.shexjava.pattern.indications.TestCommon.ex_com_r_iri;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.ex_org_p_iri;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.ex_org_q_iri;
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
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.other_com_iri;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.titi_org_iri;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v11;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v12;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v13;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v21;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v22;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v23;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v32;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.v33;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.w1;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.w2;
import static fr.inria.lille.shexjava.pattern.indications.TestCommon.w3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.IRI;
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


public class TestPatternInstantiation2 {
	
	PredFilter[] f = new PredFilter[9];
	{
		f[0] = new PredFilterPrefix("http://ex.org/");
		f[1] = new PredFilterPrefix("http://a.com/");
		f[2] = new PredFilterPrefix("http://a.com/x");
		f[3] = new PredFilterPrefix("http://b.com/x");
		f[4] = new PredFilterPrefix("http://b.com/y");
		f[5] = new PredFilterIRI(GlobalFactory.RDFFactory.createIRI("http://a.com/truc"));
		f[6] = new PredFilterIRI(GlobalFactory.RDFFactory.createIRI("http://a.com/bidule"));
		f[7] = new PredFilterIRI(GlobalFactory.RDFFactory.createIRI("http://a.com/x/truc"));
		f[8] = new PredFilterIRI(GlobalFactory.RDFFactory.createIRI("http://a.com/x#bidule"));
	}
	int[][] greater = new int[][] {{1,2}, {1,5}, {1,6}, {1,7}, {1,8}, {2,7}, {2,8}};
	int[][] notGreater = new int[][] {{3,4}, {2,5}, {3,7}, {4,8}};
	
	@Test
	public void testGreaterThan() throws Exception {

		for (int[] x : greater) {
			assertTrue("False: Index " + x[0] + " > index " + x[1], PatternInstantiation.greaterThan(f[x[0]], f[x[1]]));
		}
		for (int[] x : notGreater) {
			assertFalse("False: Index " + x[0] + " !> index " + x[1], PatternInstantiation.greaterThan(f[x[0]], f[x[1]]));
		}
	}
	
	@Test
	public void testSortPatternTripleConstraints() throws Exception {
		List<PTConstr> list = new ArrayList<>();
		for (int i = 0; i < f.length; i++) {
			list.add(new PTConstr(f[i], null));
		}
		
		List<PTConstr> copy = PatternInstantiation.topologicalSort(list);
		
		// Test they are all different
		for (int i = 0; i < copy.size(); i++) {
			for (int j = 0; j < copy.size(); j++) {
				if (i != j)
					assertFalse(copy.get(i).equals(copy.get(j)));
			}
		}
			
		for (int[] x : greater) {
			int idxG = findIndex(copy, f[x[0]]);
			int idxL = findIndex(copy, f[x[1]]);
			assertTrue ("False: Index " + x[0] + " > index " + x[1], idxG > idxL);
		}
		
	}

	static int findIndex (List<PTConstr> list, PredFilter filter) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPredFilter().equals(filter)) {
				return i;
			}
		}
		return -1;
	}
	
	@Test
	public void testBestMatch() throws Exception {
		PredFilter fexOrgPref = new PredFilterPrefix("http://ex.org/");
		PredFilter fexComPref = new PredFilterPrefix("http://ex.com/");
		PredFilter fexOrgIri = new PredFilterIRI(GlobalFactory.RDFFactory.createIRI("http://ex.org/s"));
		
		PTConstr exOrgPrefTC = new PTConstr(fexOrgPref, new ValueSelectorValueKind());
		PTConstr exComPrefTC = new PTConstr(fexComPref, new ValueSelectorValueKind());
		PTConstr exOrgIriTC  = new PTConstr(fexOrgIri, new ValueSelectorValueKind());
		
		List<PTConstr> l = new ArrayList<>();
		l.add(exOrgPrefTC); l.add(exComPrefTC); l.add(exOrgIriTC);
		Pattern pattern = new Pattern(l);
		
		PatternInstantiation pi = new PatternInstantiation(pattern, Arrays.asList(), null);
		pi.getTripleConstraints();
		assertEquals(exOrgPrefTC, pi.bestMatch(ex_org_p_iri));
		assertEquals(exOrgIriTC, pi.bestMatch(ex_org_s_iri));
		assertEquals(exComPrefTC, pi.bestMatch(ex_com_r_iri));
		assertNull(pi.bestMatch(other_com_iri));
	}
	
	// Test for the Indication pattern construction
	@Test
	public void testPatternInstantiation() throws Exception {
		
		PredFilter fexOrgPref = new PredFilterPrefix("http://ex.org/");
		PredFilter fexComPref = new PredFilterPrefix("http://ex.com/");
		PredFilter fexOrgIri = new PredFilterIRI(ex_org_s_iri);		
		
		PTConstr exOrgPrefTC = new PTConstr(fexOrgPref, new ValueSelectorValueKind());
		PTConstr exComPrefTC = new PTConstr(fexComPref, new ValueSelectorListValues());
		PTConstr exOrgIriTC  = new PTConstr(fexOrgIri, new ValueSelectorValueKind());
		
		List<PTConstr> l = new ArrayList<>();
		l.add(exOrgPrefTC); l.add(exComPrefTC); l.add(exOrgIriTC);
		
		Pattern pattern = new Pattern(l);
					
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
		
		//assertEquals(pi.getMatchingTriples().size(), pi.getOriginPTCMap().size());
		//assertEquals(pi.getMatchingTriples().keySet(), pi.getOriginPTCMap().keySet());
		
		Map<IRI, PTConstrInstantiation> predicatesMap = new HashMap<>();
		for (PTConstrInstantiation i : pi.getTripleConstraints()) {
			predicatesMap.put(i.getPredicate(), i);
		}
		assertEquals(new HashSet<>(Arrays.asList(ex_org_p_iri, ex_org_q_iri, ex_org_s_iri, ex_com_r_iri)), 
				predicatesMap.keySet());
		
		assertEquals(exOrgPrefTC, predicatesMap.get(ex_org_p_iri).getOrigin());
		assertEquals(new HashSet<>(Arrays.asList(v11, v12, v21)), 
				((ValueSelectorValueKindInstantiation) predicatesMap.get(ex_org_p_iri).getValue()).getSample());
		assertEquals(UniformCardinality.STAR, predicatesMap.get(ex_org_p_iri).getCardinality());

//		assertEquals(UniformValueConstraintFactory.instance().mostSpecific(GlobalFactory.RDFFactory.createIRI("http://a.b/node/"), false), 
//				((ValueSelectorValueKindInstantiation)predicatesMap.get(ex_org_p_iri).getValue()).getConstraint());
				
		assertEquals(exOrgPrefTC, predicatesMap.get(ex_org_q_iri).getOrigin());
		assertEquals(new HashSet<>(Arrays.asList(v13, v32, v33)), 
				((ValueSelectorValueKindInstantiation) predicatesMap.get(ex_org_q_iri).getValue()).getSample());
		assertEquals(UniformCardinality.STAR, predicatesMap.get(ex_org_q_iri).getCardinality());
//		assertEquals(new UniformValueConstraintPrefix(GlobalFactory.RDFFactory.createIRI("http://a.b/")), 
//				((ValueSelectorValueKindInstantiation)predicatesMap.get(ex_org_q_iri).getValue()).getConstraint());
		
		assertEquals(exOrgIriTC, predicatesMap.get(ex_org_s_iri).getOrigin());
		assertEquals(new HashSet<>(Arrays.asList(v22)), 
				((ValueSelectorValueKindInstantiation) predicatesMap.get(ex_org_s_iri).getValue()).getSample());
		assertEquals(UniformCardinality.OPT, predicatesMap.get(ex_org_s_iri).getCardinality());
//		assertEquals(UniformValueConstraintFactory.instance().mostSpecific(GlobalFactory.RDFFactory.createIRI("http://a.b/"), false), 
//				((ValueSelectorValueKindInstantiation)predicatesMap.get(ex_org_s_iri).getValue()).getConstraint());
				
		assertEquals(exComPrefTC, predicatesMap.get(ex_com_r_iri).getOrigin());
		assertEquals(new HashSet<>(Arrays.asList(v23)), 
				((ValueSelectorListValuesInstantiation) predicatesMap.get(ex_com_r_iri).getValue()).getSample());
		
		
		// A pattern with nested pattern
		
		PredFilter ftotoOrgPref = new PredFilterPrefix("http://toto.org/");
		PTConstr totoOrgTC = new PTConstr(ftotoOrgPref, new ValueSelectorListValues());
		Pattern totoPattern = new Pattern(Arrays.asList(totoOrgTC));

		PredFilter titiOrgIri = new PredFilterIRI(titi_org_iri);
		PTConstr titiOrgTC = new PTConstr(titiOrgIri, totoPattern);

		Pattern pattern2 = new Pattern(Arrays.asList(exOrgPrefTC, titiOrgTC));
		
		graph.add(n1, titi_org_iri, w1);
		graph.add(n1, titi_org_iri, w2);
		graph.add(n2, titi_org_iri, w3);
		
		PatternInstantiation pi2 = new PatternInstantiation(pattern2, sample, graph);
		
		Map<IRI, PTConstrInstantiation> predicatesMap2 = new HashMap<>();
		for (PTConstrInstantiation i : pi2.getTripleConstraints()) {
			predicatesMap2.put(i.getPredicate(), i);
		}
		assertEquals(new HashSet<>(Arrays.asList(ex_org_p_iri, ex_org_q_iri, ex_org_s_iri, titi_org_iri)), 
				predicatesMap2.keySet());
		
		PatternInstantiation totoPatternInst = (PatternInstantiation) (predicatesMap2.get(titi_org_iri).getValue());
		assertEquals(new HashSet<>(Arrays.asList(w1, w2, w3)), totoPatternInst.getSample());
		

		StringBuilder s = new StringBuilder();
		pi2.prettyPrint(s,0);
		
		// TODO: should be a real test here
		System.out.println(s.toString());
	}
	
	
	
	
	
	
	
	
}