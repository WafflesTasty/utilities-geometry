package waffles.utils.geom.collidable.spaces.lines;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.Collideable3D;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Line3D} defines an affine line in three-dimensional space.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see Collideable3D
 * @see Line
 */
public class Line3D extends Line implements Collideable3D
{
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param x1  a vector x-coordinate
	 * @param y1  a vector y-coordinate
	 * @param z1  a vector z-coordinate
	 * @param x2  a vector x-coordinate
	 * @param y2  a vector y-coordinate
	 * @param z2  a vector z-coordinate
	 */
	public Line3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		this
		(
			new Vector3(x1, y1, z1),
			new Vector3(x2, y2, z2)
		);
	}
	
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Line3D(Vector3 p1, Vector3 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param p  an origin point
	 * @param v  a vector direction
	 * 
	 * 
	 * @see Vector3
	 * @see Point
	 */
	public Line3D(Point p, Vector3 v)
	{
		super(p, v);
	}
	
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param p1  a line point
	 * @param p2  a line point
	 * 
	 * 
	 * @see Point
	 */
	public Line3D(Point p1, Point p2)
	{
		super(p1, p2);
	}
}