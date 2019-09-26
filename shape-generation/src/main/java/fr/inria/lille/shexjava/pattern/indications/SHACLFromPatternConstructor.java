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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.rdf.api.BlankNodeOrIRI;
import org.apache.commons.rdf.api.Graph;
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.RDFTerm;
import org.apache.commons.rdf.rdf4j.RDF4JGraph;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.SHACL;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;

import fr.inria.lille.shexjava.pattern.ProjectFactory;
import fr.inria.lille.shexjava.pattern.indications.UniformValueConstraintFactory.UniformValueConstraintPrefix;
import fr.inria.lille.shexjava.schema.concrsynt.Constraint;
import fr.inria.lille.shexjava.schema.concrsynt.DatatypeConstraint;
import fr.inria.lille.shexjava.schema.concrsynt.NodeKindConstraint;
import fr.inria.lille.shexjava.util.Interval;

public class SHACLFromPatternConstructor {
	private String baseIRI;
	
	
	public SHACLFromPatternConstructor(String baseIRI) {
		super();
		this.baseIRI = baseIRI;
	}


	protected Set<String> generatedName;
	public Model construct (PatternInstantiation pattern) {
		generatedName = new HashSet<>();
		Model result = new TreeModel();
		RDF4JGraph model = ProjectFactory.factory.asGraph(result);
		visit(pattern,model);
		return result;
	}

	
	private BlankNodeOrIRI visit(PatternInstantiation pattern, Graph result) {
		BlankNodeOrIRI root = generateShapeName(pattern);

		List<PTConstrInstantiation> patternSorted = pattern.getTripleConstraints().stream()
				.sorted((p1,p2) -> p1.getPredicate().getIRIString().compareTo(p2.getPredicate().getIRIString()))
				.collect(Collectors.toList());
	
		for (PTConstrInstantiation ptc : patternSorted) {
			if (ptc.getPredicate().equals(ProjectFactory.factory.asRDFTerm(RDF.TYPE))) {
				if (ptc.getValue() instanceof ValueSelectorListValuesInstantiation) {
					ValueSelectorListValuesInstantiation values = (ValueSelectorListValuesInstantiation) ptc.getValue();
					for (RDFTerm val:values.getValues()) {
						result.add(root, ProjectFactory.factory.asRDFTerm(SHACL.TARGET_CLASS), val);
					}
				}
			} else {
				BlankNodeOrIRI rootTC = generatePropertyName(root,ptc);
				result.add(root, ProjectFactory.factory.asRDFTerm(SHACL.PROPERTY), rootTC);
				visit(ptc,result,rootTC);
			}
		}

		result.add(root, ProjectFactory.factory.asRDFTerm(RDF.TYPE), ProjectFactory.factory.asRDFTerm(SHACL.NODE_SHAPE));
		return root;
	}

	
	private void visit(PTConstrInstantiation ptConstr, Graph result, BlankNodeOrIRI root) {
		Interval card = ptConstr.getCardinality().getInterval();
		result.add(root, ProjectFactory.factory.asRDFTerm(SHACL.MIN_COUNT), 
				ProjectFactory.factory.createLiteral(card.min+"", ProjectFactory.factory.asRDFTerm(XMLSchema.INTEGER)));
		if (!card.isUnbound())
			result.add(root, ProjectFactory.factory.asRDFTerm(SHACL.MAX_COUNT), 
					ProjectFactory.factory.createLiteral(card.max+"", ProjectFactory.factory.asRDFTerm(XMLSchema.INTEGER)));
		
		result.add(root,ProjectFactory.factory.asRDFTerm(SHACL.PATH),ptConstr.getPredicate());
		
		visit(ptConstr.getValue(),result,root);
	}


