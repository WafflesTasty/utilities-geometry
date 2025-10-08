package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.fixed.Vector2;
import waffles.utils.geom.spatial.data.unary.Scaled2D;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Scalable2D} object can be scaled in a two-dimensional vector space.
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
	 * @param w  a width scale
	 * @param h  a height scale
	 */
	public default void scaleFor(float w, float h)
	{
		if(ERROR < Floats.abs(w - 1f) || ERROR < Floats.abs(h - 1f))
		{
			scaleFor(new Vector2(w, h));
		}
	}

	/**
	 * Scales the {@code Scalable2D} to a new scale.
	 * 
	 * @param w  a width scale
	 * @param h  a height scale
	 */
	public default void scaleTo(float w, float h)
	{
		scaleTo(new Vector2(w, h));
	}

		
	@Override
	public default Vector2 Scale()
	{
		return (Vector2) Scalable.super.Scale();
	}
}