package zeno.util.geom.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.shapes.NCube;

/**
 * The {@code Square} class defines a two-dimensional square shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see NCube
 */
public class Square extends NCube
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
		super(center, l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param l  the square's length
	 */
	public Square(float l)
	{
		super(2, l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 */
	public Square()
	{
		this(1);
	}
}