package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Spatial3D;
import waffles.utils.geom.spatial.owners.Rotatable3D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Adjustable3D} object can be affine transformed in a three-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Aligned3D
 * @see Adjustable
 * @see Rotatable3D
 * @see Spatial3D
 */
public interface Adjustable3D extends Adjustable, Aligned3D, Rotatable3D, Spatial3D
{
	/**
	 * Strafes the {@code Vantage3D} for a given distance.
	 * 
	 * @param d  a distance value
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
	 * @param d  a distance value
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
	 * @param d  a distance value
	 */
	public default void liftFor(float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Up(), d);
		}
	}
}