package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Movable3D} object can be moved around a three-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 21, 2016
 * @version 1.0
 * 
 * 
 * @see Movable
 */
public interface Movable3D extends Movable
{	
	@Override
	public default Vector3 Origin()
	{
		return (Vector3) Movable.super.Origin();
	}
	
	
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
	
	
	/**
	 * Returns the x-coordinate of the {@code Movable3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public default float X()
	{
		return Origin().X();
	}

	/**
	 * Returns the y-coordinate of the {@code Movable3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public default float Y()
	{
		return Origin().Y();
	}
	
	/**
	 * Returns the z-coordinate of the {@code Movable3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public default float Z()
	{
		return Origin().Z();
	}
}