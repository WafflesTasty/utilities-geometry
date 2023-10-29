package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Scalable2D} object can be scaled in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Scalable
 */
public interface Scalable2D extends Scalable
{	
	@Override
	public abstract Vector2 Size();
	
	
	/**
	 * Scales the {@code Scalable2D} with a given factor.
	 * 
	 * @param w  a width  factor
	 * @param h  a height factor
	 */
	public default void scaleFor(float w, float h)
	{
		if(!Floats.isEqual(w, 1f, 1) || !Floats.isEqual(h, 1f, 1))
		{
			scaleFor(new Vector2(w, h));
		}
	}

	/**
	 * Scales the {@code Scalable2D} to a new scale vector.
	 * 
	 * @param w  a scale width
	 * @param h  a scale height
	 */
	public default void scaleTo(float w, float h)
	{
		scaleTo(new Vector2(w, h));
	}

		
	/**
	 * Returns the height of the {@code Scalable2D}.
	 * 
	 * @return  a height factor
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the width of the {@code Scalable2D}.
	 * 
	 * @return  a width factor
	 */
	public default float Width()
	{
		return Size().X();
	}
}