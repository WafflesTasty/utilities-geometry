package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.spatial.data.unary.Positioned2D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Movable2D} object can be moved around a two-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
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
		if(!Floats.isZero(x, 1)
		|| !Floats.isZero(y, 1))
		{
			moveFor(new Vector2(x, y));
		}
	}

	/**
	 * Moves the {@code Movable2D} to a new origin.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 */
	public default void moveTo(float x, float y)
	{
		moveTo(new Vector2(x, y));
	}

	
	@Override
	public default Vector2 Origin()
	{
		return (Vector2) Movable.super.Origin();
	}
}