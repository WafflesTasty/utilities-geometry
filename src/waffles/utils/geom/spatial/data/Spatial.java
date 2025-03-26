package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Rotated;

/**
 * A {@code Spatial} object describes an affine position in
 * n-dimensional space. It defines both an origin and size
 * vector, as well as a rotation spin.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Rotated
 * @see Axial
 */
public interface Spatial extends Axial, Rotated
{
	/**
	 * A {@code Mutable Spatial} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Spatial
	 * @see Rotated
	 * @see Axial
	 */
	public static interface Mutable extends Spatial, Axial.Mutable, Rotated.Mutable
	{
		// NOT APPLICABLE
	}
}