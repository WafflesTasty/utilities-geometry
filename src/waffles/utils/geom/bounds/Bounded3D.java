package waffles.utils.geom.bounds;

/**
 * A {@code Bounded} object contains a well-defined three-dimensional boundary.
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