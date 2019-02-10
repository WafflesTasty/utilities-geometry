package zeno.util.geom.transformables.affine;

import zeno.util.tools.Floats;

/**
 * The {@code IVantage2D} interface defines an object
 * capable of moving and rotating in 2D affine space.
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
	/**
	 * Strafes the {@code IVantage2D} for a specified distance.
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
	 * Advances the {@code IVantage2D} for a specified distance.
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
}