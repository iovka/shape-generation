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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.rdf.api.BlankNodeOrIRI;
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.RDFTerm;
import org.apache.commons.rdf.simple.Types;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

import com.google.common.collect.Lists;

import fr.inria.lille.shexjava.GlobalFactory;
import fr.inria.lille.shexjava.schema.concrsynt.Constraint;
import fr.inria.lille.shexjava.schema.concrsynt.DatatypeConstraint;
import fr.inria.lille.shexjava.schema.concrsynt.IRIStemConstraint;
import fr.inria.lille.shexjava.schema.concrsynt.NodeKindConstraint;
import fr.inria.lille.shexjava.schema.concrsynt.ValueConstraint;
import fr.inria.lille.shexjava.schema.concrsynt.ValueSetValueConstraint;
import fr.inria.lille.shexjava.schema.concrsynt.WildcardConstraint;

/** A value constraint of a pattern, and a factory for creating such value constraints.
 * 
 * @author Iovka Boneva
 *
 */
public class UniformValueConstraintFactory  {
	
	private UniformValueConstraintFactory () {
		typeConstraints = new HashMap<>();
		prefixConstraints = new HashMap<>();
		typeHierarchy = new DirectedAcyclicGraph<>(DefaultEdge.class);
		pathFinder = new AllDirectedPaths<>(typeHierarchy);
		addNodeKindConstraintsToHierarchy();
		addLiteralTypeConstraintsToHierarchy();
	}
	private static UniformValueConstraintFactory instance = new UniformValueConstraintFactory();
	public static UniformValueConstraintFactory instance() { return instance; }
	
	/** Returns the most specific value constraint satisfied by the term. 
	 * @param value The term for which the constraint is to be computed
	 * @param iriOnly if true, indicates that the most specific for IRI values is {@link #Iri}
	 * @return
	 */
	public UniformValueConstraint mostSpecific (RDFTerm value, boolean iriOnly) {
		if (value instanceof org.apache.commons.rdf.api.BlankNode) return Blank;
		if (value instanceof IRI) {
			if (iriOnly) return Iri;
			else {
				String iri = ((IRI) value).getIRIString(); 
				return getPrefixConstraint(GlobalFactory.RDFFactory.createIRI(iri.substring(0, iri.lastIndexOf('/'))));
			}
		}
		if (value instanceof BlankNodeOrIRI) return Nonlit;
		if (value instanceof org.apache.commons.rdf.api.Literal) {
			org.apache.commons.rdf.api.Literal lit = (org.apache.commons.rdf.api.Literal) value;
			return getLiteralTypeConstraint(lit.getDatatype());
		}
		throw new UnsupportedOperationException("Unsupported subclass of RDFTerm : " + value.getClass());
	}
	
	public UniformValueConstraint meet (UniformValueConstraint one, UniformValueConstraint two) {
		if (one.equals(two))
			return one;
		
		List<UniformValueConstraint> tpath = Lists.reverse(pathFinder.getAllPaths(one, Any, true, null)
				.get(0).getVertexList());
		List<UniformValueConstraint> opath = Lists.reverse(pathFinder.getAllPaths(two, Any, true, null)
				.get(0).getVertexList());
		
		UniformValueConstraint result = Any;
		
		int i = 1;
		while (i < tpath.size() && i < opath.size() && tpath.get(i).equals(opath.get(i))) {
			result = tpath.get(i);
			i++;
		}
		return result;
	}
	
	
	
	
	// -------------------------------------------------------------------------------------------------
	// Type hierarchy
	// -------------------------------------------------------------------------------------------------	
	private Map<IRI, UniformValueConstraintTypedLiteral> typeConstraints;
	private Map<IRI, UniformValueConstraintPrefix> prefixConstraints;
	private DirectedAcyclicGraph<UniformValueConstraint, DefaultEdge> typeHierarchy;
	private AllDirectedPaths<UniformValueConstraint, DefaultEdge> pathFinder;
	
	// -------------------------------------------------------------------------------------------------
	// Node kind constraints
	// -------------------------------------------------------------------------------------------------
	abstract class UniformValueConstraintAbstract implements UniformValueConstraint {
		@Override
		public UniformValueConstraint meet(UniformValueConstraint other) {
			return instance().meet(this, other);
		}
	}
	
