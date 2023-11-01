package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.tools.patterns.semantics.Immutable;

/**
 * A {@code Positioned} object defines an origin vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 * 
 * 
 * @see Immutable
 */
@FunctionalInterface
public interface Positioned extends Immutable
{
	/**
	 * A {@code Mutable Positioned} can change its own origin.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Positioned
	 */
	public static interface Mutable extends Immutable.Mutable, Positioned
	{
		/**
		 * Changes the origin of the {@code Positioned}.
		 * 
		 * @param o  an origin vector
		 * 
		 * 
		 * @see Vector
		 */
		public abstract void setOrigin(Vector o);
	}
	
	
	/**
	 * Returns the origin of the {@code Positioned}.
	 * 
	 * @return  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Origin();
}