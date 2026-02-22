package waffles.utils.geom.spatial.maps.data;

import waffles.utils.geom.spatial.maps.data.unary.Positioned2D;
import waffles.utils.geom.spatial.maps.data.unary.Rotated2D;

/**
 * A {@code Radial2D} object combines a two-dimensional origin and spin.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 *
 *
 * @see Positioned2D
 * @see Rotated2D
 * @see Radial
 */
public interface Radial2D extends Radial, Positioned2D, Rotated2D
{
	/**
	 * A {@code Mutable Radial2D} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 *
	 *
	 * @see Radial2D
	 * @see Positioned2D
	 * @see Rotated2D
	 * @see Axial
	 */
	public static interface Mutable extends Radial2D, Radial.Mutable, Positioned2D.Mutable, Rotated2D.Mutable
	{
		// NOT APPLICABLE
	}

	
	@Override
	public default int Dimension()
	{
		return 2;
	}
}