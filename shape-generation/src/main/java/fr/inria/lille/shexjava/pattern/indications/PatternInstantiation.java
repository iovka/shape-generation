/*******************************************************************************
 * Copyright (C) 2019 Université de Lille - Inria
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package fr.inria.lille.shexjava.pattern.indications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.rdf.api.BlankNodeOrIRI;
import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.Literal;
import org.apache.commons.rdf.api.RDFTerm;
import org.apache.commons.rdf.api.Triple;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

import fr.inria.lille.shexjava.pattern.abstrt.PTConstr;
import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilter;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilterIRI;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilterPrefix;

public class PatternInstantiation implements ValueSelectorInstantiation {
	
	private final Pattern origin;
	private final List<BlankNodeOrIRI> votingSample;
	private final Set<Literal> literalsSample;
	private final Graph graph;
	
	public PatternInstantiation(Pattern origin, List<RDFTerm> sample, Graph graph) {
		this.origin = origin;
		this.votingSample = new ArrayList<>();
		this.literalsSample = new HashSet<>();
		this.graph = graph;
		
		for (RDFTerm t : sample) {
			if (t instanceof BlankNodeOrIRI) {
				votingSample.add((BlankNodeOrIRI)t);
			} else if (t instanceof Literal) {
				literalsSample.add((Literal)t);
			} else {
				throw new UnsupportedOperationException("RDTerm type not supported : " + t.getClass());
			}
		}
	}

	// -----------------------------------------------------------
	// Lazy attributes
	// -----------------------------------------------------------
	
	private Map<IRI, PTConstrInstantiation> tripleConstrs = null;
	private List<PTConstr> sortedPTConstraints = null;
	private ValueSelectorValueKindInstantiation literalValuesType = null;
	
	public Collection<PTConstrInstantiation> getTripleConstraints () {
		if (tripleConstrs == null) {
			computeTripleConstraintsAndLiteralValuesType();
		}
		if (tripleConstrs != null)
			return Collections.unmodifiableCollection(tripleConstrs.values());
		else
			return null;
	}
	
	public ValueSelectorValueKindInstantiation getLiteralValuesType () {
		if (literalValuesType == null)
			computeTripleConstraintsAndLiteralValuesType();
		return literalValuesType;
	}
	
	private List<PTConstr> getSortedPTConstraints () {
		if (sortedPTConstraints == null)
			sortedPTConstraints = topologicalSort(origin.getTripleConstraints());
		return sortedPTConstraints;
	}
	
	private void computeTripleConstraintsAndLiteralValuesType () {
		
		if (! literalsSample.isEmpty())
			literalValuesType = new ValueSelectorValueKindInstantiation(new ArrayList<>(literalsSample));

		if (! votingSample.isEmpty()) {
		
			tripleConstrs = new LinkedHashMap<>();

			// Process all the triples of the sample nodes wrt the triple constraints
			for (BlankNodeOrIRI focus : votingSample) {
				for (Triple triple : graph.iterate(focus,null,null)) {
					process(triple);
				}
			}
			for (PTConstrInstantiation tc : tripleConstrs.values()) {
				tc.collectZeroCardVoters(votingSample);
			}
		}
		
		
	}

	private void process (Triple triple) {
		IRI predicate = triple.getPredicate();
		PTConstrInstantiation tcinst = tripleConstrs.get(predicate);
		if (tcinst == null) {
			PTConstr bestMatch = bestMatch(predicate);
			if (bestMatch != null) {
				tcinst = new PTConstrInstantiation(bestMatch, predicate, graph);
				tripleConstrs.put(predicate, tcinst);
			}
		}
		if (tcinst != null) {
			tcinst.addMatchingTriple(triple);
		}
	}
	
	void prettyPrint (StringBuilder s, int indent) {
		s.append("{\n");
		for (PTConstrInstantiation tc : getTripleConstraints()) {
			tc.prettyPrint(s, indent);
			s.append("\n");
		}
		s.append("}");
	}
	
	// ------------------------------------------------------------------------------------------------
	// Utility methods
	// ------------------------------------------------------------------------------------------------
	/** Returns the PTConstr of the pattern that is a best match for this predicate, or null if the predicate does not match any of the pattern PTConstr */
	PTConstr bestMatch (IRI predicate) {

		for (PTConstr ptc : getSortedPTConstraints()) {
			if (matches(predicate, ptc.getPredFilter()))
				return ptc;
		}
		return null;
	}
	
	static boolean matches (IRI predicate, PredFilter predFilter) {
		if (predFilter instanceof PredFilterIRI) {
			return predicate.equals(((PredFilterIRI) predFilter).getIRI());
		} else if (predFilter instanceof PredFilterPrefix) {
			return predicate.getIRIString().startsWith(((PredFilterPrefix) predFilter).getPrefix());
		} else {
			throw new UnsupportedOperationException("Missing case: predFilter instanceof " + predFilter.getClass());
		}
	}
	
	/** Returns true if f1 strictly more general than f2, and false if f1 less general than f2, or the two are incomparable. */
	static boolean greaterThan (PredFilter f1, PredFilter f2) {
		if (f1 instanceof PredFilterIRI) {
			return false;
		} else if (f1 instanceof PredFilterPrefix) {
			PredFilterPrefix p1 = (PredFilterPrefix) f1;
			if (f2 instanceof PredFilterPrefix) {
				PredFilterPrefix p2 = (PredFilterPrefix) f2;
				return !p2.getPrefix().equals(p1.getPrefix()) && p2.getPrefix().startsWith(p1.getPrefix());
			} else if (f2 instanceof PredFilterIRI) {
				PredFilterIRI i2 = (PredFilterIRI) f2;
				return i2.getIRI().getIRIString().startsWith(p1.getPrefix());
			} else {
				throw new UnsupportedOperationException("Missing case: f2 instanceof " + f2.getClass());
			}
		} else {
			throw new UnsupportedOperationException("Missing case: f1 instanceof " + f1.getClass());
		}
	}
	
	static List<PTConstr> topologicalSort (List<PTConstr> list) {
		// Construct the graph
		DefaultDirectedGraph<PTConstr, DefaultEdge> dep = new DefaultDirectedGraph<>(DefaultEdge.class);
		for (PTConstr tc : list) {
			dep.addVertex(tc);
		}

		for (PTConstr tc1 : list) {
			for (PTConstr tc2 : list) {
				if (greaterThan(tc1.getPredFilter(), tc2.getPredFilter())) {
					dep.addEdge(tc2, tc1);
				}
			}
		}
		
		List<PTConstr> result = new ArrayList<>(list.size());
		TopologicalOrderIterator<PTConstr, DefaultEdge> tit = new TopologicalOrderIterator<>(dep);
		while (tit.hasNext()) {
			result.add(tit.next());
		}
		return result;
	}
	
	// ------------------------------------------------------------------------------------------------
	// For testing
	// ------------------------------------------------------------------------------------------------
	Set<RDFTerm> getSample () {
		Set<RDFTerm> result = new HashSet<>(votingSample);
		result.addAll(literalsSample);
		return result;
	}

	@Override
	public String toString() {
		return "PatternInstantiation [origin=" + origin + ", votingSample=" + votingSample + ", literalsSample="
				+ literalsSample + ", graph=" + graph + ", tripleConstrs=" + tripleConstrs + ", sortedPTConstraints="
				+ sortedPTConstraints + "]";
	}
	
	
	
}
