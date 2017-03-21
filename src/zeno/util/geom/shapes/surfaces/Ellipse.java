package zeno.util.geom.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.shapes.NEllipsoid;

/**
 * The {@code Ellipse} class defines a two-dimensional ellipsoid shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NEllipsoid
 */
public class Ellipse extends NEllipsoid
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
	
	
	/**
	 * Changes the size of the {@code Ellipse}.
	 * 
	 * @param w  a new ellipsoid width
	 * @param h  a new ellipsoid height
	 */
	public void setSize(float w, float h)
	{
		setSize(new Vector2(w, h));
	}
		
	/**
	 * Changes the size of the {@code Ellipse}.
	 * 
	 * @param size  a new ellipsoid size
	 * @see Vector2
	 */
	public void setSize(Vector2 size)
	{
		super.setSize(size);
	}
	
	
	/**
	 * Changes the height of the {@code Ellipse}.
	 * 
	 * @param h  a new ellipsoid height
	 */
	public void setHeight(float h)
	{
		setSize(Width(), h);
	}
	
	/**
	 * Changes the width of the {@code Ellipse}.
	 * 
	 * @param w  a new ellipsoid width
	 */
	public void setWidth(float w)
	{
		setSize(w, Height());
	}
	
	
	/**
	 * Returns the size of the {@code Ellipse}.
	 * 
	 * @return  the ellipsoid's size
	 * @see Vector2
	 */
	@Override
	public Vector2 Size()
	{
		return (Vector2) super.Size();
	}
	
	/**
	 * Returns the height of the {@code Ellipse}.
	 * 
	 * @return  the ellipsoid's height
	 */
	public float Height()
	{
		return Size().get(1);
	}
	
	/**
	 * Returns the width of the {@code Ellipse}.
	 * 
	 * @return  the ellipsoid's width
	 */
	public float Width()
	{
		return Size().get(0);
	}
}