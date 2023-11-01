package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Positioned;
import waffles.utils.geom.spatial.data.unary.Scaled;

/**
 * A {@code Axial} object describes an axis-aligned position in space.
 * It defines both an origin and a size vector.
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
	 */
	public static interface Mutable extends Axial, Positioned.Mutable, Scaled.Mutable
	{
		// NOT APPLICABLE
	}
}