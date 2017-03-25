package zeno.util.geom.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.IGeometry2D;
import zeno.util.geom.shapes.other.NGeometry;

/**
 * The {@code Geometry2D} class is the base class for closed two-dimensional shapes.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IGeometry2D
 * @see NGeometry
 */
public abstract class Geometry2D extends NGeometry implements IGeometry2D
{
	/**
	 * Creates a new {@code Geometry2D}.
	 * 
	 * @param center  the geometry's center
	 * @param size  the geometry's size
	 * @see Vector2
	 */
	public Geometry2D(Vector2 center, Vector2 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Geometry2D}.
	 * 
	 * @param size  the geometry's size
	 * @see Vector2
	 */
	public Geometry2D(Vector2 size)
	{
		this(new Vector2(), size);
	}
	
	/**
	 * Creates a new {@code Geometry2D}.
	 */
	public Geometry2D()
	{
		this(new Vector2(1, 1));
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
}