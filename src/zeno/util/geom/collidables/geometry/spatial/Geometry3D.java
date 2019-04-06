package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.higher.GeometryND;

/**
 * The {@code Geometry3D} class is the base class for closed three-dimensional shapes.
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
		this(new Vector3(), size);
	}
	
	/**
	 * Creates a new {@code Geometry3D}.
	 */
	public Geometry3D()
	{
		this(new Vector3(1, 1, 1));
	}

	
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