	public final UniformValueConstraint Literal = new UniformValueConstraintAbstract() {
		@Override
		public Constraint getConstraint() {
			return NodeKindConstraint.LiteralKind;
		}
		@Override
		public String toString() {
			return "UniformValueConstraint: Literal";
		}
	}; 
	public final UniformValueConstraint Blank = new UniformValueConstraintAbstract() {
		@Override
		public Constraint getConstraint() {
			return NodeKindConstraint.BNodeKind;
		}
		@Override
		public String toString() {
			return "UniformValueConstraint: Blank";
		}
	};
	public final UniformValueConstraint Iri = new UniformValueConstraintAbstract() {
		@Override
		public Constraint getConstraint() {
			return NodeKindConstraint.IRIKind;
		}
		@Override
		public String toString() {
			return "UniformValueConstraint: Iri";
		}
	};
	public final UniformValueConstraint Nonlit = new UniformValueConstraintAbstract() {
		@Override
		public Constraint getConstraint() {
			return NodeKindConstraint.NonLiteralKind;
		}
		@Override
		public String toString() {
			return "UniformValueConstraint: Nonliteral";
		}
	};
	public final UniformValueConstraint Any = new UniformValueConstraintAbstract() {
		@Override
		public Constraint getConstraint() {
			Set<ValueConstraint> cst = new HashSet<>();
			cst.add(new WildcardConstraint());
			return new ValueSetValueConstraint(Collections.emptySet(), cst);
		}
		@Override
		public String toString() {
			return "UniformValueConstraint: Any";
		}
	};
		
	private void addNodeKindConstraintsToHierarchy () {
		typeHierarchy.addVertex(Literal);
		typeHierarchy.addVertex(Blank);
		typeHierarchy.addVertex(Iri);
		typeHierarchy.addVertex(Nonlit);
		typeHierarchy.addVertex(Any);
		typeHierarchy.addEdge(Literal, Any);
		typeHierarchy.addEdge(Nonlit, Any);
		typeHierarchy.addEdge(Blank, Nonlit);
		typeHierarchy.addEdge(Iri, Nonlit);
	}
	
	// -------------------------------------------------------------------------------------------------
	// Literal type constraints
	// -------------------------------------------------------------------------------------------------
	private final UniformValueConstraint getLiteralTypeConstraint (IRI type) {
		UniformValueConstraintTypedLiteral res = typeConstraints.get(type);
		if (res == null) {
			res = new UniformValueConstraintTypedLiteral(type);
			typeConstraints.put(type, res);
			if (! typeHierarchy.containsVertex(res)) {
				// Custom literal type
				typeHierarchy.addVertex(res);
			}
			typeHierarchy.addEdge(res, Literal);
			 
				
		}
		return res;
	}
	
