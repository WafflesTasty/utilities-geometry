package zeno.util.geom._refactor.shapes.solids.quadric;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom._refactor.shapes.solids.Quadric;

/**
 * The {@code Sphere} class defines a three-dimensional sphere.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Quadric
 */
public class Sphere extends Quadric
{
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param vcount  a vertex count
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param rad  a sphere radius
	 */
	public Sphere(int vcount, float x, float y, float z, float rad)
	{
		super(vcount, x, y, z, rad, rad, rad);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param vcount  a vertex count
	 * @param center  a sphere center
	 * @param rad  a sphere radius
	 * @see Vector3
	 */
	public Sphere(int vcount, Vector3 center, float rad)
	{
		this(vcount, center.X(), center.Y(), center.Z(), rad);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param rad  a sphere radius
	 */
	public Sphere(float x, float y, float z, float rad)
	{
		super(x, y, z, rad, rad, rad);
	}
		
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param center  a sphere center
	 * @param rad  a sphere radius
	 * @see Vector3
	 */
	public Sphere(Vector3 center, float rad)
	{
		this(center.X(), center.Y(), center.Z(), rad);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param vcount  a vertex count
	 * @param rad  a sphere radius
	 */
	public Sphere(int vcount, float rad)
	{
		this(vcount, 0, 0, 0, rad);
	}

	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param rad  a sphere radius
	 */
	public Sphere(float rad)
	{
		this(0, 0, 0, rad);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 */
	public Sphere()
	{
		this(1);
	}

	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Sphere)
		{
			return super.equals(o);
		}
		
		return false;
	}

	/**
	 * Indicates if the {@code Sphere} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 * @return  {@code true} if the point is contained
	 */
	@Override
	public boolean contains(float x, float y, float z)
	{
		float r = Radius();
		
		// Normalized point.
		float nx = x - X();
		float ny = y - Y();
		float nz = z - Z();
		
		// Distance from center.
		return nx * nx + ny * ny + nz * nz < r * r;
	}
	
	/**
	 * Changes the radius of the {@code Sphere}.
	 * 
	 * @param rad  a new sphere radius
	 */
	public void setRadius(float rad)
	{
		setSize(Math.abs(rad), Math.abs(rad), Math.abs(rad));
	}
	
	/**
	 * Returns the radius of the {@code Sphere}.
	 * 
	 * @return  the sphere's radius
	 */
	public float Radius()
	{
		return Width();
	}
}