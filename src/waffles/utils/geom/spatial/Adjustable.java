package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.geom.spatial.types.Vantage;

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
		Spatial.Mutable src = Spatial().Mutator();
		if(src != null)
		{
			src.setSpin(s);
		}
	}

	@Override
	public default Spin Spin()
	{
		return Spatial().Spin();
	}
}