package waffles.utils.geom.spatial.maps.data;

import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.geom.spatial.maps.data.unary.Rotated;

/**
 * A {@code Radial} object combines an origin and a spin.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Positioned
 * @see Rotated
 */
public interface Radial extends Positioned, Rotated
{
	/**
	 * A {@code Mutable Radial} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Radial
	 * @see Positioned
	 * @see Rotated
	 */
	public static interface Mutable extends Radial, Positioned.Mutable, Rotated.Mutable
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public default int Dimension()
	{
		return Positioned.super.Dimension();
	}
}