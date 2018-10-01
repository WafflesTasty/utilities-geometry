package zeno.util.geom.transformables.movement;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.transformables.rotation.IRotatable3D;
import zeno.util.geom.transformations.ITransformation3D;

/**
 * The {@code IVantage3D} interface	defines an object
 * capable of being moved and rotated in 3D space.
 * 
 * @author Zeno
 * @since Apr 21, 2016
 * @version 1.0
 * 
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
			Vector3 rwd = Transform().Right();
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
			Vector3 fwd = Transform().Forward();
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
			Vector3 fwd = Transform().Up();
			moveFor(fwd.times(dist));
		}
	}
}