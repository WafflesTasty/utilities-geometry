package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.collisions.convex.CLSConvex;

/**
 * The {@code IConvex} interface is the base for convex n-dimensional shapes.
 * </br> It performs collision detection through an iterative algorithm.
 *
 * @author Zeno
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface IConvex extends IGeometry
{
	/**
	 * The {@code Extremum} interface generates extremal points on an {@code IConvex}.
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
	 * Returns the extremum of an {@code IConvex}.
	 * 
	 * @return  an extremum
	 * 
	 * 
	 * @see Extremum
	 */
	public abstract Extremum Extremum();
	
	/**
	 * Returns the Minkowski difference with an {@code IConvex}.
	 * 
	 * @param c  a convex shape
	 * @return   a minkowski difference
	 */
	public default IConvex minus(IConvex c)
	{
		return new DConvex(this, c);
	}
	
	
	@Override
	public default CLSConvex Collisions()
	{
		return new CLSConvex(this);
	}
}