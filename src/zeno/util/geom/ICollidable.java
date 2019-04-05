package zeno.util.geom;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom._deprecated.collideables.affine.ASpace;
import zeno.util.geom._deprecated.collideables.affine.Point;

/**
 * The {@code ICollidable} interface defines an object that can be collided with.
 * It allows spatial queries to be performed through affine spaces.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 */
public interface ICollidable
{
	/**
	 * Indicates if the {@code ICollidable} contains a point.
	 * 
	 * @param p  a point to check
	 * @return  {@code true} if the point is contained
	 * 
	 * 
	 * @see Point
	 */
	public abstract boolean contains(Point p);
	
	/**
	 * Indicates if the {@code ICollidable} intersects a space.
	 * 
	 * @param s  a space to check
	 * @return  {@code true} if the space is intersected
	 * 
	 * 
	 * @see ASpace
	 */
	public abstract boolean intersects(ASpace s);
		
	/**
	 * Indicates if the {@code ICollidable} contains a point.
	 * 
	 * @param v  a point to check
	 * @return  {@code true} if the point is contained
	 * 
	 * 
	 * @see Vector
	 */
	public default boolean contains(Vector v)
	{
		return contains(new Point(v));
	}
}