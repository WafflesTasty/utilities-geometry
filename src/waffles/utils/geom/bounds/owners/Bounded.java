package waffles.utils.geom.bounds.owners;

import waffles.utils.geom.bounds.Bounds;

/**
 * A {@code Bounded} object defines an n-dimensional {@code Bounds}.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 */
@FunctionalInterface
public interface Bounded
{	
	/**
	 * Returns the bounds of the {@code Bounded}.
	 * 
	 * @return  a boundary
	 * 
	 * 
	 * @see Bounds
	 */
	public abstract Bounds Bounds();
}