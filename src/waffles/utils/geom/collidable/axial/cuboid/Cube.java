package waffles.utils.geom.collidable.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;

/**
 * A {@code Cube} defines a three-dimensional cube shape.
 * 
 * @author Waffles
 * @since Mar 21, 2017
 * @version 1.0
 * 
 * 
 * @see Cuboid
 * @see ICube
 */
public class Cube extends Cuboid implements ICube
{
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param x  a center x-coordinate
	 * @param y  a center y-coordinate
	 * @param z  a center z-coordinate
	 * @param l  a cube length
	 */
	public Cube(float x, float y, float z, float l)
	{
		this(new Vector3(x, y, z), l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param c  a center vector
	 * @param l  a cube length
	 * 
	 * 
	 * @see Vector3
	 */
	public Cube(Vector3 c, float l)
	{
		super(c, new Vector3(l, l, l));
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param l  a cube length
	 */
	public Cube(float l)
	{
		this(0f, 0f, 0f, l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 */
	public Cube()
	{
		super();
	}
}