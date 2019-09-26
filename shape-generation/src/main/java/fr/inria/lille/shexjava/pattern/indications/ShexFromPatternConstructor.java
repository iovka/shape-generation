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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.rdf.api.IRI;

import fr.inria.lille.shexjava.GlobalFactory;
import fr.inria.lille.shexjava.schema.abstrsynt.Annotation;
import fr.inria.lille.shexjava.schema.abstrsynt.EachOf;
import fr.inria.lille.shexjava.schema.abstrsynt.EmptyTripleExpression;
import fr.inria.lille.shexjava.schema.abstrsynt.NodeConstraint;
import fr.inria.lille.shexjava.schema.abstrsynt.RepeatedTripleExpression;
import fr.inria.lille.shexjava.schema.abstrsynt.Shape;
import fr.inria.lille.shexjava.schema.abstrsynt.ShapeExpr;
import fr.inria.lille.shexjava.schema.abstrsynt.ShapeOr;
import fr.inria.lille.shexjava.schema.abstrsynt.TCProperty;
import fr.inria.lille.shexjava.schema.abstrsynt.TripleConstraint;
import fr.inria.lille.shexjava.schema.abstrsynt.TripleExpr;
import fr.inria.lille.shexjava.schema.concrsynt.Constraint;
import fr.inria.lille.shexjava.schema.concrsynt.ValueSetValueConstraint;
import fr.inria.lille.shexjava.util.Interval;

public class ShexFromPatternConstructor {
	
	public static final IRI FREQ_ANNOTATION = GlobalFactory.RDFFactory.createIRI("http://inria.fr/shexjapp/freq");
	
	public ShapeExpr construct (PatternInstantiation pattern) {
		return visit(pattern);
	}

	protected ShapeExpr visit(PatternInstantiation pattern) {
		List<TripleExpr> tcs = new ArrayList<>();

		TripleExpr texpr = null;
		ShapeExpr literalsTypeExpr = null;

		Collection<PTConstrInstantiation> tripleConstraints = pattern.getTripleConstraints();
		if (tripleConstraints != null) {
			List<PTConstrInstantiation> patternSorted = tripleConstraints.stream()
					.sorted((p1,p2) -> p2.getFrequency().getSecond().compareTo(p1.getFrequency().getSecond()))
					.collect(Collectors.toList());
			for (PTConstrInstantiation ptc : patternSorted) {
				tcs.add(visit(ptc));
			}
			if (tcs.size()==0)
				texpr = new EmptyTripleExpression();
			else if (tcs.size()==1)
				texpr = tcs.get(0);
			else 
				texpr = new EachOf(tcs);	
		}
		
		ValueSelectorValueKindInstantiation literalValuesType = pattern.getLiteralValuesType();
		if (literalValuesType != null) {
			literalsTypeExpr = visit(literalValuesType);
		}
		
		if (texpr == null)
			return literalsTypeExpr;
		else if (literalsTypeExpr == null)
			return new Shape(texpr, Collections.emptySet(), false);
		else 
			return new ShapeOr(Arrays.asList(literalsTypeExpr, new Shape(texpr, Collections.emptySet(), false)));
	}

	protected TripleExpr visit(PTConstrInstantiation ptConstr) {
		ShapeExpr c = visit(ptConstr.getValue());
		Interval card = ptConstr.getCardinality().getInterval();
		TCProperty prop = TCProperty.createFwProperty(ptConstr.getPredicate());
		TripleConstraint tc = new TripleConstraint(prop, c);
		String freqString = String.format("[%d/%d]", ptConstr.getFrequency().getSecond(), ptConstr.getFrequency().getFirst());
		if (tc.getAnnotations() == null)
			tc.setAnnotations(new ArrayList<>(1));
		tc.getAnnotations().add(new Annotation(FREQ_ANNOTATION, GlobalFactory.RDFFactory.createLiteral(freqString)));
		if (card.equals(Interval.ONE))
			return tc;
		return new RepeatedTripleExpression(tc, card);

	}


	protected ShapeExpr visit(ValueSelectorInstantiation vsel) {
		Constraint c = null;
		if (vsel instanceof ValueSelectorListValuesInstantiation) {
			ValueSelectorListValuesInstantiation lv = (ValueSelectorListValuesInstantiation) vsel;
			c = new ValueSetValueConstraint(lv.getValues(), Collections.emptySet());
		} 
		if (vsel instanceof ValueSelectorValueKindInstantiation) {
			ValueSelectorValueKindInstantiation vk = (ValueSelectorValueKindInstantiation) vsel;
			UniformValueConstraint uc = vk.getConstraint();
			c = uc.getConstraint();
		}
		
		if (c == null) {
			if (vsel instanceof PatternInstantiation) {
				return this.visit((PatternInstantiation) vsel);
			} else {
				String message = String.format("Unsupported subclass of %s : %s [%s]", 
						ValueSelectorInstantiation.class, vsel.getClass(), vsel.toString());
				throw new UnsupportedOperationException(message);
			}
		} else {
			return new NodeConstraint(Collections.singletonList(c));
		}
	}

}