	// Create the constraints for standard RDF 1.1 literal types
	private void addLiteralTypeConstraintsToHierarchy () {
		for (IRI t : Types.values()) {
			UniformValueConstraintTypedLiteral c = new UniformValueConstraintTypedLiteral(t);
			typeConstraints.put(t, c);
			typeHierarchy.addVertex(c);
		}
		
		// The top level RDF 1.1 datatypes
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_STRING), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_BOOLEAN), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_BASE64BINARY), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_HEXBINARY), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_FLOAT), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_DECIMAL), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_DOUBLE), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_ANYURI), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_DURATION), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_DAYTIMEDURATION), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_DATETIME), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_TIME), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_DATE), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_GYEARMONTH), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_GYEAR), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_GMONTHDAY), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_GDAY), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_GMONTH), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.RDF_HTML), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.RDF_LANGSTRING), Literal);
		typeHierarchy.addEdge(typeConstraints.get(Types.RDF_XMLLITERAL), Literal);

		// The sub-hierarchy of integer
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_UNSIGNEDBYTE), typeConstraints.get(Types.XSD_UNSIGNEDSHORT));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_UNSIGNEDSHORT), typeConstraints.get(Types.XSD_UNSIGNEDINT));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_UNSIGNEDINT), typeConstraints.get(Types.XSD_UNSIGNEDLONG));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_POSITIVEINTEGER), typeConstraints.get(Types.XSD_NONNEGATIVEINTEGER));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_UNSIGNEDLONG), typeConstraints.get(Types.XSD_NONNEGATIVEINTEGER));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_BYTE), typeConstraints.get(Types.XSD_SHORT));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_SHORT), typeConstraints.get(Types.XSD_INT));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_INT), typeConstraints.get(Types.XSD_LONG));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_NEGATIVEINTEGER), typeConstraints.get(Types.XSD_NONPOSITIVEINTEGER));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_NONPOSITIVEINTEGER), typeConstraints.get(Types.XSD_INTEGER));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_LONG), typeConstraints.get(Types.XSD_INTEGER));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_NONNEGATIVEINTEGER), typeConstraints.get(Types.XSD_INTEGER));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_INTEGER), typeConstraints.get(Types.XSD_DECIMAL));
		
		// The sub-hierarchy of string
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_NCNAME), typeConstraints.get(Types.XSD_NAME));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_NMTOKEN), typeConstraints.get(Types.XSD_TOKEN));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_NAME), typeConstraints.get(Types.XSD_TOKEN));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_LANGUAGE), typeConstraints.get(Types.XSD_TOKEN));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_TOKEN), typeConstraints.get(Types.XSD_NORMALIZEDSTRING));
		typeHierarchy.addEdge(typeConstraints.get(Types.XSD_NORMALIZEDSTRING), typeConstraints.get(Types.XSD_STRING));
	}
	
	class UniformValueConstraintTypedLiteral extends UniformValueConstraintAbstract {

		private IRI type;
		private UniformValueConstraintTypedLiteral(IRI type) {
			this.type = type;
		}

		@Override
		public Constraint getConstraint() {
			return new DatatypeConstraint(type);
		}
				
		void prettyPrint(StringBuilder s) {
			s.append(type);
		}
	}
	
	// -------------------------------------------------------------------------------------------------
	// Prefix constraints
	// -------------------------------------------------------------------------------------------------
	private final UniformValueConstraintPrefix getPrefixConstraint (IRI prefix) {
		UniformValueConstraintPrefix res = prefixConstraints.get(prefix);
		if (res == null) {
			res = new UniformValueConstraintPrefix(prefix);
			prefixConstraints.put(prefix, res);
			typeHierarchy.addVertex(res);
			insertInTypeHierarchy(res);
		}
		return res;
	}
	
	private void insertInTypeHierarchy (UniformValueConstraintPrefix constr) {
		UniformValueConstraintPrefix parent = constr;

		for (UniformValueConstraintPrefix p : prefixConstraints.values()) {
			if (p.isStrictPrefixOf(constr) && parent.isStrictPrefixOf(p))
				parent = p;
		}

		if (parent.equals(constr))
			typeHierarchy.addEdge(constr, Iri);
		else
			typeHierarchy.addEdge(constr, parent);
		
		Set<UniformValueConstraint> children = typeHierarchy.getAncestors(parent);
		for (UniformValueConstraint v : children) {
			UniformValueConstraintPrefix p = (UniformValueConstraintPrefix) v;
			if (constr.isStrictPrefixOf(p)) {
				typeHierarchy.removeEdge(p, parent);
				typeHierarchy.addEdge(p, constr);
			}
		}
	}
	
	class UniformValueConstraintPrefix extends UniformValueConstraintAbstract {

		private IRI prefix;
		private UniformValueConstraintPrefix(IRI prefix) {
			this.prefix = prefix;
		}
		IRI getPrefix () {
			return prefix;
		}
		
		@Override
		public Constraint getConstraint() {
			Set<ValueConstraint> cst = new HashSet<>();
			cst.add(new IRIStemConstraint(prefix.getIRIString()));
			return new ValueSetValueConstraint(Collections.emptySet(), cst);
		}
		
		@Override
		public String toString() {
			return "UniformValueConstraintPrefix [prefix="+prefix+"]";
		}
		
		boolean isStrictPrefixOf (UniformValueConstraintPrefix other) {
			return this.prefix.getIRIString().length() < other.prefix.getIRIString().length() &&
					other.prefix.getIRIString().startsWith(this.prefix.getIRIString());
		}
		
		void prettyPrint (StringBuilder s) {
			s.append(prefix);
		}
	}
}
