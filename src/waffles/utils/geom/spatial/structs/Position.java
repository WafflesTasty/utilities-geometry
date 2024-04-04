package waffles.utils.geom.spatial.structs;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.data.unary.Positioned;
import waffles.utils.geom.spatial.maps.fixed.linear.Translation;

/**
 * A {@code Position} defines a {@code Positioned.Mutable} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.1
 * 
 * 
 * @see Positioned
 */
public class Position implements Positioned.Mutable
{
	private Vector origin;

	/**
	 * Creates a new {@code Position}.
	 * 
	 * @param o  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Position(Vector o)
	{
		origin = o;
	}
	
	/**
	 * Creates a new {@code Position}.
	 * 
	 * @param dim  an axis dimension
	 */
	public Position(int dim)
	{
		this
		(
			Translation.Default(dim)
		);
	}
	
	/**
	 * Creates a new {@code Position}.
	 */
	public Position()
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public void setOrigin(Vector o)
	{
		origin = o;
	}
	
	@Override
	public Vector Origin()
	{
		return origin;
	}
}