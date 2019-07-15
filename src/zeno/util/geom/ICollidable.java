package zeno.util.geom;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;

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
	 * @param p  an affine point to check
	 * @return  {@code true} if the point is contained
	 * 
	 * 
	 * @see Point
	 */
	public abstract boolean contains(Point p);
	
	/**
	 * Indicates if the {@code ICollidable} intersects an affine set.
	 * 
	 * @param a  an affine set to check
	 * @return  {@code true} if the set is intersected
	 * 
	 * 
	 * @see Affine
	 */
	public abstract boolean intersects(Affine a);

	
	/**
	 * Indicates if the {@code ICollidable} contains an affine set.
	 * 
	 * @param a  an affine set to check
	 * @return  {@code true} if the set is contained
	 * 
	 * 
	 * @see Affine
	 */
	public default boolean contains(Affine a)
	{
		for(Point p : a.Span())
		{
			if(!contains(p))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Indicates if the {@code ICollidable} contains a point.
	 * 
	 * @param v  a vector point to check
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