package waffles.utils.geom.shapes.collision;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.tools.patterns.properties.counters.Taxed;
import waffles.utils.tools.patterns.properties.values.Sourced;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Collision} defines collision checks for a {@code Collidable}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 *
 * 
 * @see Algorithmic
 * @see Collidable
 * @see Sourced
 */
public class Collision implements Algorithmic, Sourced<Collidable>
{
	/**
	 * A {@code Response} defines the result of a collision check.
	 *
	 * @author Waffles
	 * @since 11 May 2021
	 * @version 1.0
	 *
	 *
	 * @see Taxed
	 * @see Dimensional
	 * @see Collidable
	 * @see Sourced
	 */
	@FunctionalInterface
	public static interface Response extends Taxed, Dimensional, Sourced<Collidable>
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
		 * If the {@code Response} has no impact, this vector defines the
		 * smallest translation of the source to achieve it. If the
		 * {@code Response} has impact, this vector is null.
		 *
		 * @return  a distance vector
		 *
		 *
		 * @see Vector
		 */
		public default Vector Distance()
		{
			return Vectors.create(Dimension());
		}

		/**
		 * Returns the minimum collision penetration {@code Vector}.
		 * If the {@code Response} has impact, this vector defines the
		 * smallest translation of the source to remove it. If the
		 * {@code Response} has no impact, this vector is null.
		 *
		 * @return  a penetration vector
		 *
		 *
		 * @see Vector
		 */
		public default Vector Penetration()
		{
			return Vectors.create(Dimension());
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
			return new Void(Dimension());
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

	
	private double err;
	private Collidable src;

	/**
	 * Creates a new {@code Collision}.
	 * 
	 * @param s  a collidable source
	 * 
	 * 
	 * @see Collidable
	 */
	public Collision(Collidable s)
	{
		this(s, Doubles.pow(2, -8));
	}
	
	/**
	 * Creates a new {@code Collision}.
	 * 
	 * @param s  a collidable source
	 * @param e  an arror margin
	 * 
	 * 
	 * @see Collidable
	 */
	public Collision(Collidable s, double e)
	{
		src = s;
		err = e;
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
	public Response contain(Collidable c)
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
	public Response intersect(Collidable c)
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
	public Response inhabit(Collidable c)
	{
		return () -> Source();
	}
	
	
	@Override
	public Collidable Source()
	{
		return src;
	}
	
	@Override
	public double Error()
	{
		return err;
	}
}