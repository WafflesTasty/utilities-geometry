package waffles.utils.geom.spatial.owners;

import waffles.utils.geom.spatial.owners.types.Vantage3D;
import waffles.utils.geom.spatial.spin.Spin3D;

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