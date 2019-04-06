package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.ICuboid;

/**
 * The {@code Cuboid} class defines a three-dimensional rectangular shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see Geometry3D
 * @see ICuboid
 */
public class Cuboid extends Geometry3D implements ICuboid
{
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param x  a cuboid center x-coördinate
	 * @param y  a cuboid center y-coördinate
	 * @param z  a cuboid center z-coördinate
	 * @param w  a cuboid width
	 * @param h  a cuboid height
	 * @param d  a cuboid depth
	 */
	public Cuboid(float x, float y, float z, float w, float h, float d)
	{
		this(new Vector3(x, y, z), new Vector3(w, h, d));
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param center  a cuboid center
	 * @param size    a cuboid size
	 * 
	 * 
	 * @see Vector3
	 */
	public Cuboid(Vector3 center, Vector3 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param w  a cuboid width
	 * @param h  a cuboid height
	 * @param d  a cuboid depth
	 */
	public Cuboid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param size  a cuboid size
	 * 
	 * 
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
	public Cuboid Box()
	{
		return this;
	}
}