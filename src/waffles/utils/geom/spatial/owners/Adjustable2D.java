package waffles.utils.geom.spatial.owners;

import waffles.utils.geom.spatial.owners.types.Vantage2D;
import waffles.utils.geom.spatial.spin.Spin2D;

/**
 * An {@code Adjustable2D} object can be fully transformed in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Adjustable
 * @see Aligned2D
 * @see Vantage2D
 */
public interface Adjustable2D extends Adjustable, Aligned2D, Vantage2D
{
	@Override
	public default Spin2D Spin()
	{
		return (Spin2D) Spatial().Spin();
	}
}