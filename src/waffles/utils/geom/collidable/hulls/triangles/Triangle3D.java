package waffles.utils.geom.collidable.hulls.triangles;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.hulls.Hull3D;

/**
 * A {@code Triangle3D} defines a three-dimensional triangle.
 *
 * @author Waffles
 * @since 13 Jan 2021
 * @version 1.0
 * 
 * 
 * @see ITriangle
 * @see Hull3D
 */
public class Triangle3D extends Hull3D implements ITriangle
{
	/**
	 * Creates a new {@code Triangle3D}.
	 * 
	 * @param x1  a vector x-coordinate
	 * @param y1  a vector y-coordinate
	 * @param z1  a vector z-coordinate
	 * @param x2  a vector x-coordinate
	 * @param y2  a vector y-coordinate
	 * @param z2  a vector z-coordinate
 	 * @param x3  a vector x-coordinate
	 * @param y3  a vector y-coordinate
	 * @param z3  a vector z-coordinate
	 */
	public Triangle3D(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3)
	{
		this
		(
			new Vector3(x1, y1, z1),
			new Vector3(x2, y2, z2),
			new Vector3(x3, y3, z3)
		);
	}
	
	/**
	 * Creates a new {@code Triangle3D}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * @param p3  a point vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Triangle3D(Vector3 p1, Vector3 p2, Vector3 p3)
	{
		super(p1, p2, p3);
	}
	
	/**
	 * Creates a new {@code Triangle3D}.
	 * 
	 * @param p1  a triangle point
	 * @param p2  a triangle point
	 * @param p3  a triangle point
	 * 
	 * 
	 * @see Point
	 */
	public Triangle3D(Point p1, Point p2, Point p3)
	{
		this
		(
			p1.Generator(),
			p2.Generator(),
			p3.Generator()
		);
	}
	
	/**
	 * Creates a new {@code Triangle3D}.
	 */
	public Triangle3D()
	{
		this(-1f, -1f, -1f, 0f, 1f, 1f, 1f, -1f, -1f);
	}
	
		
	/**
	 * Returns a first x-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return Generator(0).X();
	}
	
	/**
	 * Returns a first y-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return Generator(0).Y();
	}
	
	/**
	 * Returns a first z-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public float Z1()
	{
		return Generator(0).Z();
	}
	
	/**
	 * Returns a second x-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return Generator(1).X();
	}
	
	/**
	 * Returns a second y-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return Generator(1).Y();
	}
	
	/**
	 * Returns a second z-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public float Z2()
	{
		return Generator(1).Z();
	}
	
	/**
	 * Returns a third x-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public float X3()
	{
		return Generator(2).X();
	}
	
	/**
	 * Returns a third y-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y3()
	{
		return Generator(2).Y();
	}
	
	/**
	 * Returns a third z-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public float Z3()
	{
		return Generator(2).Z();
	}
}