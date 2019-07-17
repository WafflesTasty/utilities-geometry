package zeno.util.geom.collidables.geometry;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.IGeometry3D;

/**
 * The {@code Geometry3D} class is the base class for bounded three-dimensional shapes.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see IGeometry3D
 * @see GeometryND
 */
public abstract class Geometry3D extends GeometryND implements IGeometry3D
{	
	/**
	 * Creates a new {@code Geometry3D}.
	 * 
	 * @param center  a geometry center
	 * @param size    a geometry size
	 * 
	 * 
	 * @see Vector3
	 */
	public Geometry3D(Vector3 center, Vector3 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Geometry3D}.
	 * 
	 * @param size  a geometry size
	 * 
	 * 
	 * @see Vector3
	 */
	public Geometry3D(Vector3 size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code Geometry3D}.
	 */
	public Geometry3D()
	{
		super(3);
	}

	
	// Obligatory Bounds overrides.
	
	@Override
	public Vector3 Minimum()
	{
		return IGeometry3D.super.Minimum();
	}
	
	@Override
	public Vector3 Maximum()
	{
		return IGeometry3D.super.Maximum();
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

	
	// Optional Bounds overrides.
	
	@Override
	public float XMin()
	{
		return X() - 0.5f * Width();
	}
	
	@Override
	public float XMax()
	{
		return X() + 0.5f * Width();
	}
	
	@Override
	public float YMin()
	{
		return Y() - 0.5f * Height();
	}
	
	@Override
	public float YMax()
	{
		return Y() + 0.5f * Height();
	}
	
	@Override
	public float ZMin()
	{
		return Z() - 0.5f * Depth();
	}
	
	@Override
	public float ZMax()
	{
		return Z() + 0.5f * Depth();
	}
}