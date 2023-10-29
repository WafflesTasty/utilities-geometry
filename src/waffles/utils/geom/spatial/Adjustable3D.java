package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.spin.Spin3D;
import waffles.utils.geom.spatial.types.Vantage3D;

/**
 * An {@code Adjustable3D} object can be fully transformed in a three-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Adjustable
 * @see Aligned3D
 * @see Vantage3D
 */
public interface Adjustable3D extends Adjustable, Aligned3D, Vantage3D
{
	@Override
	public default Spin3D Spin()
	{
		return (Spin3D) Spatial().Spin();
	}
}