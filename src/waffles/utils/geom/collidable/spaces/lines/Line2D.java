package waffles.utils.geom.collidable.spaces.lines;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.Collideable2D;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Line2D} defines an affine line in two-dimensional space.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see Collideable2D
 * @see Line
 */
public class Line2D extends Line implements Collideable2D
{		
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param x1  a vector x-coordinate
	 * @param y1  a vector y-coordinate
	 * @param x2  a vector x-coordinate
	 * @param y2  a vector y-coordinate
	 */
	public Line2D(float x1, float y1, float x2, float y2)
	{
		this
		(
			new Vector2(x1, y1),
			new Vector2(x2, y2)
		);
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * 
	 * 
	 * @see Vector2
	 */
	public Line2D(Vector2 p1, Vector2 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p  an origin point
	 * @param v  a vector direction
	 * 
	 * 
	 * @see Vector2
	 * @see Point
	 */
	public Line2D(Point p, Vector2 v)
	{
		super(p, v);
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p1  a line point
	 * @param p2  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Line2D(Point p1, Point p2)
	{
		super(p1, p2);
	}
}