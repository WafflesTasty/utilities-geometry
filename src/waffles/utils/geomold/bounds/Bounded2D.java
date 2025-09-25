package waffles.utils.geom.bounds;

/**
 * A {@code Bounded} object contains a well-defined two-dimensional boundary.
 *
 * @author Waffles
 * @since Aug 25, 2015
 * @version 1.0
 * 
 * 
 * @see Bounded
 */
@FunctionalInterface
public interface Bounded2D extends Bounded
{		
	@Override
	public abstract Bounds2D Bounds();
}