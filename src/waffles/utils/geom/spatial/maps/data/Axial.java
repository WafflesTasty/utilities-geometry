package waffles.utils.geom.spatial.maps.data;

import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.geom.spatial.maps.data.unary.Scaled;

/**
 * An {@code Axial} object combines an origin and scale vector.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Positioned
 * @see Scaled
 */
public interface Axial extends Positioned, Scaled
{
	/**
	 * A {@code Mutable Axial} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Axial
	 * @see Positioned
	 * @see Scaled
	 */
	public static interface Mutable extends Axial, Positioned.Mutable, Scaled.Mutable
	{
		// NOT APPLICABLE
	}
}