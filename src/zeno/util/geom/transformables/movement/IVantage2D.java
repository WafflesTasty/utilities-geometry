package zeno.util.geom.transformables.movement;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.transformables.rotation.IRotatable2D;
import zeno.util.geom.transformations.ITransformation2D;

/**
 * The {@code IVantage2D} interface defines an object
 * capable of being moved and rotated in 2D space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IRotatable2D
 * @see IMovable2D
 */
public interface IVantage2D extends IMovable2D, IRotatable2D
{
	@Override
	public abstract ITransformation2D Transform();
	
	
	/**
	 * Strafes the {@code IVantage2D} for a specified distance.
	 * 
	 * @param dist  a distance to strafe
	 */
	public default void strafeFor(float dist)
	{
		if (dist != 0)
		{
			Vector2 rwd = Transform().Right();
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
			Vector2 uwd = Transform().Up();
			moveFor(uwd.times(dist));
		}
	}
}