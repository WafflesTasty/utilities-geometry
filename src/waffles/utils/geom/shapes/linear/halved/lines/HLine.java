package waffles.utils.geom.shapes.linear.halved.lines;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.collision.linear.halved.CLSHLine;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.LSpace;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Transformator;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code HLine} defines a real-valued halfline of R^n.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see LSpace
 */
public class HLine implements LSpace
{	
	/**
	 * Creates a halfline from a {@code Point} and a {@code Matrix}.
	 * This method is designed to return a {@code Point} instead
	 * when the direction matrix has no columns.
	 * 
	 * @param p  an affine point
	 * @param v  a direction matrix
	 * @return  a halfline
	 * 
	 * 
	 * @see Collidable
	 * @see Matrix
	 * @see Point
	 */
	public static Collidable create(Point p, Matrix v)
	{
		switch(v.Columns())
		{
		case 0:
			return p;
		default:
			return new HLine(p, (Vector) v);
		}
	}

	/**
	 * A {@code HLine.Factory} generates {@code HLine} geometry.
	 *
	 * @author Waffles
	 * @since 07 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see Line
	 */
	public static class Factory extends Line.Factory
	{		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param s  a matrix span
		 * 
		 * 
		 * @see Matrix
		 */
		public Factory(Matrix s)
		{
			super(s);
		}
		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param o  an origin point
		 * @param d  a direction vector
		 * 
		 * 
		 * @see Vector
		 * @see Point
		 */
		public Factory(Point o, Vector d)
		{
			super(o, d);
		}

		
		@Override
		public Transformator create(Matrix m)
		{
			if(m.Columns() == 0)
			{
				int n = m.Rows();
				return new Void(n);
			}
			
			Factory fct = new Factory(m);
			return new HLine(fct);
		}
		
		@Override
		public double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Factory fct;
	
	/**
	 * Creates a new {@code HLine}.
	 * 
	 * @param p  an origin point
	 * @param v  a direction vector
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public HLine(Point p, Vector v)
	{
		this(new Factory(p, v));
	}
	
	/**
	 * Creates a new {@code HLine}.
	 * 
	 * @param p  a source point
	 * @param q  a target point
	 * 
	 * 
	 * @see Point
	 */
	public HLine(Point p, Point q)
	{
		this(p, q.minus(p).Vector());
	}
		
	/**
	 * Creates a new {@code HLine}.
	 * 
	 * @param f  a line factory
	 * 
	 * 
	 * @see Factory
	 */
	public HLine(Factory f)
	{
		fct = f;
	}


	@Override
	public Collision Collision()
	{
		return new CLSHLine(this);
	}
	
	@Override
	public Factory Factory()
	{
		return fct;
	}
}