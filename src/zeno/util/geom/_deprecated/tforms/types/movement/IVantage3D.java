package zeno.util.geom._deprecated.tforms.types.movement;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom._deprecated.tforms.types.ITransformation3D;
import zeno.util.geom._deprecated.tforms.types.rotation.IRotatable3D;

/**
 * The {@code IVantage3D} interface	defines an object
 * capable of being moved and rotated in 3D space.
 *
 * @since Apr 21, 2016
 * @author Zeno
 * 
 * @see IRotatable3D
 * @see IMovable3D
 */
public interface IVantage3D extends IMovable3D, IRotatable3D
{	
	@Override
	public abstract ITransformation3D Transform();
	
	/**
	 * Strafes the {@code IVantage3D} for a distance.
	 * 
	 * @param dist  a distance to strafe
	 */
	public default void strafeFor(float dist)
	{
		if(dist != 0)
		{
			Vector3 rwd = Basis().right();
			moveFor(rwd.times(dist));
		}
	}
		
	/**
	 * Advances the {@code IVantage3D} for a distance.
	 * 
	 * @param dist  a distance to advance
	 */
	public default void advanceFor(float dist)
	{
		if(dist != 0)
		{
			Vector3 fwd = Basis().forward();
			moveFor(fwd.times(dist));
		}
	}

	/**
	 * Lifts the {@code IVantage3D} for a distance.
	 * 
	 * @param dist  a distance to lift
	 */
	public default void liftFor(float dist)
	{
		if(dist != 0)
		{
			Vector3 fwd = Basis().up();
			moveFor(fwd.times(dist));
		}
	}
}