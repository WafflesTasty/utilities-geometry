package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.spatial.data.unary.Positioned3D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Movable3D} object can be moved around a three-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 21, 2016
 * @version 1.0
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
		if(!Floats.isZero(x, 1)
		|| !Floats.isZero(y, 1)
		|| !Floats.isZero(z, 1))
		{
			moveFor(new Vector3(x, y, z));
		}
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
		moveTo(new Vector3(x, y, z));
	}
	
	
	@Override
	public default Vector3 Origin()
	{
		return (Vector3) Movable.super.Origin();
	}
}