package waffles.utils.geom.utilities;

import waffles.utils.tools.patterns.properties.Immutable;

/**
 * An {@code Axled} object defines an {@code Axis}.
 *
 * @author Waffles
 * @since Jul 16, 2026
 * @version 1.1
 *
 * 
 * @see Immutable
 */
public interface Axled extends Immutable
{
	/**
	 * An {@code Axled.Mutable} can change its own {@code Axis}.
	 *
	 * @author Waffles
	 * @since Jul 16, 2026
	 * @version 1.1
	 *
	 * 
	 * @see Immutable
	 * @see Axled
	 */
	public interface Mutable extends Axled, Immutable.Mutable
	{
		/**
		 * Changes the axis of the {@code Axled}.
		 * 
		 * @param a  a standard axis
		 * 
		 * 
		 * @see Axis
		 */
		public abstract void setAxis(Axis a);
	}
	
	/**
	 * Returns the axis of the {@code Axled}.
	 * 
	 * @return  a standard axis
	 * 
	 * 
	 * @see Axis
	 */
	public abstract Axis Axis();
}