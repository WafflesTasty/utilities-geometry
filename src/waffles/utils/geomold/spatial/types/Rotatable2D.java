package waffles.utils.geom.spatial.types;

import waffles.utils.geom.spatial.data.spin.Spin2D;
import waffles.utils.geom.spatial.data.unary.Rotated2D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Rotatable2D} object can be rotated in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.1
 * 
 * 
 * @see Rotatable
 * @see Rotated2D
 */
public interface Rotatable2D extends Rotatable, Rotated2D
{	
	/**
	 * Rotates the {@code Rotatable2D} for a specified angle.
	 * 
	 * @param a  an angle to rotate for
	 */
	public default void rotateFor(float a)
	{
		if(!Floats.isZero(a, 1))
		{
			rotateFor(new Spin2D(a));
		}
	}
	
	/**
	 * Rotates the {@code Rotatable2D} to a new angle.
	 * 
	 * @param a  an angle to rotate to
	 */
	public default void rotateTo(float a)
	{
		rotateTo(new Spin2D(a));
	}

	
	@Override
	public default Spin2D Spin()
	{
		return (Spin2D) Rotatable.super.Spin();
	}
}