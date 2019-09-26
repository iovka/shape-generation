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
package fr.inria.lille.shexjava.pattern;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.apache.commons.rdf.api.IRI;

import fr.inria.lille.shexjava.pattern.abstrt.PTConstr;
import fr.inria.lille.shexjava.pattern.abstrt.Pattern;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilter;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilterIRI;
import fr.inria.lille.shexjava.pattern.abstrt.PredFilterPrefix;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelector;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelectorIriPrefix;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelectorListValues;
import fr.inria.lille.shexjava.pattern.abstrt.ValueSelectorValueKind;
import fr.inria.lille.shexjava.pattern.parsing.PatternBaseVisitor;
import fr.inria.lille.shexjava.pattern.parsing.PatternErrorListener;
import fr.inria.lille.shexjava.pattern.parsing.PatternErrorStrategy;
import fr.inria.lille.shexjava.pattern.parsing.PatternLexer;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.FilterContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.IriContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.ListValuesContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.PTConstrContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.PatternContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.PredFilterContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.PredicateContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.PrefixContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.PrefixDeclContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.PrefixedNameContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.RdfTypeContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.ValueKindContext;
import fr.inria.lille.shexjava.pattern.parsing.PatternParser.ValueSelectorContext;


public class PatternParsing extends PatternBaseVisitor<Object>{
	Map<String,String> prefixes = new HashMap<>();
	private Map<String,String> prefixesParsed;

	
	public Pattern getPattern(InputStream is) throws Exception{
		Reader isr = new InputStreamReader(is,Charset.defaultCharset().name());
		CharStream inputStream = CharStreams.fromReader(isr);
		PatternLexer lexer = new PatternLexer(inputStream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(new PatternErrorListener());
		
		CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
		PatternParser parser = new PatternParser(commonTokenStream);   
		parser.removeErrorListeners();
		parser.addErrorListener(new PatternErrorListener());
		parser.setErrorHandler(new PatternErrorStrategy());
		
		prefixesParsed = new HashMap<>();
		Pattern result = this.visitPattern(parser.pattern());
		return result;
	}
	

	@Override
	public Pattern visitPattern(PatternContext ctx) {
		for (PrefixDeclContext preCtx:ctx.prefixDecl())
			this.visitPrefixDecl(preCtx);
		List<PTConstr> result = new ArrayList<>();
		for (PTConstrContext sub:ctx.pTConstr())
			result.add(this.visitPTConstr(sub));
		return new Pattern(result);
	}
	
	@Override
	public Object visitPrefixDecl(PrefixDeclContext ctx) {
		String pre = ctx.PNAME_NS().getText();
		String iri = ctx.IRIREF().getText();
				
		prefixesParsed.put(pre, iri.substring(1,iri.length()-1));
		return null;
	}

	@Override
	public PTConstr visitPTConstr(PTConstrContext ctx) {
		return new PTConstr(this.visitPredFilter(ctx.predFilter()), this.visitValueSelector(ctx.valueSelector()));
	}

	@Override
	public PredFilter visitPredFilter(PredFilterContext ctx) {
		if (ctx.predicate()!=null)
			return new PredFilterIRI(visitPredicate(ctx.predicate()));
		return this.visitFilter(ctx.filter());
	}
	
	
	@Override
	public PredFilterPrefix visitFilter(FilterContext ctx) {
		if (ctx.predicate()!=null)
			return new PredFilterPrefix(visitPredicate(ctx.predicate()).getIRIString());
		if (ctx.prefix()!=null)
			return new PredFilterPrefix(visitPrefix(ctx.prefix()));
		return new PredFilterPrefix("");

	}
	

	@Override
	public ValueSelector visitValueSelector(ValueSelectorContext ctx) {
		if (ctx.listValues()!=null) return this.visitListValues(ctx.listValues());
		if (ctx.valueKind()!=null) return this.visitValueKind(ctx.valueKind());
		if (ctx.pattern()!=null) return this.visitPattern(ctx.pattern());
		return null;
	}
	

	@Override
	public ValueSelectorListValues visitListValues(ListValuesContext ctx) {
		return new ValueSelectorListValues();
	}

	@Override
	public ValueSelectorValueKind visitValueKind(ValueKindContext ctx) {
		return new ValueSelectorValueKind();
	}
	
	@Override 
	public ValueSelectorIriPrefix visitValueKindStem(PatternParser.ValueKindStemContext ctx) { 
		return new ValueSelectorIriPrefix(); 
	}


	@Override
	public IRI visitPredicate(PredicateContext ctx) {
		if (ctx.iri()!=null)
			return this.visitIri(ctx.iri());
		return this.visitRdfType(ctx.rdfType());
	}

	@Override
	public IRI visitRdfType(RdfTypeContext ctx) {
		return ProjectFactory.factory.createIRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
	}

	@Override
	public IRI visitIri(IriContext ctx) {
		if (ctx.prefixedName()!=null)
			return this.visitPrefixedName(ctx.prefixedName());
		return ProjectFactory.factory.createIRI(ctx.getText());
	}

	@Override
	public IRI visitPrefixedName(PrefixedNameContext ctx) throws RecognitionException {
		String prefix = ctx.getText().substring(0, ctx.getText().indexOf(":")+1);
		if (!prefixes.containsKey(prefix) && !prefixesParsed.containsKey(prefix))
			throw new RecognitionException("Prefix "+prefix+" not found in the prefixes.", null, null, ctx);
		if (prefixesParsed.containsKey(prefix))
			return ProjectFactory.factory.createIRI(prefixesParsed.get(prefix)+ctx.getText().substring(ctx.getText().indexOf(":")+1));
		return ProjectFactory.factory.createIRI(prefixes.get(prefix)+ctx.getText().substring(ctx.getText().indexOf(":")+1));
	}

	@Override
	public String visitPrefix(PrefixContext ctx) {
		if (!prefixes.containsKey(ctx.getText()) && !prefixesParsed.containsKey(ctx.getText()))
			throw new RecognitionException("Prefix "+ctx.getText()+" not found in the prefixes.", null, null, ctx);
		if (prefixesParsed.containsKey(ctx.getText()))
			return prefixesParsed.get(ctx.getText());
		return prefixes.get(ctx.getText());
	}

	public void setPrefixesMap(Map<String, String> prefixesMap) {
		this.prefixes = prefixesMap;		
	}
}
