package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.Vector;

/**
 * A {@code Positioned} object defines an origin vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 */
@FunctionalInterface
public interface Positioned
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
	public static interface Mutable extends Positioned
	{
		/**
		 * Changes the origin of the {@code Positioned}.
		 * 
		 * @param o  an origin Vector
		 * 
		 * 
		 * @see Vector
		 */
		public abstract void setOrigin(Vector o);
		
		@Override
		public default Mutable Mutator()
		{
			return this;
		}
	}
	
	
	/**
	 * Returns a mutable {@code Positioned}.
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
	 * Returns the origin of the {@code Positioned}.
	 * 
	 * @return  an origin Vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Origin();
}