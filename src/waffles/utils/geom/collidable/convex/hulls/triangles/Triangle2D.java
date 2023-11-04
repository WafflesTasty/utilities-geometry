package waffles.utils.geom.collidable.convex.hulls.triangles;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.collidable.convex.hulls.Hull2D;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Triangle2D} defines a two-dimensional triangle.
 *
 * @author Waffles
 * @since 13 Jan 2021
 * @version 1.0
 * 
 * 
 * @see Triangle
 * @see Hull2D
 */
public class Triangle2D extends Hull2D implements Triangle
{	
	/**
	 * Creates a new {@code Triangle2D}.
	 * 
	 * @param x1  a vector x-coordinate
	 * @param y1  a vector y-coordinate
	 * @param x2  a vector x-coordinate
	 * @param y2  a vector y-coordinate
 	 * @param x3  a vector x-coordinate
	 * @param y3  a vector y-coordinate
	 */
	public Triangle2D(float x1, float y1, float x2, float y2, float x3, float y3)
	{
		this
		(
			new Vector2(x1, y1),
			new Vector2(x2, y2),
			new Vector2(x3, y3)
		);
	}
	
	/**
	 * Creates a new {@code Triangle2D}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * @param p3  a point vector
	 * 
	 * 
	 * @see Vector2
	 */
	public Triangle2D(Vector2 p1, Vector2 p2, Vector2 p3)
	{
		super(p1, p2, p3);
	}
	
	/**
	 * Creates a new {@code Triangle2D}.
	 * 
	 * @param p1  a triangle point
	 * @param p2  a triangle point
	 * @param p3  a triangle point
	 * 
	 * 
	 * @see Point
	 */
	public Triangle2D(Point p1, Point p2, Point p3)
	{
		this
		(
			p1.Generator(),
			p2.Generator(),
			p3.Generator()
		);
	}
	
	/**
	 * Creates a new {@code Triangle2D}.
	 */
	public Triangle2D()
	{
		this(-1f, -1f, 0f, 1f, 1f, -1f);
	}

	/**
	 * Returns a first x-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return Generator(0).X();
	}
	
	/**
	 * Returns a first y-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return Generator(0).Y();
	}
	
	/**
	 * Returns a second x-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return Generator(1).X();
	}
	
	/**
	 * Returns a second y-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return Generator(1).Y();
	}
	
	/**
	 * Returns a third x-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X3()
	{
		return Generator(2).X();
	}
	
	/**
	 * Returns a third y-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y3()
	{
		return Generator(2).Y();
	}
}