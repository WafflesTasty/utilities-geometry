package waffles.utils.geom.spatial.owners;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Positioned3D;

/**
 * An {@code Movable3D} object can be moved around a three-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 21, 2016
 * @version 1.1
 * 
 * 
 * @see Positioned3D
 * @see Movable
 */
public interface Movable3D extends Movable, Positioned3D
{		
	/**
	 * Moves the {@code Movable3D} for a specified distance.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 * @param z  an z-coordinate
	 */
	public default void moveFor(float x, float y, float z)
	{
		moveFor(new Point(x, y, z, 1f));
	}
	
	/**
	 * Moves the {@code Movable3D} to a new origin.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 * @param z  an z-coordinate
	 */
	public default void moveTo(float x, float y, float z)
	{
		moveTo(new Point(x, y, z, 1f));
	}
	
	
	@Override
	public default Point Origin()
	{
		return Movable.super.Origin();
	}
}