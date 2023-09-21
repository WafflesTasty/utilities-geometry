package waffles.utils.geom.spatial.owners;

import waffles.utils.geom.spatial.Spatial;
import waffles.utils.geom.spatial.owners.types.Vantage;
import waffles.utils.geom.spatial.spin.Spin;

/**
 * An {@code Adjustable} object can be fully transformed in a vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Aligned
 * @see Vantage
 */
public interface Adjustable extends Aligned, Vantage
{
	@Override
	public abstract Spatial Spatial();
	
	@Override
	public default void rotateTo(Spin s)
	{
		Spatial().setSpin(s);
	}

	@Override
	public default Spin Spin()
	{
		return Spatial().Spin();
	}
}