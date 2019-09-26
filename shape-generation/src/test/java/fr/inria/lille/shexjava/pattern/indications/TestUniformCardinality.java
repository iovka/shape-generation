package fr.inria.lille.shexjava.pattern.indications;

import static fr.inria.lille.shexjava.pattern.indications.UniformCardinality.ONE;
import static fr.inria.lille.shexjava.pattern.indications.UniformCardinality.OPT;
import static fr.inria.lille.shexjava.pattern.indications.UniformCardinality.PLUS;
import static fr.inria.lille.shexjava.pattern.indications.UniformCardinality.STAR;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestUniformCardinality {
	
	@Test
	public void testMeetSame() throws Exception {
		for (UniformCardinality c : UniformCardinality.values()) {
			assertEquals(c, c.meet(c));
		}
	}
	
	@Test
	public void testMeetTop() throws Exception {
		for (UniformCardinality c : UniformCardinality.values()) {
			assertEquals(STAR, c.meet(STAR));
			assertEquals(STAR, STAR.meet(c));
		}
	}
	
	@Test
	public void testMeetBottom() throws Exception {
		for (UniformCardinality c : UniformCardinality.values()) {
			assertEquals(c, c.meet(ONE));
			assertEquals(c, ONE.meet(c));
		}
	}
	
	@Test
	public void testMeet() throws Exception {
		assertEquals(STAR, OPT.meet(PLUS));
		assertEquals(STAR, PLUS.meet(OPT));
	}

	@Test
	public void testMostSpecific() throws Exception {
		assertEquals(OPT, UniformCardinality.mostSpecific(0));
		assertEquals(ONE, UniformCardinality.mostSpecific(1));
		assertEquals(PLUS, UniformCardinality.mostSpecific(3));
	}
}
