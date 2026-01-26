package waffles.utils.geom.shapes.convex;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.convex.CLSConvex;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.bounds.convex.BNDConvex;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.maps.data.Axial;

/**
 * A {@code ConvexSet} defines a bounded convex set in n-dimensional space.
 * Each set comes equipped with an {@code Extremum} operator to compute
 * boundary points. This operation is used as a utility method
 * in various convex optimization algorithms.
 *
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 *
 *
 * @see Geometry
 * @see Axial
 */
public interface ConvexSet extends Axial, Geometry
{
	/**
	 * An {@code Extremum} computes boundary points on a {@code ConvexSet}.
	 * Given a Point p, the {@link #along(Point)} function returns a point
	 * y in the convex set which satisfies p &centerdot; (x-y) &lt; 0
	 * for any x on the set.
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
		 * @see Point
		 */
		public default Point along(Vector v)
		{
			return along(new Point(v, 0f));
		}

		/**
		 * Returns an extremum along a {@code Point}.
		 *
		 * @param p  a target point
		 * @return   a convex extremum
		 *
		 *
		 * @see Point
		 */
		public abstract Point along(Point p);
	}

	/**
	 * Returns a {@code ConvexSet} extremum.
	 *
	 * @return  an extremum operator
	 *
	 *
	 * @see Extremum
	 */
	public abstract Extremum Extremum();

			
	@Override
	public default Bounds Bounds()
	{
		return new BNDConvex(this);
	}
		
	@Override
	public default Bounds Bounds(LinearMap m)
	{
		return new BNDConvex(this, m);
	}
	
	@Override
	public default int Dimension()
	{
		return Scale().Dimension();
	}
	
	
	@Override
	public default Collision Collision()
	{
		return new CLSConvex(this);
	}
		
	@Override
	public default Arrow Scale()
	{
		return Bounds().Scale();
	}
}