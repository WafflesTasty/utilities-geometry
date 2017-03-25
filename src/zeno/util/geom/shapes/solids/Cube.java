package zeno.util.geom.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.shapes.ICube;

/**
 * The {@code Cube} class defines a three-dimensional cube shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see Cuboid
 * @see ICube
 */
public class Cube extends Cuboid implements ICube
{
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param x  the cube's center x-coördinate
	 * @param y  the cube's center y-coördinate
	 * @param z  the cube's center z-coördinate
	 * @param l  the cube's length
	 */
	public Cube(float x, float y, float z, float l)
	{
		this(new Vector3(x, y, z), l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param center  the cube's center
	 * @param l  the cube's length
	 * @see Vector3
	 */
	public Cube(Vector3 center, float l)
	{
		super(center, new Vector3(l, l, l));
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param l  the cube's length
	 */
	public Cube(float l)
	{
		this(new Vector3(), l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 */
	public Cube()
	{
		this(1);
	}
}