	private void visit(ValueSelectorInstantiation vsel, Graph result, BlankNodeOrIRI root) {
		if (vsel instanceof ValueSelectorListValuesInstantiation) {
			ValueSelectorListValuesInstantiation lv = (ValueSelectorListValuesInstantiation) vsel;
			for (RDFTerm value: lv.getValues()) 
				result.add(ProjectFactory.factory.createTriple(root, ProjectFactory.factory.asRDFTerm(SHACL.IN),value));
			return ;			
		} 
		if (vsel instanceof ValueSelectorValueKindInstantiation) {
			ValueSelectorValueKindInstantiation vk = (ValueSelectorValueKindInstantiation) vsel;
			UniformValueConstraint uc = vk.getConstraint();
			Constraint c = uc.getConstraint();
			if (c.equals(NodeKindConstraint.IRIKind) || uc instanceof UniformValueConstraintPrefix)
				result.add(ProjectFactory.factory.createTriple(root, ProjectFactory.factory.asRDFTerm(SHACL.NODE_KIND), 
						ProjectFactory.factory.asRDFTerm(SHACL.IRI)));
			if (c.equals(NodeKindConstraint.BNodeKind))
				result.add(ProjectFactory.factory.createTriple(root, ProjectFactory.factory.asRDFTerm(SHACL.NODE_KIND), 
						ProjectFactory.factory.asRDFTerm(SHACL.BLANK_NODE)));
			if (c.equals(NodeKindConstraint.LiteralKind))
				result.add(ProjectFactory.factory.createTriple(root, ProjectFactory.factory.asRDFTerm(SHACL.NODE_KIND), 
						ProjectFactory.factory.asRDFTerm(SHACL.LITERAL)));
			if (c.equals(NodeKindConstraint.NonLiteralKind))
				result.add(ProjectFactory.factory.createTriple(root, ProjectFactory.factory.asRDFTerm(SHACL.NODE_KIND), 
						ProjectFactory.factory.asRDFTerm(SHACL.BLANK_NODE_OR_IRI)));
			if (c instanceof DatatypeConstraint)
				result.add(ProjectFactory.factory.createTriple(root, ProjectFactory.factory.asRDFTerm(SHACL.DATATYPE), 
						((DatatypeConstraint) c).getDatatypeIri()));
			return ;
		}
		if (vsel instanceof PatternInstantiation) {
			BlankNodeOrIRI subShape = this.visit((PatternInstantiation) vsel,result);
			result.add(ProjectFactory.factory.createTriple(root, ProjectFactory.factory.asRDFTerm(SHACL.NODE), subShape));
		} else {
			String message = String.format("Unsupported subclass of %s : %s [%s]", 
					ValueSelectorInstantiation.class, vsel.getClass(), vsel.toString());
			throw new UnsupportedOperationException(message);
		}
	}
	
	//--------------------------------------------
	// Name generation utils
	//--------------------------------------------
	
	private BlankNodeOrIRI generatePropertyName(BlankNodeOrIRI root, PTConstrInstantiation ptConstr) {
		if (root instanceof IRI) {
			String baseName = baseIRI+getLastPartOfIRI(ptConstr.getPredicate())+getLastPartOfIRI((IRI) root)+"Property";
			if (generatedName.contains(baseName)) {
				int i = 2;
				while (generatedName.contains(baseName+"_"+i)) i++;
				baseName += "_"+i;
			}
			generatedName.add(baseName);
			return (ProjectFactory.factory.createIRI(baseName));
		}

		return ProjectFactory.factory.createBlankNode();
	}

	
	private BlankNodeOrIRI generateShapeName(PatternInstantiation pattern) {
		for (PTConstrInstantiation ptc : pattern.getTripleConstraints())
			if (ptc.getPredicate().equals(ProjectFactory.factory.asRDFTerm(RDF.TYPE))) 
				if (ptc.getValue() instanceof ValueSelectorListValuesInstantiation)
					for (RDFTerm val:((ValueSelectorListValuesInstantiation) ptc.getValue()).getValues()) 
						if (val instanceof IRI) {
							String baseName =  baseIRI+getLastPartOfIRI((IRI) val)+"Shape";
							if (generatedName.contains(baseName)) {
								int i = 2;
								while (generatedName.contains(baseName+"_"+i)) i++;
								baseName += "_"+i;
							}
							generatedName.add(baseName);
							return (ProjectFactory.factory.createIRI(baseName));
						}
		return ProjectFactory.factory.createBlankNode();
	}
	
	private String getLastPartOfIRI(IRI iri) {
		org.eclipse.rdf4j.model.IRI val = (org.eclipse.rdf4j.model.IRI) ProjectFactory.factory.asValue(iri);
		return val.getLocalName();
	}
}
