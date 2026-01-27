package waffles.utils.geom.spatial.maps.data.unary;

import waffles.utils.geom.spatial.maps.data.spin.Spin;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.tools.patterns.properties.Immutable;

/**
 * A {@code Rotated} object defines a rotation spin.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 * 
 * 
 * @see Dimensional
 * @see Immutable
 */
@FunctionalInterface
public interface Rotated extends Immutable, Dimensional
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
	
	
	@Override
	public default int Dimension()
	{
		return Spin().Dimension();
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