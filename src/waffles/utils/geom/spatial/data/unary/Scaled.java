package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.tools.patterns.semantics.Immutable;

/**
 * A {@code Scaled} object defines a size vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 * 
 * 
 * @see Immutable
 */
public interface Scaled extends Immutable
{
	/**
	 * A {@code Mutable Scaled} can change its own size.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Scaled
	 */
	public static interface Mutable extends Immutable.Mutable, Scaled
	{
		/**
		 * Changes the size of the {@code Scaled}.
		 * 
		 * @param s  a size vector
		 * 
		 * 
		 * @see Vector
		 */
		public abstract void setScale(Vector s);
	}
	
	
	/**
	 * Returns the size of the {@code Scaled}.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Scale();
}