package waffles.utils.geom;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;

/**
 * A {@code Collideable3D} object is a {@code Collidable} in three-dimensional space.
 * 
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see Collidable
 */
public interface Collideable3D extends Collidable
{
	/**
	 * Checks containment of a vector in the {@code Collideable2D}.
	 * 
	 * @param x  a vector x-coordinate
	 * @param y  a vector y-coordinate
	 * @param z  a vector z-coordinate
	 * @return   a collision response
	 */
	public default boolean contains(float x, float y, float z)
	{
		return contains(new Vector3(x, y, z));
	}
}