package waffles.utils.geom.spatial.bounds.owners;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.maps.IdentityMap;

/**
 * A {@code Boundable} object defines an n-dimensional transformable {@code Bounds}.
 *
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 *
 *
 * @see Bounded
 */
@FunctionalInterface
public interface Boundable extends Bounded
{
	/**
	 * Returns the bounds of the {@code Boundable}.
	 *
	 * @param m  a linear map
	 * @return   a boundary
	 *
	 *
	 * @see LinearMap
	 * @see Bounds
	 */
	public abstract Bounds Bounds(LinearMap m);


	@Override
	public default Bounds Bounds()
	{
		return Bounds(new IdentityMap());
	}
}