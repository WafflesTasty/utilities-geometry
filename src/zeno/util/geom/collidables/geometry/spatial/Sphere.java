package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.generic.ISphere;

/**
 * The {@code Sphere} class defines a three-dimensional sphere shape.
 * 
 * @author Zeno
 * @since Mar 21, 2017
 * @version 1.0
 * 
 * 
 * @see Ellipsoid
 * @see ISphere
 */
public class Sphere extends Ellipsoid implements ISphere
{
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param x  the sphere's center x-coördinate
	 * @param y  the sphere's center y-coördinate
	 * @param z  the sphere's center z-coördinate
	 * @param r  the sphere's radius
	 */
	public Sphere(float x, float y, float z, float r)
	{
		this(new Vector3(x, y, z), r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param c  the sphere's center
	 * @param r  the sphere's radius
	 * 
	 * 
	 * @see Vector3
	 */
	public Sphere(Vector3 c, float r)
	{
		super(c, new Vector3(2 * r, 2 * r, 2 * r));
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param r  the sphere's radius
	 */
	public Sphere(float r)
	{
		this(new Vector3(), r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 */
	public Sphere()
	{
		this(1);
	}

	
	@Override
	public Sphere Ball()
	{
		return this;
	}
}