package zeno.util.geom._deprecated.geometry.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom._deprecated.geometry.shapes.NCuboid;
import zeno.util.geom._deprecated.geometry.types.IShape3D;

/**
 * The {@code Cuboid} class defines a three-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IShape3D
 * @see NCuboid
 */
public class Cuboid extends NCuboid implements IShape3D
{
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param x  the cuboid's center x-coördinate
	 * @param y  the cuboid's center y-coördinate
	 * @param z  the cuboid's center z-coördinate
	 * @param w  the cuboid's width
	 * @param h  the cuboid's height
	 * @param d  the cuboid's depth
	 */
	public Cuboid(float x, float y, float z, float w, float h, float d)
	{
		this(new Vector3(x, y, z), new Vector3(w, h, d));
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param center  the cuboid's center
	 * @param size  the cuboid's size
	 * @see Vector3
	 */
	public Cuboid(Vector3 center, Vector3 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param w  the cuboid's width
	 * @param h  the cuboid's height
	 * @param d  the cuboid's depth
	 */
	public Cuboid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param size  the cuboid's size
	 * @see Vector3
	 */
	public Cuboid(Vector3 size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 */
	public Cuboid()
	{
		this(1, 1, 1);
	}
	
	
	@Override
	public Cuboid Bounds()
	{
		return this;
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