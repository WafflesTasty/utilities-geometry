package zeno.util.geom.geometry.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.geometry.shapes.NSphere;
import zeno.util.geom.geometry.types.IShape3D;

/**
 * The {@code Sphere} class defines a three-dimensional sphere shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see IShape3D
 * @see NSphere
 */
public class Sphere extends NSphere implements IShape3D
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
	 * @param center  the sphere's center
	 * @param r  the sphere's radius
	 * @see Vector3
	 */
	public Sphere(Vector3 center, float r)
	{
		super(center, 2 * r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param r  the sphere's radius
	 */
	public Sphere(float r)
	{
		super(3, r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 */
	public Sphere()
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