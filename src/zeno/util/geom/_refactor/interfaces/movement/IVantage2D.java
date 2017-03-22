package zeno.util.geom._refactor.interfaces.movement;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom._refactor.interfaces.rotation.IRotatable2D;

/**
 * The {@code IVantage2D} interface defines an object
 * capable of being moved and rotated in 2D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
 * 
 * @see IRotatable2D
 * @see IMovable2D
 */
public interface IVantage2D extends IMovable2D, IRotatable2D
{
	/**
	 * Strafes the {@code IVantage2D} for a specified distance.
	 * 
	 * @param dist  a distance to strafe
	 */
	public default void strafeFor(float dist)
	{
		if (dist != 0)
		{
			Vector2 rwd = getRight();
			moveFor(rwd.times(dist));
		}
	}
		
	/**
	 * Lifts the {@code IVantage2D} for a specified distance.
	 * 
	 * @param dist  a distance to advance
	 */
	public default void liftFor(float dist)
	{
		if (dist != 0)
		{
			Vector2 uwd = getUp();
			moveFor(uwd.times(dist));
		}
	}
}