package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.generic.ICube;

/**
 * The {@code Cube} class defines a three-dimensional cube shape.
 * 
 * @author Zeno
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
	 * @param x  a cube center x-co�rdinate
	 * @param y  a cube center y-co�rdinate
	 * @param z  a cube center z-co�rdinate
	 * @param l  a cube length
	 */
	public Cube(float x, float y, float z, float l)
	{
		this(new Vector3(x, y, z), l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param c  a cube center
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