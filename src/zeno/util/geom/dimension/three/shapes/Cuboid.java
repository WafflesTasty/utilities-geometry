package zeno.util.geom.dimension.three.shapes;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.dimension.three.Geometry3D;
import zeno.util.geom.shapes.ICuboid;

/**
 * The {@code Cuboid} class defines a three-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see Geometry3D
 * @see ICuboid
 */
public class Cuboid extends Geometry3D implements ICuboid
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
}