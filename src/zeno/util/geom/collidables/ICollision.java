package zeno.util.geom.collidables;

import zeno.util.geom.ICollidable;

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
	 * Computes the intersection with a {@code ICollidable}.
	 * 
	 * @param c  a collidable object to intersect
	 * @return  an object intersection
	 * 
	 * 
	 * @see ICollidable
	 */
	public default ICollidable intersect(ICollidable c)
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
		ICollidable isect = intersect(c);
		if(isect != null)
		{
			return !isect.equals(ICollidable.EMPTY);
		}
		
		return null;
	}
}