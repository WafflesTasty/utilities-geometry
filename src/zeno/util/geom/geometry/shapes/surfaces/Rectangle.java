package zeno.util.geom.geometry.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.geometry.shapes.NCuboid;
import zeno.util.geom.geometry.types.IShape2D;

/**
 * The {@code Rectangle} class defines a two-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IShape2D
 * @see NCuboid
 */
public class Rectangle extends NCuboid implements IShape2D
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
	 * @param size  the rectangle's size
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
	
	
	@Override
	public Rectangle Bounds()
	{
		return this;
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