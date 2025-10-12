package waffles.utils.geom;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.geomold.collidable.fixed.Point;
import waffles.utils.geomold.utilities.Geometries;
import waffles.utils.tools.patterns.properties.counters.Accountable;
import waffles.utils.tools.patterns.properties.values.Sourced;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Collision} defines collision checks for a {@code Collidable}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see Collidable
 * @see Sourced
 */
@FunctionalInterface
public interface Collision extends Sourced<Collidable>
{	
	/**
	 * A {@code Response} defines the result of a collision check.
	 *
	 * @author Waffles
	 * @since 11 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see Accountable
	 * @see Dimensional
	 * @see Collidable
	 * @see Sourced
	 */
	@FunctionalInterface
	public static interface Response extends Accountable, Dimensional, Sourced<Collidable>
	{
		/**
		 * Returns a collision contact {@code Point}.
		 * This point describes the epicenter of a collision.
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
		 * Returns the minimum collision distance {@code Vector}.
		 * If the intersection has no impact, this vector defines the
		 * smallest translation of the source to intersect it. If the
		 * intersection has impact, this vector is null.
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
		 * Returns the minimum collision penetration {@code Vector}.
		 * If the intersection has impact, this vector defines the
		 * smallest translation of the source to remove it. If the
		 * intersection has no impact, this vector is null.
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
		 * Checks if the {@code Response} has made an impact.
		 * 
		 * @return  {@code true} if impact happened
		 */
		public default boolean hasImpact()
		{
			return false;
		}
		
		/**
		 * Returns a {@code Collidable} collision shape.
		 * 
		 * @return  a collision shape
		 * 
		 * 
		 * @see Collidable
		 */
		public default Collidable Shape()
		{
			int dim = Dimension();
			return Geometries.Void(dim);
		}
		

		@Override
		public default int Dimension()
		{
			return Source().Dimension();
		}
		
		@Override
		public default int cost()
		{
			return Integers.MAX_VALUE;
		}
	}

				
	/**
	 * Computes a containment response with a {@code Collidable}.
	 * 
	 * @param c  a collidable
	 * @return   a response
	 * 
	 * 
	 * @see Collidable
	 * @see Response
	 */
	public default Response contain(Collidable c)
	{
		return () -> Source();
	}

	/**
	 * Computes an intersection response with a {@code Collidable}.
	 * 
	 * @param c  a collidable
	 * @return   a response
	 * 
	 * 
	 * @see Response
	 */
	public default Response intersect(Collidable c)
	{
		return () -> Source();
	}
	
	/**
	 * Computes a habitation response with a {@code Collidable}.
	 * 
	 * @param c  a collidable
	 * @return   a response
	 * 
	 * 
	 * @see Response
	 */
	public default Response inhabit(Collidable c)
	{
		return () -> Source();
	}
}