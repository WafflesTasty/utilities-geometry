package waffles.utils.geom.collidable.shaped;

import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.spatial.Adjustable;
import waffles.utils.geom.spatial.maps.spatial.SpatialMap;

/**
 * A {@code Shapeable} object defines a fully adjustable, n-dimensional geometric object.
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
public interface Shapeable extends Adjustable, Geometrical
{
	@Override
	public abstract SpatialMap Transform();
}