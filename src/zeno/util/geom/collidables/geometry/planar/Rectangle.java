package zeno.util.geom.collidables.geometry.planar;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.Geometry2D;
import zeno.util.geom.collidables.geometry.bounds.BNDCuboid2D;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

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
	 * @param x  a rectangle center x-co�rdinate
	 * @param y  a rectangle center y-co�rdinate
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
	 * @param c  a rectangle center
	 * @param s  a rectangle size
	 * 
	 * 
	 * @see Vector2
	 */
	public Rectangle(Vector2 c, Vector2 s)
	{
		super(c, s);
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
	 * @param s  a rectangle size
	 * 
	 * 
	 * @see Vector2
	 */
	public Rectangle(Vector2 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 */
	public Rectangle()
	{
		super();
	}
	
		
	@Override
	public Bounds2D Bounds(ITransformation map)
	{
		return new BNDCuboid2D(this, map);
	}
	
	// Optional Bounds overrides.
		
	@Override
	public Rectangle Box()
	{
		return this;
	}
}