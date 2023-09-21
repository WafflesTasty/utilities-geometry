package waffles.utils.geom.spaces;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.axial.cuboid.ICuboid;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Space} is a set of n-dimensional objects capable of handling spatial queries.
 * 
 * @author Waffles
 * @since Mar 29, 2017
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 */
public interface Space<O>
{
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
	 * Queries the {@code Space} at a given cuboid.
	 * 
	 * @param c  a cuboid area
	 * @return   an object set
	 * 
	 * 
	 * @see Iterable
	 * @see ICuboid
	 */
	public abstract Iterable<O> query(ICuboid c);
	
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
	public abstract Iterable<O> query(Point p);
}