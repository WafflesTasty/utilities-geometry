package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ICollideable3D;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;

/**
 * The {@code Line3D} class defines a three-dimensional line space.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ICollideable3D
 * @see LineND
 */
public class Line3D extends LineND implements ICollideable3D
{
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
	 */
	public Line3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		this(new Vector3(x1, y1, z1), new Vector3(x2, y2, z2));
	}
	
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
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
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Point
	 */
	public Line3D(Point p1, Point p2)
	{
		super((Point) ASpaces.occupy(p1, 3), (Point) ASpaces.occupy(p2, 3));
	}
	
	/**
	 * Creates a new {@code Line3D}.
	 */
	public Line3D()
	{
		this(-.5f, -.5f, -.5f, .5f, .5f, .5f);
	}
	
		
	/**
	 * Returns the first x-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first x-coördinate
	 */
	public float X1()
	{
		return P1().X();
	}
	
	/**
	 * Returns the first y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first y-coördinate
	 */
	public float Y1()
	{
		return P1().Y();
	}
	
	/**
	 * Returns the first z-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first z-coördinate
	 */
	public float Z1()
	{
		return P1().Z();
	}
	
	/**
	 * Returns the second x-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second x-coördinate
	 */
	public float X2()
	{
		return P2().X();
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second y-coördinate
	 */
	public float Y2()
	{
		return P2().Y();
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second z-coördinate
	 */
	public float Z2()
	{
		return P2().Z();
	}

	
	@Override
	public Vector3 P1()
	{
		return (Vector3) super.P1();
	}
	
	@Override
	public Vector3 P2()
	{
		return (Vector3) super.P2();
	}
}