package zeno.util.geom;

import zeno.util.algebra.linear.vector.fixed.Vector2;

/**
 * The {@code ICollideable2D} interface defines an object that can be collided with.
 * It allows spatial queries to be performed in two dimensions through points and lines.
 * 
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see ICollidable
 */
public interface ICollideable2D extends ICollidable
{
	/**
	 * Indicates if the {@code ICollideable2D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return {@code true} if the point is contained
	 */
	public default boolean contains(float x, float y)
	{
		return contains(new Vector2(x, y));
	}
}