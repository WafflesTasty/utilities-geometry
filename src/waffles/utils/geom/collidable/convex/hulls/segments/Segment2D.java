package waffles.utils.geom.collidable.convex.hulls.segments;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.collidable.convex.hulls.Hull2D;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Segment2D} defines a two-dimensional line segment.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see Segment
 * @see Hull2D
 */
public class Segment2D extends Hull2D implements Segment
{
	/**
	 * Creates a new {@code Segment2D}.
	 * 
	 * @param x1  a vector x-coordinate
	 * @param y1  a vector y-coordinate
	 * @param x2  a vector x-coordinate
	 * @param y2  a vector y-coordinate
	 */
	public Segment2D(float x1, float y1, float x2, float y2)
	{
		this
		(
			new Vector2(x1, y1),
			new Vector2(x2, y2)
		);
	}
	
	/**
	 * Creates a new {@code Segment2D}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * 
	 * 
	 * @see Vector2
	 */
	public Segment2D(Vector2 p1, Vector2 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Segment2D}.
	 * 
	 * @param p1  a segment point
	 * @param p2  a segment point
	 * 
	 * 
	 * @see Point
	 */
	public Segment2D(Point p1, Point p2)
	{
		this
		(
			p1.Generator(),
			p2.Generator()
		);
	}
	
	
	/**
	 * Returns a first x-coordinate of the {@code Segment2D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return Generator(0).X();
	}
	
	/**
	 * Returns a first y-coordinate of the {@code Segment2D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return Generator(0).Y();
	}
	
	/**
	 * Returns a second x-coordinate of the {@code Segment2D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return Generator(1).X();
	}
	
	/**
	 * Returns a second y-coordinate of the {@code Segment2D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return Generator(1).Y();
	}
}