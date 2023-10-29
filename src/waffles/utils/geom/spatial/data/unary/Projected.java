package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.Vector;

/**
 * A {@code Projected} object defines a projection oculus.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 */
@FunctionalInterface
public interface Projected
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
	public static interface Mutable extends Projected
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
		
		@Override
		public default Mutable Mutator()
		{
			return this;
		}
	}
	
	
	/**
	 * Returns a mutable {@code Projected}.
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
	 * Returns the oculus of the {@code Projected}.
	 * 
	 * @return  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Oculus();
}