package waffles.utils.geom;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.response.fixed.RSPFlip;

/**
 * A {@code Collidable} object defines a {@code #Collisions()} property
 * which allows containment and intersection tests to be executed.
 * Each test generates a {@code Response} object containing
 * all the necessary collision information.
 * 
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.0
 */
public interface Collidable
{	
	/**
	 * Returns collisions of the {@code Collidable}.
	 * 
	 * @return  a collision operator
	 * 
	 * 
	 * @see Collision
	 */
	public abstract Collision Collisions();
	
	
	/**
	 * Checks containment of a vector in the {@code Collidable}.
	 * 
	 * @param v  a point vector
	 * @return   a collision response
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
	 * @param c  a collidable object
	 * @return   a collision response
	 * 
	 * 
	 * @see Collidable
	 * @see Response
	 */
	public default Response contain(Collidable c)
	{
		Response rsp1 =   Collisions().contain(c);
		Response rsp2 = c.Collisions().inhabit(this);
		
		rsp2 = new RSPFlip(rsp2);
		if(rsp1.Cost() < rsp2.Cost())
			return rsp1;
		return rsp2;
	}
	
	/**
	 * Checks intersection of an object in the {@code Collidable}.
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
		Response rsp1 =   Collisions().intersect(c);
		Response rsp2 = c.Collisions().intersect(this);

		rsp2 = new RSPFlip(rsp2);
		if(rsp1.Cost() < rsp2.Cost())
			return rsp1;
		return rsp2;
	}
	
	
	/**
	 * Checks intersection of an object in the {@code Collidable}.
	 * 
	 * @param c  a collidable object
	 * @return  {@code true} if intersection happens
	 */
	public default boolean intersects(Collidable c)
	{
		return intersect(c).hasImpact();
	}
	
	/**
	 * Checks containment of an object in the {@code Collidable}.
	 * 
	 * @param c  a collidable object
	 * @return  {@code true} if containment happens
	 */
	public default boolean contains(Collidable c)
	{
		return contain(c).hasImpact();
	}
	
	/**
	 * Checks containment of a vector in the {@code Collidable}.
	 * 
	 * @param v  a point vector
	 * @return  {@code true} if containment happens
	 * 
	 * 
	 * @see Vector
	 */
	public default boolean contains(Vector v)
	{
		return contain(v).hasImpact();
	}
	
	/**
	 * Returns the dimension of the {@code Collidable}.
	 * This is equal to the dimension of the containing space.
	 * 
	 * @return  a space dimension
	 */
	public abstract int Dimension();
}