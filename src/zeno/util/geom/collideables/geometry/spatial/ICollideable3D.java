package zeno.util.geom.collideables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ICollideable;

/**
 * The {@code ICollideable3D} interface defines an object that can be collided with.
 * It allows spatial queries to be performed in three dimensions through points and lines.
 * 
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * @see ICollideable
 */
public interface ICollideable3D extends ICollideable
{
	/**
	 * Indicates if the {@code ICollideable3D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 * @return {@code true} if the point is contained
	 */
	public default boolean contains(float x, float y, float z)
	{
		return contains(new Vector3(x, y, z));
	}
}