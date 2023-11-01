package waffles.utils.geom.collidable.convex.hulls.segments;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.collidable.convex.hulls.Hull3D;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Segment3D} defines a three-dimensional line segment.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ISegment
 * @see Hull3D
 */
public class Segment3D extends Hull3D implements ISegment
{
	/**
	 * Creates a new {@code Segment3D}.
	 * 
	 * @param x1  a vector x-coordinate
	 * @param y1  a vector y-coordinate
	 * @param z1  a vector z-coordinate
	 * @param x2  a vector x-coordinate
	 * @param y2  a vector y-coordinate
	 * @param z2  a vector z-coordinate
	 */
	public Segment3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		this
		(
			new Vector3(x1, y1, z1),
			new Vector3(x2, y2, z2)
		);
	}
	
	/**
	 * Creates a new {@code Segment3D}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Segment3D(Vector3 p1, Vector3 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Segment3D}.
	 * 
	 * @param p1  a segment point
	 * @param p2  a segment point
	 * 
	 * 
	 * @see Point
	 */
	public Segment3D(Point p1, Point p2)
	{
		this(p1.Generator(), p2.Generator());
	}
	
		
	/**
	 * Returns a first x-coordinate of the {@code Segment3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return Generator(0).X();
	}
	
	/**
	 * Returns a first y-coordinate of the {@code Segment3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return Generator(0).Y();
	}
	
	/**
	 * Returns a first z-coordinate of the {@code Segment3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public float Z1()
	{
		return Generator(0).Z();
	}
	
	/**
	 * Returns a second x-coordinate of the {@code Segment3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return Generator(1).X();
	}
	
	/**
	 * Returns a second y-coordinate of the {@code Segment3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return Generator(1).Y();
	}
	
	/**
	 * Returns a second z-coordinate of the {@code Segment3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public float Z2()
	{
		return Generator(1).Z();
	}
}