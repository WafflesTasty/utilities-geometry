package waffles.utils.geom.collidable.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.bounds.axial.cuboid.BNDCuboid2D;
import waffles.utils.geom.collidable.axial.AxialSet2D;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code Rectangle} defines a two-dimensional cuboid shape.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see AxialSet2D
 * @see ICuboid
 */
public class Rectangle extends AxialSet2D implements ICuboid
{		
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param x  a center x-coordinate
	 * @param y  a center y-coordinate
	 * @param w  a width
	 * @param h  a height
	 */
	public Rectangle(float x, float y, float w, float h)
	{
		this(new Vector2(x, y), new Vector2(w, h));
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param c  a center vector
	 * @param s  a size vector
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
	 * @param c  a center vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector2
	 * @see Point
	 */
	public Rectangle(Point c, Vector2 s)
	{
		this((Vector2) c.Generator(), s);
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
	 * @param s  a size vector
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
	public Rectangle create(Vector o, Vector s)
	{
		return new Rectangle
		(	(Vector2) o, 
			(Vector2) s
		);
	}
		
	@Override
	public Bounds2D Bounds(GlobalMap map)
	{
		return new BNDCuboid2D(this, map);
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
}