package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.spin.Spin2D;
import waffles.utils.geom.spatial.types.Vantage2D;

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