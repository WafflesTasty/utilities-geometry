package waffles.utils.geom.bounds;

/**
 * A {@code Bounded} object contains a well-defined n-dimensional boundary.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 */
@FunctionalInterface
public interface Bounded
{	
	/**
	 * Returns the bounds of the {@code Bounded} object.
	 * 
	 * @return  an object boundary
	 * 
	 * 
	 * @see Bounds
	 */
	public abstract Bounds Bounds();
}