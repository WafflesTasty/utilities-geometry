package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.spatial.data.unary.Positioned;
import waffles.utils.geom.utilities.Transformable;
import waffles.utils.geomold.collidable.fixed.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Movable} object can be moved around an n-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Transformable
 * @see Positioned
 */
public interface Movable extends Positioned, Transformable
{	
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
	 * Moves the {@code Movable} to a new origin.
	 * 
	 * @param v  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public default void moveTo(Vector v)
	{
		Transform().setOrigin(v);
	}
	
	
	/**
	 * Moves the {@code Movable} for a given distance.
	 * 
	 * @param v  a distance vector
	 * 
	 * 
	 * @see Vector
	 */
	public default void moveFor(Vector v)
	{
		moveTo(Origin().plus(v));
	}
	
	/**
	 * Moves the {@code Movable} for a given distance.
	 * 
	 * @param v  a direction vector
	 * @param d  a distance value
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
	
	
	@Override
	public abstract Positioned.Mutable Transform();
	
	@Override
	public default Vector Origin()
	{
		return Transform().Origin();
	}
}