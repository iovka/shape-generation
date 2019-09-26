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

import fr.inria.lille.shexjava.util.Interval;

public enum UniformCardinality {
	
	OPT(true, false), ONE(false, false), PLUS(false, true), STAR(true, true);
	
	private final boolean opt;
	private final boolean plus;
	
	private UniformCardinality (boolean opt, boolean plus) {
		this.opt = opt;
		this.plus = plus;
	}
	
	private static UniformCardinality get (boolean opt, boolean plus) {
		if (! opt && ! plus) return ONE;
		if (! opt && plus) return PLUS;
		if (opt && !plus) return OPT;
		if (opt && plus) return STAR;
		throw new IllegalStateException("Should not happen.");
 	}
	
	public static UniformCardinality mostSpecific (int nbOcc) {
		if (nbOcc == 0) return OPT;
		else if (nbOcc == 1) return ONE;
		else return PLUS;
	}
	
	public UniformCardinality meet (UniformCardinality other) {
		boolean o = this.opt || other.opt;
		boolean p = this.plus || other.plus ;
		
		return get(o,p);
	}
	
	public Interval getInterval () {
		switch (this) {
		case OPT: return Interval.OPT;
		case ONE: return Interval.ONE;
		case PLUS: return Interval.PLUS;
		case STAR: return Interval.STAR;
		default: throw new IllegalStateException("Should not happen.");
		}
	}

}
