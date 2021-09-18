package zeno.util.geom.collidables;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.Point;

/**
 * The {@code ICollision} interface defines collision for a {@code ICollidable} subtype.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 */
public interface ICollision
{	
	/**
	 * The {@code Response} interface defines the result of a collision check.
	 *
	 * @author Waffles
	 * @since 11 May 2021
	 * @version 1.0
	 */
	public static interface Response
	{
		/**
		 * Checks if the shape intersection is empty.
		 * 
		 * @return  {@code true} if intersection is empty
		 */
		public abstract boolean isEmpty();
		
		/**
		 * Returns the shape of the intersection.
		 * 
		 * @return  an intersection shape
		 * 
		 * 
		 * @see ICollidable
		 */
		public abstract ICollidable Shape();
		
		/**
		 * Returns the minimum penetration vector.
		 * If the intersection is non-empty, this vector defines
		 * the smallest translation of the source to clear it. If the
		 * intersection is empty, this vector should be null.
		 * 
		 * @return  a penetration vector
		 * 
		 * 
		 * @see Vector
		 */
		public abstract Vector Penetration();
		
		/**
		 * Returns the minimum collision distance.
		 * If the intersection is empty, this vector defines the
		 * smallest translation of the source to intersect it. If the
		 * intersection is non-empty, this vector should be null.
		 * 
		 * @return  a distance vector
		 * 
		 * 
		 * @see Vector
		 */
		public abstract Vector Distance();
	}
	
		
	/**
	 * Checks if the source contains a {@code ICollidable}.
	 * 
	 * @param c  a collidable object to check
	 * @return  {@code true} if the object is contained
	 * 
	 * 
	 * @see ICollidable
	 */
	public default Boolean contains(ICollidable c)
	{
		return null;
	}
	
	/**
	 * Checks if the source inhabits a {@code ICollidable}.
	 * 
	 * @param c  a collidable object to check
	 * @return  {@code true} if the object is inhabited
	 * 
	 * 
	 * @see ICollidable
	 */
	public default Boolean inhabits(ICollidable c)
	{
		return null;
	}
	
	
	/**
	 * Computes the collision response with a {@code Point}.
	 * 
	 * @param p  a target point
	 * @return  a collision response
	 * 
	 * 
	 * @see Response
	 * @see Point
	 */
	public default Response contain(Point p)
	{
		return null;
	}
	
	/**
	 * Computes the intersection with a {@code ICollidable}.
	 * 
	 * @param c  a collidable object to intersect
	 * @return  an object intersection
	 * 
	 * 
	 * @see Response
	 */
	public default Response intersect(ICollidable c)
	{
		return null;
	}
	
	/**
	 * Checks if the source equals another {@code ICollidable}.
	 * 
	 * @param c  a collidable object to check
	 * @param ulps  an error margin to use
	 * @return  {@code true} if the objects are equal
	 * 
	 * 
	 * @see ICollidable
	 */
	public default Boolean equals(ICollidable c, int ulps)
	{
		return null;
	}
	
	/**
	 * Checks if the source intersects a {@code ICollidable}.
	 * 
	 * @param c  a collidable object to check
	 * @return  {@code true} if the object is intersected
	 * 
	 * 
	 * @see ICollidable
	 */
	public default Boolean intersects(ICollidable c)
	{
		Response r = intersect(c);
		if(r != null)
		{
			return !r.isEmpty();
		}
		
		return null;
	}
}