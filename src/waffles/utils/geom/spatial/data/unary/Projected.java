package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.tools.patterns.semantics.Immutable;

/**
 * A {@code Projected} object defines a projection oculus.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 * 
 * 
 * @see Immutable
 */
@FunctionalInterface
public interface Projected extends Immutable
{
	/**
	 * A {@code Mutable Projected} can change its own oculus.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Projected
	 */
	public static interface Mutable extends Immutable.Mutable, Projected
	{
		/**
		 * Changes the oculus of the {@code Projected}.
		 * 
		 * @param o  an oculus vector
		 * 
		 * 
		 * @see Vector
		 */
		public abstract void setOculus(Vector o);
	}
	
	
	/**
	 * Returns the oculus of the {@code Projected}.
	 * 
	 * @return  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Oculus();
}