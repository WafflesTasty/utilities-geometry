package waffles.utils.geom.spatial.types;

import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Vantage2D} object can be moved and rotated in a two-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Rotatable2D
 * @see Movable2D
 */
public interface Vantage2D extends Movable2D, Rotatable2D
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