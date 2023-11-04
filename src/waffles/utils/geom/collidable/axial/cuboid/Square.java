package waffles.utils.geom.collidable.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Square} defines a two-dimensional cube shape.
 * 
 * @author Waffles
 * @since Mar 21, 2017
 * @version 1.0
 * 
 * 
 * @see Rectangle
 * @see HyperCube
 */
public class Square extends Rectangle implements HyperCube
{
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param x  a center x-coordinate
	 * @param y  a center y-coordinate
	 * @param l  a square length
	 */
	public Square(float x, float y, float l)
	{
		this(new Vector2(x, y), l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param c  a center vector
	 * @param l  a square length
	 * 
	 * 
	 * @see Vector2
	 */
	public Square(Vector2 c, float l)
	{
		super(c, new Vector2(l, l));
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param c  a point center
	 * @param l  a square length
	 * 
	 * 
	 * @see Point
	 */
	public Square(Point c, float l)
	{
		this((Vector2) c.Generator(), l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param l  a square length
	 */
	public Square(float l)
	{
		super(l, l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 */
	public Square()
	{
		super();
	}
}