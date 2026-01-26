package waffles.utils.geom.spatial.owners;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Positioned2D;

/**
 * An {@code Movable2D} object can be moved around a two-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.1
 *
 *
 * @see Positioned2D
 * @see Movable
 */
public interface Movable2D extends Movable, Positioned2D
{
	/**
	 * Moves the {@code Movable2D} for a specified distance.
	 *
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 */
	public default void moveFor(float x, float y)
	{
		moveFor(new Point(x, y, 1f));
	}

	/**
	 * Moves the {@code Movable2D} to a new origin.
	 *
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 */
	public default void moveTo(float x, float y)
	{
		moveTo(new Point(x, y, 1f));
	}


	@Override
	public default Point Origin()
	{
		return Movable.super.Origin();
	}
}