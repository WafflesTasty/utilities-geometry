package waffles.utils.geom;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.collide.response.RSPFlipped;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Dimensional;

/**
 * A {@code Collidable} object defines a {@code Collision} property
 * which allows containment and intersection checks to be executed.
 * Each checks generates a {@code Response} object containing
 * all the necessary collision information.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see Dimensional
 */
public interface Collidable extends Dimensional
{
	/**
	 * Returns the collision of the {@code Collidable}.
	 *
	 * @return  a collision
	 *
	 *
	 * @see Collision
	 */
	public abstract Collision Collision();


	/**
	 * Checks containment of a point in the {@code Collidable}.
	 *
	 * @param v  a vector
	 * @return   a response
	 *
	 *
	 * @see Response
	 * @see Vector
	 */
	public default Response contain(Vector v)
	{
		return contain(new Point(v, 1f));
	}

	/**
	 * Checks containment of an object in the {@code Collidable}.
	 *
	 * @param c  a collidable
	 * @return   a response
	 *
	 *
	 * @see Response
	 */
	public default Response contain(Collidable c)
	{
		Response rsp1 =   Collision().contain(c);
		Response rsp2 = c.Collision().inhabit(this);

		rsp2 = new RSPFlipped(rsp2);
		if(rsp1.cost() < rsp2.cost())
		{
			return rsp1;
		}
		return rsp2;
	}

	/**
	 * Checks intersection of an object in the {@code Collidable}.
	 *
	 * @param c  a collidable
	 * @return   a response
	 *
	 *
	 * @see Response
	 */
	public default Response intersect(Collidable c)
	{
		Response rsp1 =   Collision().intersect(c);
		Response rsp2 = c.Collision().intersect(this);

		rsp2 = new RSPFlipped(rsp2);
		if(rsp1.cost() <= rsp2.cost())
		{
			return rsp1;
		}
		return rsp2;
	}


	/**
	 * Checks intersection of an object in the {@code Collidable}.
	 *
	 * @param c  a collidable
	 * @return  {@code true} if intersection occurs
	 */
	public default boolean intersects(Collidable c)
	{
		return intersect(c).hasImpact();
	}

	/**
	 * Checks containment of an object in the {@code Collidable}.
	 *
	 * @param c  a collidable
	 * @return  {@code true} if containment occurs
	 */
	public default boolean contains(Collidable c)
	{
		return contain(c).hasImpact();
	}

	/**
	 * Checks containment of a vector in the {@code Collidable}.
	 *
	 * @param v  a vector
	 * @return  {@code true} if containment occurs
	 *
	 *
	 * @see Vector
	 */
	public default boolean contains(Vector v)
	{
		return contain(v).hasImpact();
	}
}