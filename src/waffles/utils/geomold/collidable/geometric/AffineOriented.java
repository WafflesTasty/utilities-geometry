package waffles.utils.geom.collidable.geometric;

import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.spatial.Adjustable;
import waffles.utils.geom.spatial.maps.spatial.SpatialMap;

/**
 * A {@code AffineOriented} object defines an arbitrarily oriented, n-dimensional geometric object.
 * It defines a shape, a spatial transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Adjustable
 */
public interface AffineOriented extends Adjustable, Geometrical
{
	@Override
	public abstract SpatialMap Transform();
}