package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.maps.data.Spatial2D;
import waffles.utils.geom.spatial.owners.Rotatable2D;

/**
 * An {@code Adjustable2D} object can be affine transformed in a two-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Aligned2D
 * @see Adjustable
 * @see Rotatable2D
 * @see Spatial2D
 */
public interface Adjustable2D extends Adjustable, Aligned2D, Rotatable2D, Spatial2D
{
	/**
	 * Strafes the {@code Vantage2D} for a given distance.
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
	 * Advances the {@code Vantage2D} for a given distance.
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
}