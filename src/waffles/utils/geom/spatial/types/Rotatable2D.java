package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.spatial.data.spin.Spin2D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Rotatable2D} object can be rotated in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Rotatable
 */
public interface Rotatable2D extends Rotatable
{
	@Override
	public default Spin2D Spin()
	{
		return (Spin2D) Rotatable.super.Spin();
	}
	
	
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

	
	/**
	 * Returns a forward {@code Vector2}.
	 * 
	 * @return  a forward vector
	 * 
	 * 
	 * @see Vector2
	 */
	public default Vector2 Forward()
	{
		return Spin().Forward();
	}
	
	/**
	 * Returns a right {@code Vector2}.
	 * 
	 * @return  a right vector
	 * 
	 * 
	 * @see Vector2
	 */
	public default Vector2 Right()
	{
		return Spin().Right();
	}
}