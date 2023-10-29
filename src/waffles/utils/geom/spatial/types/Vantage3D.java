package waffles.utils.geom.spatial.types;

import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Vantage3D} object can be moved and rotated in a three-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 21, 2016
 * @version 1.0
 * 
 * 
 * @see Rotatable3D
 * @see Movable3D
 */
public interface Vantage3D extends Movable3D, Rotatable3D
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