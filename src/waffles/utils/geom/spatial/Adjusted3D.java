package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.maps.data.Spatial3D;
import waffles.utils.geom.spatial.owners.Rotatable3D;

/**
 * An {@code Adjusted3D} object can be affine transformed in a three-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Adjusted
 * @see Aligned3D
 * @see Rotatable3D
 * @see Spatial3D
 */
public interface Adjusted3D extends Adjusted, Aligned3D, Rotatable3D, Spatial3D
{
	/**
	 * Strafes the {@code Vantage3D} for a given distance.
	 * 
	 * @param d  a distance value
	 */
	public default void strafeFor(float d)
	{
		if(ERROR < d)
		{
			moveFor(0, d);
		}
	}
		
	/**
	 * Advances the {@code Vantage3D} for a given distance.
	 * 
	 * @param d  a distance value
	 */
	public default void advanceFor(float d)
	{
		if(ERROR < d)
		{
			moveFor(1, d);
		}
	}

	/**
	 * Lifts the {@code Vantage3D} for a given distance.
	 * 
	 * @param d  a distance value
	 */
	public default void liftFor(float d)
	{
		if(ERROR < d)
		{
			moveFor(2, d);
		}
	}
}