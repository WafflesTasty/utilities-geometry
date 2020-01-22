package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.utilities.spin.Spin2D;
import zeno.util.tools.Floats;

/**
 * The {@code IRotatable2D} interface defines an object
 * capable of being rotated in a 2D affine space.
 *
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IRotatable
 */
public interface IRotatable2D extends IRotatable
{
	/**
	 * Rotates the {@code IRotatable2D} for a specified angle.
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
	 * Rotates the {@code IRotatable2D} to a new angle.
	 * 
	 * @param a  an angle to rotate to
	 */
	public default void rotateTo(float a)
	{
		rotateTo(new Spin2D(a));
	}

	
	@Override
	public abstract Spin2D Spin();

	/**
	 * Returns the forward vector of the {@code IRotatable2D}.
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
	 * Returns the right vector of the {@code IRotatable2D}.
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