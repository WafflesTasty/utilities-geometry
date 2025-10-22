package waffles.utils.geom.spatial.bounds.owners;

import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code Bounded} object defines a three-dimensional {@code Bounds}.
 *
 * @author Waffles
 * @since Aug 25, 2015
 * @version 1.0
 *
 *
 * @see Bounded
 */
@FunctionalInterface
public interface Bounded3D extends Bounded
{
	@Override
	public abstract Bounds3D Bounds();
}