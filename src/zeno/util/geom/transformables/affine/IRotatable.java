package zeno.util.geom.transformables.affine;

import zeno.util.geom.utilities.spin.Spin;

/**
 * The {@code IRotatable} interface defines an object
 * capable of being rotated in an affine space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface IRotatable
{
	/**
	 * Returns the spin of the {@code IRotatable}.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract Spin Spin();
	
	/**
	 * Rotates the {@code IRotatable} to a new spin.
	 * 
	 * @param s  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract void rotateTo(Spin s);
	
	/**
	 * Rotates the {@code IRotatable} from its spin.
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