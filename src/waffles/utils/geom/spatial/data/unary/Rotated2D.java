package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.spatial.data.spin.Spin2D;

/**
 * A {@code Rotated2D} object defines a two-dimensional rotation spin.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 * 
 * 
 * @see Rotated
 */
@FunctionalInterface
public interface Rotated2D extends Rotated
{
	/**
	 * A {@code Mutable Rotated2D} can change its own spin.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Rotated2D
	 * @see Rotated
	 */
	public static interface Mutable extends Rotated.Mutable, Rotated2D
	{
		/**
		 * Changes the spin of the {@code Rotated2D}.
		 * 
		 * @param a  a spin angle
		 */
		public default void setSpin(float a)
		{
			setSpin(new Spin2D(a));
		}
	}

	
	@Override
	public abstract Spin2D Spin();
	
	
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