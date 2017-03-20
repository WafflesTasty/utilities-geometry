package zeno.util.geom._refactor.shapes.surfaces.lateral;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom._refactor.shapes.surfaces.Lateral2D;

/**
 * The {@code Rectangle} class defines a two-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see Lateral2D
 */
public class Rectangle extends Lateral2D
{
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param w  a width
	 * @param h  a height
	 */
	public Rectangle(float x, float y, float w, float h)
	{
		super(x, y, w, h);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param center  a center
	 * @param size  a size
	 * @see Vector2
	 */
	public Rectangle(Vector2 center, Vector2 size)
	{
		this(center.X(), center.Y(), size.X(), size.Y());
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param w  a width
	 * @param h  a height
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
		this(size.X(), size.Y());
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 */
	public Rectangle()
	{
		this(1, 1);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Rectangle)
		{
			return super.equals(o);
		}
		
		return false;
	}
			
	/**
	 * Changes the size of the {@code Rectangle}.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 */
	@Override
	public void setSize(float w, float h)
	{
		super.setSize(w, h);
	}
	
	/**
	 * Changes the size of the {@code Rectangle}.
	 * 
	 * @param size  a new size
	 * @see Vector2
	 */
	@Override
	public void setSize(Vector2 size)
	{
		super.setSize(size);
	}
	
	
	/**
	 * Changes the height of the {@code Rectangle}.
	 * 
	 * @param h  a new height
	 */
	@Override
	public void setHeight(float h)
	{
		super.setHeight(h);
	}
	
	/**
	 * Changes the width of the {@code Rectangle}.
	 * 
	 * @param w  a new width
	 */
	@Override
	public void setWidth(float w)
	{
		super.setWidth(w);
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
		return super.Size();
	}
	
	/**
	 * Returns the height of the {@code Rectangle}.
	 * 
	 * @return  the rectangle's height
	 */
	@Override
	public float Height()
	{
		return super.Height();
	}
	
	/**
	 * Returns the width of the {@code Rectangle}.
	 * 
	 * @return  the rectangle's width
	 */
	@Override
	public float Width()
	{
		return super.Width();
	}
}