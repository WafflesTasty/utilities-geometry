package waffles.utils.geom.spaces;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.geomold.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geomold.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geomold.collidable.fixed.Point;

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
 */
public interface Space<O> extends Dimensional
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
	public abstract Iterable<O> query(HyperCuboid c);
		
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