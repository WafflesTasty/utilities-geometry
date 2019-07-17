package zeno.util.geom.collidables.geometry;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collidables.IGeometry2D;

/**
 * The {@code Geometry2D} class is the base class for bounded two-dimensional shapes.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see IGeometry2D
 * @see GeometryND
 */
public abstract class Geometry2D extends GeometryND implements IGeometry2D
{	
	/**
	 * Creates a new {@code Geometry2D}.
	 * 
	 * @param center  a geometry center
	 * @param size    a geometry size
	 * 
	 * 
	 * @see Vector2
	 */
	public Geometry2D(Vector2 center, Vector2 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Geometry2D}.
	 * 
	 * @param size  a geometry size
	 * 
	 * 
	 * @see Vector2
	 */
	public Geometry2D(Vector2 size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code Geometry2D}.
	 */
	public Geometry2D()
	{
		super(2);
	}
	
			
	// Obligatory Bounds overrides.
	
	@Override
	public Vector2 Minimum()
	{
		return IGeometry2D.super.Minimum();
	}
	
	@Override
	public Vector2 Maximum()
	{
		return IGeometry2D.super.Maximum();
	}
	
	@Override
	public Vector2 Center()
	{
		return (Vector2) super.Center();
	}
	
	@Override
	public Vector2 Size()
	{
		return (Vector2) super.Size();
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
}