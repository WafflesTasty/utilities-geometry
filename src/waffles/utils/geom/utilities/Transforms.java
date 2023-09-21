package waffles.utils.geom.utilities;

import waffles.utils.geom.collidable.axial.spheroid.ISpheroid;
import waffles.utils.geom.maps.affine.StandardMap;

/**
 * The {@code Transforms} class provides static utility methods to generate {@code GlobalMap} objects.
 * The maps generated by this class tend to be derived from geometric objects.
 *
 * @author Waffles
 * @since 14 Sep 2023
 * @version 1.0
 */
public final class Transforms
{
	/**
	 * Creates a map from the unit sphere to an {@code ISpheroid}.
	 * 
	 * @param e  an ellipsoid
	 * @return   an affine map
	 * 
	 * 
	 * @see StandardMap
	 * @see ISpheroid
	 */
	public static StandardMap fromUSphere(ISpheroid e)
	{
		StandardMap map = new StandardMap(e.Dimension());
		map.setSize(e.Size().times(0.5f));
		map.setOrigin(e.Origin());
		return map;
	}


	private Transforms()
	{
		// NOT APPLICABLE
	}
}