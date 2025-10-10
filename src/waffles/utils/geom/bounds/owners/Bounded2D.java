package waffles.utils.geom.bounds.owners;

import waffles.utils.geom.bounds.Bounds2D;

/**
 * A {@code Bounded} object defines a two-dimensional {@code Bounds}.
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