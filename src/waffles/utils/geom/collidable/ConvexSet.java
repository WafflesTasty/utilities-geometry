package waffles.utils.geom.collidable;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collision.convex.CLSConvex;

/**
 * The {@code ConvexSet} interface is the base for convex n-dimensional shapes.
 * It defines an {@code Extremum} operator which is used as a utility
 * function in convex optimization algorithms.
 *
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see Geometry
 */
public interface ConvexSet extends Geometry
{
	/**
	 * An {@code Extremum} computes boundary points on a {@code ConvexSet}.
	 * Given a vector v, the {@link #along(Vector)} function returns a vector
	 * y on the set which satisfies v &centerdot; (x-y) &lt; 0 for any x on the set.
	 * 
	 * @author Waffles
	 * @since 01 Sep 2021
	 * @version 1.0
	 */
	@FunctionalInterface
	public static interface Extremum
	{
		/**
		 * Returns an extremum along a {@code Vector}.
		 * 
		 * @param v  a target vector
		 * @return   a convex extremum
		 * 
		 * 
		 * @see Vector
		 */
		public abstract Vector along(Vector v);
	}
	
	/**
	 * Returns the extremum of the {@code ConvexSet}.
	 * 
	 * @return  an extremum operator
	 * 
	 * 
	 * @see Extremum
	 */
	public abstract Extremum Extremum();

	
	@Override
	public default Collision Collisions()
	{
		return new CLSConvex(this);
	}
}