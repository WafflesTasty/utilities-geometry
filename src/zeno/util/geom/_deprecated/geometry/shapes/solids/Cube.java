package zeno.util.geom._deprecated.geometry.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom._deprecated.geometry.shapes.NCube;
import zeno.util.geom._deprecated.geometry.types.IShape3D;

/**
 * The {@code Cube} class defines a three-dimensional cube shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see IShape3D
 * @see NCube
 */
public class Cube extends NCube implements IShape3D
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
		super(center, l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param l  the cube's length
	 */
	public Cube(float l)
	{
		super(3, l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 */
	public Cube()
	{
		this(1);
	}

	
	@Override
	public Cuboid Bounds()
	{
		return IShape3D.super.Bounds();
	}
	
	@Override
	public Vector3 Center()
	{
		return (Vector3) super.Center();
	}
	
	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
	}
}