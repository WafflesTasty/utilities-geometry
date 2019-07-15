package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.Point;

/**
 * The {@code Line} class defines an n-dimensional line space.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public class Line extends ASpace
{			
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Vector
	 */
	public Line(Vector p1, Vector p2)
	{
		this(new Point(p1), p2.minus(p1));
	}
	
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Point
	 * @see Vector
	 */
	public Line(Point p1, Vector p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Point
	 */
	public Line(Point p1, Point p2)
	{
		this(p1, p2.minus(p1));
	}
}