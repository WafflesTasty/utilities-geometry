package waffles.utils.geom.spatial.owners.types;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Movable} object can be moved around a vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface Movable
{
	/**
	 * Returns the origin of the {@code Movable}.
	 * 
	 * @return  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Origin();
			
	/**
	 * Moves the {@code Movable} to a new origin.
	 * 
	 * @param v  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void moveTo(Vector v);
	
	
	/**
	 * Moves the {@code Movable} to a new origin.
	 * 
	 * @param p  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public default void moveTo(Point p)
	{
		moveTo(p.Generator());
	}
	
	/**
	 * Moves the {@code Movable} for a specified distance.
	 * 
	 * @param v  a direction to move in
	 * @param d  a distance to move for
	 * 
	 * 
	 * @see Vector
	 */
	public default void moveFor(Vector v, float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(v.normalize().times(d));
		}
	}
	
	/**
	 * Moves the {@code Movable} for a specified distance.
	 * 
	 * @param v  a direction to move in
	 * 
	 * 
	 * @see Vector
	 */
	public default void moveFor(Vector v)
	{
		moveTo(Origin().plus(v));
	}
}