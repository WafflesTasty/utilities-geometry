package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.Vector;

/**
 * A {@code Scaled} object defines a size vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 */
@FunctionalInterface
public interface Scaled
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
	public static interface Mutable extends Scaled
	{
		/**
		 * Changes the size of the {@code Scaled}.
		 * 
		 * @param s  a size vector
		 * 
		 * 
		 * @see Vector
		 */
		public abstract void setSize(Vector s);
		
		@Override
		public default Mutable Mutator()
		{
			return this;
		}
	}
	
	
	/**
	 * Returns a mutable {@code Scaled}.
	 * If the object is mutable, it will be returned
	 * as mutable. Otherwise this returns null.
	 * 
	 * @return  a mutable object
	 * 
	 * 
	 * @see Mutable
	 */
	public default Mutable Mutator()
	{
		if(this instanceof Mutable)
		{
			return (Mutable) this;
		}
		
		return null;
	}
	
	/**
	 * Returns the size of the {@code Scaled}.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Size();
}