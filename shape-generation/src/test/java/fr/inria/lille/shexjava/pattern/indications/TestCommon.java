package fr.inria.lille.shexjava.pattern.indications;

import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.Triple;

import fr.inria.lille.shexjava.GlobalFactory;

public class TestCommon {

	static IRI n1 = GlobalFactory.RDFFactory.createIRI("http://a.b/n1");
	static IRI n2 = GlobalFactory.RDFFactory.createIRI("http://a.b/n2");
	static IRI n3 = GlobalFactory.RDFFactory.createIRI("http://a.b/n3");
	static IRI v11 = GlobalFactory.RDFFactory.createIRI("http://a.b/node/v11");
	static IRI v12 = GlobalFactory.RDFFactory.createIRI("http://a.b/node/v12");
	static IRI v13 = GlobalFactory.RDFFactory.createIRI("http://a.b/node/v13");
	static IRI v21 = GlobalFactory.RDFFactory.createIRI("http://a.b/node/v21");
	static IRI v22 = GlobalFactory.RDFFactory.createIRI("http://a.b/v22");
	static IRI v23 = GlobalFactory.RDFFactory.createIRI("http://a.b/v23");
	static IRI v31 = GlobalFactory.RDFFactory.createIRI("http://a.b/v31");
	static IRI v32 = GlobalFactory.RDFFactory.createIRI("http://a.b/v32");
	static IRI v33 = GlobalFactory.RDFFactory.createIRI("http://a.b/v33");
	
	static IRI w1 = GlobalFactory.RDFFactory.createIRI("http://a.b/w1");
	static IRI w2 = GlobalFactory.RDFFactory.createIRI("http://a.b/w2");
	static IRI w3 = GlobalFactory.RDFFactory.createIRI("http://a.b/w3");
	
	static IRI ex_org_p_iri = GlobalFactory.RDFFactory.createIRI("http://ex.org/p");  
	static IRI ex_org_q_iri = GlobalFactory.RDFFactory.createIRI("http://ex.org/q");  
	static IRI ex_com_r_iri = GlobalFactory.RDFFactory.createIRI("http://ex.com/r");
	static IRI ex_org_s_iri = GlobalFactory.RDFFactory.createIRI("http://ex.org/s");
	static IRI other_com_iri = GlobalFactory.RDFFactory.createIRI("http://other.com/o");    
	
	static IRI titi_org_iri = GlobalFactory.RDFFactory.createIRI("http://titi.org/");
	static IRI toto_org_iri = GlobalFactory.RDFFactory.createIRI("http://toto.org/");	
	
	// n1 p v11 ; p v12 ; q v13 .
	// n2 p v21 ; s v22 ; r v23 .
	// n3 o v31 ; q v32 ; q v33 .
	static Triple n1_p_v11 = GlobalFactory.RDFFactory.createTriple(n1, ex_org_p_iri, v11);
	static Triple n1_p_v12 = GlobalFactory.RDFFactory.createTriple(n1, ex_org_p_iri, v12);
	static Triple n1_q_v13 = GlobalFactory.RDFFactory.createTriple(n1, ex_org_q_iri, v13);
	static Triple n2_p_v21 = GlobalFactory.RDFFactory.createTriple(n2, ex_org_p_iri, v21);
	static Triple n2_s_v22 = GlobalFactory.RDFFactory.createTriple(n2, ex_org_s_iri, v22);
	static Triple n2_r_v23 = GlobalFactory.RDFFactory.createTriple(n2, ex_com_r_iri, v23);
	static Triple n3_other_v31 = GlobalFactory.RDFFactory.createTriple(n3, other_com_iri, v31);
	static Triple n3_q_v32 = GlobalFactory.RDFFactory.createTriple(n3, ex_org_q_iri, v32);
	static Triple n3_q_v33 = GlobalFactory.RDFFactory.createTriple(n3, ex_org_q_iri, v33);
	
}
