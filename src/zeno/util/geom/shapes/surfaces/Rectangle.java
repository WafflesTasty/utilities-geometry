package zeno.util.geom.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.shapes.NCuboid;

/**
 * The {@code Rectangle} class defines a two-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NCuboid
 */
public class Rectangle extends NCuboid
{
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param x  the rectangle's center x-coördinate
	 * @param y  the rectangle's center y-coördinate
	 * @param w  the rectangle's width
	 * @param h  the rectangle's height
	 */
	public Rectangle(float x, float y, float w, float h)
	{
		this(new Vector2(x, y), new Vector2(w, h));
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param center  the rectangle's center
	 * @param size  the rectangle's size
	 * @see Vector2
	 */
	public Rectangle(Vector2 center, Vector2 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param w  the rectangle's width
	 * @param h  the rectangle's height
	 */
	public Rectangle(float w, float h)
	{
		this(0, 0, w, h);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param size  a size
	 * @see Vector2
	 */
	public Rectangle(Vector2 size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 */
	public Rectangle()
	{
		this(1, 1);
	}
	
	
	/**
	 * Changes the size of the {@code Rectangle}.
	 * 
	 * @param w  a new rectangle width
	 * @param h  a new rectangle height
	 */
	public void setSize(float w, float h)
	{
		setSize(new Vector2(w, h));
	}
		
	/**
	 * Changes the size of the {@code Rectangle}.
	 * 
	 * @param size  a new rectangle size
	 * @see Vector2
	 */
	public void setSize(Vector2 size)
	{
		super.setSize(size);
	}
	
	
	/**
	 * Changes the height of the {@code Rectangle}.
	 * 
	 * @param h  a new rectangle height
	 */
	public void setHeight(float h)
	{
		setSize(Width(), h);
	}
	
	/**
	 * Changes the width of the {@code Rectangle}.
	 * 
	 * @param w  a new rectangle width
	 */
	public void setWidth(float w)
	{
		setSize(w, Height());
	}
	
	
	/**
	 * Returns the size of the {@code Rectangle}.
	 * 
	 * @return  the rectangle's size
	 * @see Vector2
	 */
	@Override
	public Vector2 Size()
	{
		return (Vector2) super.Size();
	}
	
	/**
	 * Returns the height of the {@code Rectangle}.
	 * 
	 * @return  the rectangle's height
	 */
	public float Height()
	{
		return Size().get(1);
	}
	
	/**
	 * Returns the width of the {@code Rectangle}.
	 * 
	 * @return  the rectangle's width
	 */
	public float Width()
	{
		return Size().get(0);
	}
}