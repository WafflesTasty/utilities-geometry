package zeno.util.geom._refactor.shapes.surfaces.lateral;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom._refactor.shapes.surfaces.Lateral2D;

/**
 * The {@code Square} class defines a two-dimensional square shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see Lateral2D
 */
public class Square extends Lateral2D
{
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param l  a square length
	 */
	public Square(float x, float y, float l)
	{
		super(x, y, l, l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param center  a center
	 * @param l  a square length
	 * @see Vector2
	 */
	public Square(Vector2 center, float l)
	{
		this(center.X(), center.Y(), l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 * 
	 * @param l  a square length
	 */
	public Square(float l)
	{
		this(0, 0, l);
	}
	
	/**
	 * Creates a new {@code Square}.
	 */
	public Square()
	{
		this(1);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Square)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	/**
	 * Changes the length of the {@code Square}.
	 * 
	 * @param l  a new square length
	 */
	public void setLength(float l)
	{
		super.setSize(Math.abs(l), Math.abs(l));
	}
			
	/**
	 * Returns the length of the {@code Square}.
	 * 
	 * @return  the square's length
	 */
	public float Length()
	{
		return Width();
	}
}