package waffles.utils.geom.spatial.maps.data;

import waffles.utils.geom.spatial.maps.data.unary.Rotated2D;

/**
 * A {@code Spatial2D} object combines a two-dimensional origin, scale and rotation spin.
 * It describes a full two-dimensional Euclidian transformation in space.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Rotated2D
 * @see Axial2D
 * @see Spatial
 */
public interface Spatial2D extends Spatial, Axial2D, Rotated2D
{
	/**
	 * A {@code Mutable Spatial2D} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Spatial
	 * @see Spatial2D
	 * @see Rotated2D
	 * @see Axial2D
	 */
	public static interface Mutable extends Spatial2D, Spatial.Mutable, Axial2D.Mutable, Rotated2D.Mutable
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public default int Dimension()
	{
		return 2;
	}
}