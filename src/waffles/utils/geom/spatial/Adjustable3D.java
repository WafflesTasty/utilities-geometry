package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.types.Rotatable3D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Adjustable3D} object can be fully transformed in a three-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Adjustable
 * @see Rotatable3D
 * @see Aligned3D
 */
public interface Adjustable3D extends Adjustable, Aligned3D, Rotatable3D
{
	/**
	 * Strafes the {@code Vantage3D} for a given distance.
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
	 * Advances the {@code Vantage3D} for a given distance.
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

	/**
	 * Lifts the {@code Vantage3D} for a given distance.
	 * 
	 * @param d  a distance to lift
	 */
	public default void liftFor(float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Up(), d);
		}
	}
}