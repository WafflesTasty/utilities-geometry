package waffles.utils.geom.spaces;

import java.util.Iterator;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.tools.patterns.properties.Queryable;

/**
 * A {@code Space} defines a data structure that handles spatial queries.
 * 
 * @author Waffles
 * @since Mar 29, 2017
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see Dimensional
 * @see Queryable
 */
public interface Space<O> extends Dimensional, Queryable<O>
{
	/**
	 * A {@code Space.Query} defines queries for a {@code Space}.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see Queryable
	 */
	@FunctionalInterface
	public static interface Query<O> extends Queryable.Query<O>
	{
		/**
		 * Queries the {@code Space} at a given cuboid.
		 * 
		 * @param c  a cuboid area
		 * @return   an object set
		 * 
		 * 
		 * @see HyperCuboid
		 * @see Iterator
		 */
		public default Iterator<O> in(HyperCuboid c)
		{
			return All();
		}
			
		/**
		 * Queries the {@code Space} at a given point.
		 * 
		 * @param p  a target point
		 * @return   an object set
		 * 
		 * 
		 * @see Iterator
		 * @see Point
		 */
		public default Iterator<O> at(Point p)
		{
			return All();
		}
	}
	
	
	/**
	 * Queries the {@code Space} at a given vector.
	 * 
	 * @param v  a point vector
	 * @return   an object set
	 * 
	 * 
	 * @see Iterable
	 * @see Vector
	 */
	public default Iterable<O> query(Vector v)
	{
		return query(new Point(v, 1f));
	}
	
	/**
	 * Queries the {@code Space} at a given sphere.
	 * 
	 * @param s  a sphere area
	 * @return   an object set
	 * 
	 * 
	 * @see HyperSphere
	 * @see Iterable
	 */
	public default Iterable<O> query(HyperSphere s)
	{
		return query(s.Bounds().Box());
	}

	
	/**
	 * Queries the {@code Space} at a given cuboid.
	 * 
	 * @param c  a cuboid area
	 * @return   an object set
	 * 
	 * 
	 * @see HyperCuboid
	 * @see Iterable
	 */
	public default Iterable<O> query(HyperCuboid c)
	{
		return () -> Query().in(c);
	}
		
	/**
	 * Queries the {@code Space} at a given point.
	 * 
	 * @param p  a target point
	 * @return   an object set
	 * 
	 * 
	 * @see Iterable
	 * @see Point
	 */
	public default Iterable<O> query(Point p)
	{
		return () -> Query().at(p);
	}


	@Override
	public abstract Query<O> Query();
}