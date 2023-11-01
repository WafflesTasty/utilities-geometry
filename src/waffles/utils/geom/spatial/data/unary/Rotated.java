package waffles.utils.geom.spatial.data.unary;

import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.tools.patterns.semantics.Immutable;

/**
 * A {@code Rotated} object defines a rotation spin.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 * 
 * 
 * @see Immutable
 */
@FunctionalInterface
public interface Rotated extends Immutable
{
	/**
	 * A {@code Mutable Rotated} can change its own spin.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Rotated
	 */
	public static interface Mutable extends Immutable.Mutable, Rotated
	{
		/**
		 * Changes the spin of the {@code Rotated}.
		 * 
		 * @param s  a rotation spin
		 * 
		 * 
		 * @see Spin
		 */
		public abstract void setSpin(Spin s);
	}
	
	
	/**
	 * Returns the spin of the {@code Rotated}.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract Spin Spin();
}