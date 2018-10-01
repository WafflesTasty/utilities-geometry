package zeno.util.geom._deprecated.tforms.types.movement;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom._deprecated.tforms.types.ITransformation2D;
import zeno.util.geom._deprecated.tforms.types.rotation.IRotatable2D;

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
			Vector2 rwd = Basis().right();
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
			Vector2 uwd = Basis().up();
			moveFor(uwd.times(dist));
		}
	}
}