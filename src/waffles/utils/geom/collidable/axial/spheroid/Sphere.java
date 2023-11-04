package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.bounds.axial.sphere.BNDSphere3D;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Sphere} defines a three-dimensional sphere shape.
 * 
 * @author Waffles
 * @since Mar 21, 2017
 * @version 1.0
 * 
 * 
 * @see Spheroid
 * @see HyperSphere
 */
public class Sphere extends Spheroid implements HyperSphere
{
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param x  a center x-coordinate
	 * @param y  a center y-coordinate
	 * @param z  a center z-coordinate
	 * @param r  a sphere radius
	 */
	public Sphere(float x, float y, float z, float r)
	{
		this(new Vector3(x, y, z), r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param c  a center vector
	 * @param r  a sphere radius
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
	 * @param c  a point center
	 * @param r  a sphere radius
	 * 
	 * 
	 * @see Point
	 */
	public Sphere(Point c, float r)
	{
		this(c.Generator(), r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param r  a sphere radius
	 */
	public Sphere(float r)
	{
		this(0f, 0f, 0f, r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 */
	public Sphere()
	{
		this(1f);
	}
	
	
	@Override
	public Sphere create(Vector o, Vector s)
	{
		return new Sphere((Vector3) o, s.get(0) / 2);
	}
	
	@Override
	public Bounds3D Bounds()
	{
		return new BNDSphere3D(this);
	}
}