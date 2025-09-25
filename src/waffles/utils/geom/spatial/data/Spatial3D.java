package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Rotated3D;

/**
 * A {@code Spatial3D} object combines a three-dimensional origin, scale and rotation spin.
 * It describes a full three-dimensional Euclidian transformation in space.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Spatial
 * @see Rotated3D
 * @see Axial3D
 */
public interface Spatial3D extends Spatial, Axial3D, Rotated3D
{
	/**
	 * A {@code Mutable Spatial3D} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Spatial
	 * @see Spatial3D
	 * @see Rotated3D
	 * @see Axial3D
	 */
	public static interface Mutable extends Spatial3D, Spatial.Mutable, Axial3D.Mutable, Rotated3D.Mutable
	{
		// NOT APPLICABLE
	}
}