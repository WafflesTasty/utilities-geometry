package waffles.utils.geom.shapes.linear.affine.lines;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.linear.affine.CLSLine;
import waffles.utils.geom.shapes.linear.LSpace;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Line} defines an affine line in n-dimensional space.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ASpace
 * @see LSpace
 */
public class Line extends ASpace implements LSpace
{
	/**
	 * A {@code Line.Factory} generates {@code Line} geometry.
	 *
	 * @author Waffles
	 * @since 08 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see ASpace
	 */
	public static class Factory extends ASpace.Factory
	{
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param p  a line point
		 * @param q  a line point
		 * 
		 * 
		 * @see Point
		 */
		public Factory(Point p, Point q)
		{
			super(p, q);
		}
		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param p  a line point
		 * @param v  a direction vector
		 * 
		 * 
		 * @see Vector
		 * @see Point
		 */
		public Factory(Point p, Vector v)
		{
			super(p, v);
		}
		
		/**
		 * Creates new {@code Factory}.
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
	}
	
	
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p  a line point
	 * @param v  a direction vector
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public Line(Point p, Vector v)
	{
		this(new Factory(p, v));
	}
		
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p  a line point
	 * @param q  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Line(Point p, Point q)
	{
		this(new Factory(p, q));
	}
	
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param f  a line factory
	 * 
	 * 
	 * @see Factory
	 */
	public Line(Factory f)
	{
		super(f);
	}
	

	/**
	 * Returns a point on the {@code Line}.
	 * 
	 * @return  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Point P1()
	{
		return Hints().Point(0);
	}
	
	/**
	 * Returns a point on the {@code Line}.
	 * 
	 * @return  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Point P2()
	{
		return Hints().Point(1);
	}


	@Override
	public Collision Collision()
	{
		return new CLSLine(this);
	}
}