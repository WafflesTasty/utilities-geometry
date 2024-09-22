package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.spatial.data.unary.Scaled2D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Scalable2D} object can be scaled in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.1
 * 
 * 
 * @see Scalable
 * @see Scaled2D
 */
public interface Scalable2D extends Scalable, Scaled2D
{	
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

		
	@Override
	public default Vector2 Size()
	{
		return (Vector2) Scalable.super.Size();
	}
}