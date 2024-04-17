package waffles.utils.geom;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Collision} object defines collision operations for a {@code Collidable}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 */
public interface Collision
{	
	/**
	 * The {@code Response} interface defines the result of a collision check.
	 *
	 * @author Waffles
	 * @since 11 May 2021
	 * @version 1.0
	 */
	@FunctionalInterface
	public static interface Response
	{
		/**
		 * Checks if the {@code Response} has made an impact.
		 * 
		 * @return  {@code true} if impact has happened
		 */
		public abstract boolean hasImpact();
		
		
		/**
		 * Returns a point of collision contact.
		 * 
		 * @return  a contact point
		 * 
		 * 
		 * @see Point
		 */
		public default Point Contact()
		{
			return null;
		}
		
		/**
		 * Returns the minimum collision distance.
		 * If the intersection has no impact, this vector defines the
		 * smallest translation of the source to intersect it. If the
		 * intersection has impact, this vector should be null.
		 * 
		 * @return  a distance vector
		 * 
		 * 
		 * @see Vector
		 */
		public default Vector Distance()
		{
			return null;
		}
		
		/**
		 * Returns the minimum penetration {@code Vector}.
		 * If the intersection has impact, this vector defines
		 * the smallest translation of the source to clear it. If the
		 * intersection has no impact, this vector should be null.
		 * 
		 * @return  a penetration vector
		 * 
		 * 
		 * @see Vector
		 */
		public default Vector Penetration()
		{
			return null;
		}
				
		/**
		 * Returns the {@code Response} intersection shape.
		 * 
		 * @return  an intersection shape
		 * 
		 * 
		 * @see Collidable
		 */
		public default Collidable Shape()
		{
			return Geometries.Void(0);
		}
		
		/**
		 * Returns the cost of the {@code Response}.
		 * 
		 * @return  a computation cost
		 */
		public default int Cost()
		{
			return Integers.MAX_VALUE;
		}
	}

				
	/**
	 * Computes a containment response with a {@code Collidable}.
	 * 
	 * @param c  a collidable object
	 * @return   a collision response
	 * 
	 * 
	 * @see Collidable
	 * @see Response
	 */
	public default Response contain(Collidable c)
	{
		return () -> false;
	}

	/**
	 * Computes an intersection response with a {@code Collidable}.
	 * 
	 * @param c  a collidable object
	 * @return   a collision response
	 * 
	 * 
	 * @see Collidable
	 * @see Response
	 */
	public default Response intersect(Collidable c)
	{
		return () -> false;
	}
	
	/**
	 * Computes a habitation response with a {@code Collidable}.
	 * 
	 * @param c  a collidable object
	 * @return   a collision response
	 * 
	 * 
	 * @see Collidable
	 * @see Response
	 */
	public default Response inhabit(Collidable c)
	{
		return () -> false;
	}

	/**
	 * Returns the source of the {@code Collision}.
	 * 
	 * @return  a source collidable
	 * 
	 * 
	 * @see Collidable
	 */
	public abstract Collidable Source();
}