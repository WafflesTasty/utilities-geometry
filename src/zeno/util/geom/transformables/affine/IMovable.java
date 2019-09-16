package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.tools.Floats;

/**
 * The {@code IMovable} interface defines an object
 * capable of being moved through an affine space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface IMovable
{
	/**
	 * Returns the origin of the {@code IMovable}.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public abstract Vector Origin();
		
	/**
	 * Moves the {@code IMovable} to a new origin vector.
	 * 
	 * @param v  a new origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void moveTo(Vector v);
	
	/**
	 * Moves the {@code IMovable} for a specified distance.
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
	 * Moves the {@code IMovable} for a specified distance.
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