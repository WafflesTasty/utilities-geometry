package waffles.utils.geom.spatial.maps.data;

import waffles.utils.geom.spatial.maps.data.unary.Positioned3D;
import waffles.utils.geom.spatial.maps.data.unary.Rotated3D;

/**
 * A {@code Radial3D} object combines a three-dimensional origin and spin.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 *
 *
 * @see Positioned3D
 * @see Rotated3D
 * @see Radial
 */
public interface Radial3D extends Radial, Positioned3D, Rotated3D
{
	/**
	 * A {@code Mutable Radial3D} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 *
	 *
	 * @see Radial3D
	 * @see Positioned3D
	 * @see Rotated3D
	 * @see Axial
	 */
	public static interface Mutable extends Radial3D, Radial.Mutable, Positioned3D.Mutable, Rotated3D.Mutable
	{
		// NOT APPLICABLE
	}

	
	@Override
	public default int Dimension()
	{
		return 3;
	}
}