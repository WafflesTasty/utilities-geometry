package zeno.util.geom.shapes.surfaces.conic;

import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.shapes.surfaces.Conic;

/**
 * The {@code Ellipse} class defines a two-dimensional elliptical shape.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Conic
 */
public class Ellipse extends Conic
{
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param vcount  a vertex count
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param w  a width
	 * @param h  a height
	 */
	public Ellipse(int vcount, float x, float y, float w, float h)
	{
		super(vcount, x, y, w, h);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param vcount  a vertex count
	 * @param center  a center
	 * @param size  a size
	 * @see Vector2
	 */
	public Ellipse(int vcount, Vector2 center, Vector2 size)
	{
		this(vcount, center.X(), center.Y(), size.X(), size.Y());
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param w  a width
	 * @param h  a height
	 */
	public Ellipse(float x, float y, float w, float h)
	{
		super(x, y, w, h);
	}
		
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param center  a center
	 * @param size  a size
	 * @see Vector2
	 */
	public Ellipse(Vector2 center, Vector2 size)
	{
		this(center.X(), center.Y(), size.X(), size.Y());
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param vcount  a vertex count
	 * @param w  a width
	 * @param h  a height
	 */
	public Ellipse(int vcount, float w, float h)
	{
		this(vcount, 0, 0, w, h);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param vcount  a vertex count
	 * @param size  a size
	 * @see Vector2
	 */
	public Ellipse(int vcount, Vector2 size)
	{
		this(vcount, 0, 0, size.X(), size.Y());
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 */
	public Ellipse(float w, float h)
	{
		this(0, 0, w, h);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param size  a size
	 * @see Vector2
	 */
	public Ellipse(Vector2 size)
	{
		this(0, 0, size.X(), size.Y());
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param vcount  a vertex count
	 */
	public Ellipse(int vcount)
	{
		this(vcount, 1, 1);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 */
	public Ellipse()
	{
		this(1, 1);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Ellipse)
		{
			return super.equals(o);
		}
		
		return false;
	}
		
	/**
	 * Changes the dimension of the {@code Ellipse}.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 */
	@Override
	public void setSize(float w, float h)
	{
		super.setSize(Math.abs(w), Math.abs(h));
	}
	
	/**
	 * Changes the dimension of the {@code Ellipse}.
	 * 
	 * @param size  a new size
	 * @see Vector2
	 */
	@Override
	public void setSize(Vector2 size)
	{
		setSize(size.X(), size.Y());
	}
	
	
	/**
	 * Changes the height of the {@code Ellipse}.
	 * 
	 * @param h  a new height
	 */
	@Override
	public void setHeight(float h)
	{
		setSize(Width(), h);
	}
	
	/**
	 * Changes the width of the {@code Ellipse}.
	 * 
	 * @param w  a new width
	 */
	@Override
	public void setWidth(float w)
	{
		setSize(w, Height());
	}
	
	
	/**
	 * Returns the size of the {@code Ellipse}.
	 * 
	 * @return  the ellipse's size
	 * @see Vector2
	 */
	@Override
	public Vector2 Size()
	{
		return super.Size();
	}
	
	/**
	 * Returns the {@code Ellipse}'s height.
	 * 
	 * @return  the ellipse's height
	 */
	@Override
	public float Height()
	{
		return super.Height();
	}
	
	/**
	 * Returns the {@code Ellipse}'s width.
	 * 
	 * @return  the ellipse's width
	 */
	@Override
	public float Width()
	{
		return super.Width();
	}
}