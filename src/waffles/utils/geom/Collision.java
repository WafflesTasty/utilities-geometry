package waffles.utils.geom;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.geomold.collidable.fixed.Point;
import waffles.utils.geomold.utilities.Geometries;
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
	public static interface Response extends Dimensional
	{
		/**
		 * Checks if the {@code Response} has made an impact.
		 * 
		 * @return  {@code true} if impact has happened
		 */
		public default boolean hasImpact()
		{
			return false;
		}

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
			if(!hasImpact())
			{
				int dim = Dimension();
				return Vectors.create(dim);
			}
			
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
			if(hasImpact())
			{
				int dim = Dimension();
				return Vectors.create(dim);
			}
			
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
			int dim = Dimension();
			return Geometries.Void(dim);
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
		return () -> Source().Dimension();
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
		return () -> Source().Dimension();
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
		return () -> Source().Dimension();
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