package waffles.utils.geom;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;

/**
 * A {@code Collideable2D} object is a {@code Collidable} in two-dimensional space.
 * 
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see Collidable
 */
public interface Collideable2D extends Collidable
{
	/**
	 * Checks containment of a vector in the {@code Collideable2D}.
	 * 
	 * @param x  a vector x-coordinate
	 * @param y  a vector y-coordinate
	 * @return   a collision response
	 */
	public default boolean contains(float x, float y)
	{
		return contains(new Vector2(x, y));
	}
}