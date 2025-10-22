package waffles.utils.geom.shapes;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.Collideable2D;
import waffles.utils.geom.spatial.bounds.Bounds2D;
import waffles.utils.geom.spatial.bounds.owners.Boundable2D;

/**
 * A {@code Geometry2D} defines a two-dimensional {@code Geometry}.
 *
 * @author Waffles
 * @since Aug 22, 2015
 * @version 1.0
 *
 *
 * @see Collideable2D
 * @see Boundable2D
 * @see Geometry
 */
public interface Geometry2D extends Geometry, Collideable2D, Boundable2D
{
	@Override
	public abstract Bounds2D Bounds(LinearMap map);
}