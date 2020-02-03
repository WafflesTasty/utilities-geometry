package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.AffineMap;
import zeno.util.geom.collidables.bounds.Bounds3D;
import zeno.util.geom.collidables.geometry.Geometry3D;
import zeno.util.geom.collidables.geometry.bounds.BNDCuboid3D;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

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
	 * @param x  a cuboid center x-co�rdinate
	 * @param y  a cuboid center y-co�rdinate
	 * @param z  a cuboid center z-co�rdinate
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
	 * @param c  a cuboid center
	 * @param s    a cuboid size
	 * 
	 * 
	 * @see Vector3
	 */
	public Cuboid(Vector3 c, Vector3 s)
	{
		super(c, s);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param s  a cuboid size
	 * 
	 * 
	 * @see Vector3
	 */
	public Cuboid(Vector3 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 */
	public Cuboid()
	{
		super();
	}

	
	@Override
	public Bounds3D Bounds(AffineMap map)
	{
		return new BNDCuboid3D(this, map);
	}
	
	// Optional Bounds overrides.
	
	@Override
	public Cuboid Box()
	{
		return this;
	}
}