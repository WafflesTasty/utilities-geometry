package waffles.utils.geom.spatial.types;

import waffles.utils.geom.spatial.data.spin.Spin;

/**
 * An {@code Rotated} object can be rotated in a vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface Rotatable
{
	/**
	 * Returns the spin of the {@code Rotated}.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract Spin Spin();
	
	/**
	 * Rotates the {@code Rotated} to a new spin.
	 * 
	 * @param s  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract void rotateTo(Spin s);
	
	/**
	 * Rotates the {@code Rotated} from its spin.
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
}