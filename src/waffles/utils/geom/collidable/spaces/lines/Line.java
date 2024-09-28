package waffles.utils.geom.collidable.spaces.lines;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collision.spaces.lines.CLSLine;

/**
 * A {@code Line} defines an affine line in n-dimensional space.
 * 
 * @author Waffles
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
	 * @param p1  a line point
	 * @param p2  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Line(Point p1, Point p2)
	{
		super(p1, p2.minus(p1));
	}
	
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * 
	 * 
	 * @see Vector
	 */
	public Line(Vector p1, Vector p2)
	{
		this(new Point(p1, 1f), p2.minus(p1));
	}
	
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p  an origin point
	 * @param v  a vector direction
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public Line(Point p, Vector v)
	{
		super(p, v);
	}


	/**
	 * Returns the first point of the {@code Line}.
	 * 
	 * @return  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Point P1()
	{
		return Generator(0);
	}
	
	/**
	 * Returns the second point of the {@code Line}.
	 * 
	 * @return  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Point P2()
	{
		return Generator(1);
	}


	@Override
	public Collision Collisions()
	{
		return new CLSLine(this);
	}
}