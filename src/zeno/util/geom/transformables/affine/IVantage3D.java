package zeno.util.geom.transformables.affine;

import zeno.util.tools.Floats;

/**
 * The {@code IVantage3D} interface	defines an object
 * capable of moving and rotating in 3D affine space.
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
	/**
	 * Strafes the {@code IVantage3D} for a distance.
	 * 
	 * @param d  a distance to strafe
	 */
	public default void strafeFor(float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Right(), d);
		}
	}
		
	/**
	 * Advances the {@code IVantage3D} for a distance.
	 * 
	 * @param d  a distance to advance
	 */
	public default void advanceFor(float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Forward(), d);
		}
	}

	/**
	 * Lifts the {@code IVantage3D} for a distance.
	 * 
	 * @param d  a distance to lift
	 */
	public default void liftFor(float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Up(), d);
		}
	}
}