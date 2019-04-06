package zeno.util.geom.collidables.geometry.planar;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collidables.geometry.ICube;

/**
 * The {@code Square} class defines a two-dimensional square shape.
 * 
 * @author Zeno
 * @since Mar 21, 2017
 * @version 1.0
 * 
 * 
 * @see Rectangle
 * @see ICube
 */
public class Square extends Rectangle implements ICube
{
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param x  a square center x-coördinate
	 * @param y  a square center y-coördinate
	 * @param l  a square length
	 */
	public Square(float x, float y, float l)
	{
		this(new Vector2(x, y), l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param center  a square center
	 * @param l       a square length
	 * 
	 * 
	 * @see Vector2
	 */
	public Square(Vector2 center, float l)
	{
		super(center, new Vector2(l, l));
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
		this(1);
	}
}