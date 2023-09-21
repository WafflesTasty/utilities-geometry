package waffles.utils.geom.spaces;

import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code Space3D} is a set of three-dimensional objects capable of handling spatial queries.
 *
 * @author Waffles
 * @since 22 Jul 2020
 * @version 1.0
 *
 *
 * @param <O>  an object type
 * @see Space
 */
public interface Space3D<O> extends Space<O>
{
	/**
	 * Queries the {@code Space3D} at a given point.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 * @param z  an z-coordinate
	 * @return   an object set
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<O> query(float x, float y, float z)
	{
		return query(new Point(x, y, 1f));
	}
}