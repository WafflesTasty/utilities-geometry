package waffles.utils.geom.spatial.types;

import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.geom.spatial.data.unary.Rotated;

/**
 * An {@code Rotatable} object can be rotated in an n-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Rotated
 */
public interface Rotatable extends Rotated
{
	/**
	 * Returns the transform of the {@code Rotatable}.
	 * 
	 * @return  a rotation mutable
	 * 
	 * 
	 * @see Rotated
	 */
	public abstract Rotated.Mutable Transform();
	
	
	/**
	 * Rotates the {@code Rotatable} to a new spin.
	 * 
	 * @param s  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public default void rotateTo(Spin s)
	{
		Transform().setSpin(s);
	}
	
	/**
	 * Rotates the {@code Rotatable} from its spin.
	 * 
	 * @param s  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public default void rotateFor(Spin s)
	{
		rotateTo(s.compose(Spin()));
	}
	
	
	@Override
	public default Spin Spin()
	{
		return Transform().Spin();
	}
}