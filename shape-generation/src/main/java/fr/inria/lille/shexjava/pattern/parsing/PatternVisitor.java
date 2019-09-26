// Generated from fr/inria/lille/pattern/parsing/Pattern.g4 by ANTLR 4.7.1
package fr.inria.lille.shexjava.pattern.parsing;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PatternParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PatternVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PatternParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(PatternParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#prefixDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixDecl(PatternParser.PrefixDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#pTConstr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPTConstr(PatternParser.PTConstrContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#predFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredFilter(PatternParser.PredFilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#valueSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueSelector(PatternParser.ValueSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#listValues}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListValues(PatternParser.ListValuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#valueKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueKind(PatternParser.ValueKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#valueKindStem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueKindStem(PatternParser.ValueKindStemContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(PatternParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(PatternParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#rdfType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRdfType(PatternParser.RdfTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#iri}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIri(PatternParser.IriContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#prefixedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixedName(PatternParser.PrefixedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PatternParser#prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefix(PatternParser.PrefixContext ctx);
}