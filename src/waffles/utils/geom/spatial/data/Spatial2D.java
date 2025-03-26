package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Rotated2D;

/**
 * A {@code Spatial2D} object describes an affine position in
 * two-dimensional space. It defines both an origin and size
 * vector, as well as a rotation spin.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Rotated2D
 * @see Axial2D
 */
public interface Spatial2D extends Axial2D, Rotated2D
{
	/**
	 * A {@code Mutable Spatial2D} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Spatial2D
	 * @see Rotated2D
	 * @see Axial2D
	 */
	public static interface Mutable extends Spatial2D, Axial2D.Mutable, Rotated2D.Mutable
	{
		// NOT APPLICABLE
	}
}