package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.types.Rotatable2D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Adjustable2D} object can be fully transformed in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Adjustable
 * @see Rotatable2D
 * @see Aligned2D
 */
public interface Adjustable2D extends Adjustable, Aligned2D, Rotatable2D
{
	/**
	 * Strafes the {@code Vantage2D} for a given distance.
	 * 
	 * @param d  a distance to strafe
	 */
	public default void strafeFor(float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Right(), d);
		}
	}
		
	/**
	 * Advances the {@code Vantage2D} for a given distance.
	 * 
	 * @param d  a distance to advance
	 */
	public default void advanceFor(float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Forward(), d);
		}
	}
}