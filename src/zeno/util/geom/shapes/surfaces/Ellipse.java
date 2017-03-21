package zeno.util.geom.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.interfaces.IShape2D;
import zeno.util.geom.shapes.NEllipsoid;

/**
 * The {@code Ellipse} class defines a two-dimensional ellipsoid shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NEllipsoid
 * @see IShape2D
 */
public class Ellipse extends NEllipsoid implements IShape2D
{
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param x  the ellipsoid's center x-coördinate
	 * @param y  the ellipsoid's center y-coördinate
	 * @param w  the ellipsoid's width
	 * @param h  the ellipsoid's height
	 */
	public Ellipse(float x, float y, float w, float h)
	{
		this(new Vector2(x, y), new Vector2(w, h));
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param center  the ellipsoid's center
	 * @param size  the ellipsoid's size
	 * @see Vector2
	 */
	public Ellipse(Vector2 center, Vector2 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param w  the ellipsoid's width
	 * @param h  the ellipsoid's height
	 */
	public Ellipse(float w, float h)
	{
		this(0, 0, w, h);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param size  the ellipsoid's size
	 * @see Vector2
	 */
	public Ellipse(Vector2 size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 */
	public Ellipse()
	{
		this(1, 1);
	}
	
	
	@Override
	public Rectangle Bounds()
	{
		return IShape2D.super.Bounds();
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