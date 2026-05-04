package waffles.utils.geom.spatial.maps.data;

/**
 * A {@code Spatial} object combines an origin, scale and rotation spin.
 * It describes a full Euclidian transformation in space.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 *
 *
 * @see Radial
 * @see Axial
 */
public interface Spatial extends Axial, Radial
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
	 * @see Radial
	 * @see Axial
	 */
	public static interface Mutable extends Spatial, Axial.Mutable, Radial.Mutable
	{
		// NOT APPLICABLE
	}

	
	@Override
	public default int Dimension()
	{
		return Axial.super.Dimension();
	}
}