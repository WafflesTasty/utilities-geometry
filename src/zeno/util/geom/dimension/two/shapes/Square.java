package zeno.util.geom.dimension.two.shapes;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.utilities.shapes.ICube;

/**
 * The {@code Square} class defines a two-dimensional square shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see Rectangle
 * @see ICube
 */
public class Square extends Rectangle implements ICube
{
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param x  the square's center x-coördinate
	 * @param y  the square's center y-coördinate
	 * @param l  the square's length
	 */
	public Square(float x, float y, float l)
	{
		this(new Vector2(x, y), l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param center  the square's center
	 * @param l  the square's length
	 * @see Vector2
	 */
	public Square(Vector2 center, float l)
	{
		super(center, new Vector2(l, l));
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param l  the square's length
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