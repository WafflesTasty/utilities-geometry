package zeno.util.geom;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom._test.Collisions;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.tools.patterns.properties.Inaccurate;

/**
 * The {@code ICollidable} interface defines an object that can be collided with.
 * It allows spatial queries to be performed through affine spaces.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 */
public interface ICollidable extends Inaccurate<ICollidable>
{	
	/**
	 * Defines the type of the {@code ICollidable} interface.
	 */
	public static ICollidable TYPE = new ICollidable()
	{
		// NOT APPLICABLE
	};

	
	@Override
	public default boolean equals(ICollidable c, int ulps)
	{
		return Collisions.equals(this, c, ulps);
	}

	/**
	 * Intersects the {@code ICollidable} with another object.
	 * 
	 * @param c  an object to check
	 * @return  the object intersection
	 */
	public default ICollidable intersect(ICollidable c)
	{
		return Collisions.intersect(this, c);
	}
	
	/**
	 * Checks if the {@code ICollidable} intersects another object.
	 * 
	 * @param c  an object to check
	 * @return  {@code true} if the object is intersected
	 */
	public default boolean intersects(ICollidable c)
	{
		return Collisions.intersects(this, c);
	}
	
	/**
	 * Checks if the {@code ICollidable} contains another object.
	 * 
	 * @param c  an object to check
	 * @return  {@code true} if the object is contained
	 */
	public default boolean contains(ICollidable c)
	{
		return Collisions.contains(this, c);
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

	/**
	 * Returns the type of the {@code ICollidable}.
	 *  
	 * @return  the collidable type
	 */
	public default ICollidable Type()
	{
		return ICollidable.TYPE;
	}
}