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
package fr.inria.lille.shexjava.pattern.parsing;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class PatternErrorStrategy extends DefaultErrorStrategy{

	@Override
	public void recover(Parser recognizer, RecognitionException e) {
		for (ParserRuleContext context = recognizer.getContext(); context != null; context = context.getParent()) {
			context.exception = e;
		}
		
		int line = recognizer.getCurrentToken().getLine();
		int charPositionInLine = recognizer.getCurrentToken().getCharPositionInLine();
		String msg = recognizer.getCurrentToken().getText();
		String message = ("line " + line + ":" + charPositionInLine + " " + msg);

		throw new ParseCancellationException(message,e);
	}

	/** Make sure we don't attempt to recover inline; if the parser
	 *  successfully recovers, it won't throw an exception.
	 */
	@Override
	public Token recoverInline(Parser recognizer)
			throws RecognitionException
	{
		InputMismatchException e = new InputMismatchException(recognizer);
		for (ParserRuleContext context = recognizer.getContext(); context != null; context = context.getParent()) {
			context.exception = e;
		}
		
		int line = recognizer.getCurrentToken().getLine();
		int charPositionInLine = recognizer.getCurrentToken().getCharPositionInLine();
		String msg = recognizer.getCurrentToken().getText();
		String message = ("line " + line + ":" + charPositionInLine + " " + msg);

		throw new ParseCancellationException(message,e);
	}

	/** Make sure we don't attempt to recover from problems in subrules. */
	@Override
	public void sync(Parser recognizer) { }

}
