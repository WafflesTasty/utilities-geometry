package waffles.utils.geom.spatial.data.unary;

import waffles.utils.geom.spatial.data.spin.Spin;

/**
 * A {@code Rotated} object defines a rotation spin.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 */
@FunctionalInterface
public interface Rotated
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
	public static interface Mutable extends Rotated
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
		
		@Override
		public default Mutable Mutator()
		{
			return this;
		}
	}
	
	
	/**
	 * Returns a mutable {@code Rotated}.
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
	 * Returns the spin of the {@code Rotated}.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract Spin Spin();
}