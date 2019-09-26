package fr.inria.lille.shexjava.pattern.indications;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.inria.lille.shexjava.GlobalFactory;

public class TestUniformConstraint {

	
	
	
	@Test
	public void testPrefixes() throws Exception {

		UniformValueConstraint p1 = mostSpecific("http://a.b/");
		UniformValueConstraint p2 = mostSpecific("http://toto.a/");
		assertEquals(UniformValueConstraintFactory.instance().Iri, p1.meet(p2));
		assertEquals(p1, p1.meet(p1));
		
		UniformValueConstraint p3 = mostSpecific("http://a.b/");
		UniformValueConstraint p4 = mostSpecific("http://a.b/c");
		
		assertEquals(p3, p3.meet(p4));
		assertEquals(p3, p4.meet(p3));
		
		UniformValueConstraint p5 = mostSpecific("http://a.b/d");
		UniformValueConstraint p6 = mostSpecific("http://a.b/c");
		UniformValueConstraint p7 = mostSpecific("http://a.b/");
		
		assertEquals(p7, p5.meet(p6));
		assertEquals(p7, p6.meet(p5));
		
	}	
	
	public static UniformValueConstraint mostSpecific (String s) {
		return UniformValueConstraintFactory.instance().mostSpecific(GlobalFactory.RDFFactory.createIRI(s), false);
	}
}
