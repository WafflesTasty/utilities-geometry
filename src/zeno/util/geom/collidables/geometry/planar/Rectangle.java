package zeno.util.geom.collidables.geometry.planar;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collidables.geometry.ICuboid;
import zeno.util.geom.collidables.geometry.generic.Geometry2D;

/**
 * The {@code Rectangle} class defines a two-dimensional rectangular shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see Geometry2D
 * @see ICuboid
 */
public class Rectangle extends Geometry2D implements ICuboid
{	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param x  a rectangle center x-coördinate
	 * @param y  a rectangle center y-coördinate
	 * @param w  a rectangle width
	 * @param h  a rectangle height
	 */
	public Rectangle(float x, float y, float w, float h)
	{
		this(new Vector2(x, y), new Vector2(w, h));
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param center  a rectangle center
	 * @param size    a rectangle size
	 * 
	 * 
	 * @see Vector2
	 */
	public Rectangle(Vector2 center, Vector2 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param w  a rectangle width
	 * @param h  a rectangle height
	 */
	public Rectangle(float w, float h)
	{
		this(0, 0, w, h);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param size  a rectangle size
	 * 
	 * 
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
	public Rectangle Box()
	{
		return this;
	}
}