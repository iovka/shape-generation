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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.rdf.api.BlankNodeOrIRI;
import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.RDFTerm;
import org.apache.commons.rdf.api.Triple;
import org.jgrapht.alg.util.Pair;

import fr.inria.lille.shexjava.pattern.abstrt.PTConstr;
import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelector;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelectorListValues;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelectorValueKind;

public class PTConstrInstantiation implements IASElementInstantiation{

	private final PTConstr origin;
	private final IRI predicate;
	private final Graph graph;
	
	public PTConstrInstantiation (PTConstr origin, IRI predicate, Graph graph) {
		this.origin = origin;
		this.predicate = predicate;
		this.graph = graph;
	}
	
	public IRI getPredicate() {
		return predicate;
	}
	public PTConstr getOrigin () {
		return origin;
	}

	// ----------------------------------------------------------
	// Dynamic attributes, modifiable in the package only
	// ----------------------------------------------------------
	private Set<Triple> matchingTriples = new HashSet<>();
	private Map<BlankNodeOrIRI, Integer> cardVotes = new HashMap<>();
	private int[] frequency = new int[2];
	
	void addMatchingTriple (Triple triple) {
		if (matchingTriples.add(triple)) {
			addOneToCard(triple.getSubject());
		}
	}
	
	/** Takes as input all the sample nodes used while instantiating the parent {@link Pattern}
	 * Adds as zero voters those that were not objects of some matching triple
	 * 
	 * @param allSampleNodes
	 */
	void collectZeroCardVoters (Iterable<BlankNodeOrIRI> allSampleNodes ) {
		for (BlankNodeOrIRI n : allSampleNodes) {
			frequency[0]++;
			if (cardVotes.get(n) == null) {
				cardVotes.put(n, 0);
				frequency[1]++;
			}
		}
		frequency[1] = frequency[0]-frequency[1];
	}
	
	private void addOneToCard (BlankNodeOrIRI focus) {
		Integer i = cardVotes.get(focus);
		if (i == null) {
			cardVotes.put(focus, 1);
		} else {
			cardVotes.put(focus, i+1);
		}
	}
	
	/** Total number of voting nodes / number of voting nodes that have the property
	 * To be called after {@link #collectZeroCardVoters(Iterable)}
	 * @return
	 */
	Pair<Integer, Integer> getFrequency () {
		return new Pair<>(frequency[0], frequency[1]);
	}

	// ----------------------------------------------------------
	// Lazy attributes
	// ----------------------------------------------------------	
	private ValueSelectorInstantiation value = null;
	private UniformCardinality card = null;

	public ValueSelectorInstantiation getValue () {
		if (value == null)
			computeValue();
		return value;
	}

	public UniformCardinality getCardinality () {
		if (card == null)
			computeCard();
		return card;
	}
	
	private void computeValue () {
		ValueSelector vsel = origin.getValueSelector();
		List<RDFTerm> newSample = new ArrayList<>();
		for (Triple triple: matchingTriples) 
			newSample.add(triple.getObject());
		
		if (vsel instanceof Pattern) {
			value = new PatternInstantiation((Pattern) vsel, newSample, graph);
		} else if (vsel instanceof ValueSelectorListValues) {
			this.value = new ValueSelectorListValuesInstantiation(newSample); 
		} else if (vsel instanceof ValueSelectorValueKind) {
			this.value = new ValueSelectorValueKindInstantiation(newSample);
		} else {
			throw new UnsupportedOperationException("Unknown class for ValueSelector : " + vsel.getClass());
		}
	}
	
	private void computeCard () {
		Iterator<Integer> it = cardVotes.values().iterator();
		UniformCardinality uc = UniformCardinality.mostSpecific(it.next());
		while (it.hasNext()) {
			uc = uc.meet(UniformCardinality.mostSpecific(it.next()));
		}
		card = uc;
	}

	public void prettyPrint(StringBuilder s, int indent) {
		s.append(predicate);
		s.append(" ");
		ValueSelectorInstantiation vsel = getValue();
		if (vsel instanceof PatternInstantiation) {
			((PatternInstantiation) vsel).prettyPrint(s, indent);
		} else if (vsel instanceof ValueSelectorListValuesInstantiation) {
			s.append(((ValueSelectorListValuesInstantiation)vsel).getValues());
		} else if (vsel instanceof ValueSelectorValueKindInstantiation) {
			ValueSelectorValueKindInstantiation v = (ValueSelectorValueKindInstantiation) vsel;
			UniformValueConstraint c = v.getConstraint();
			s.append(c.toString());
		} else {
			throw new UnsupportedOperationException("Unknown class for ValueSelectorInstantiation : " + vsel.getClass());
		}
	}

	@Override
	public String toString() {
		return "PTConstrInstantiation [origin=" + origin + ", predicate=" + predicate + ", graph=" + graph
				+ ", matchingTriples=" + matchingTriples + ", cardVotes=" + cardVotes + ", value=" + value + ", card="
				+ card + "]";
	}
	
	
	
}
