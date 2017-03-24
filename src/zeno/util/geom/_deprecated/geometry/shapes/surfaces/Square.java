package zeno.util.geom._deprecated.geometry.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom._deprecated.geometry.shapes.NCube;
import zeno.util.geom._deprecated.geometry.types.IShape2D;

/**
 * The {@code Square} class defines a two-dimensional square shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see IShape2D
 * @see NCube
 */
public class Square extends NCube implements IShape2D
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

	
	@Override
	public Rectangle Bounds()
	{
		return IShape2D.super.Bounds();
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