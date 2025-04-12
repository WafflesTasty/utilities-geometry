package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;

/**
 * A {@code Scaled2D} object defines a two-dimensional size vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.1
 * 
 * 
 * @see Scaled
 */
public interface Scaled2D extends Scaled
{
	/**
	 * A {@code Mutable Scaled2D} can change its own size.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Scaled2D
	 * @see Scaled
	 */
	public static interface Mutable extends Scaled.Mutable, Scaled2D
	{
		/**
		 * Changes the size of the {@code Scaled2D}.
		 * 
		 * @param w  a size width
		 * @param h  a size height
		 */
		public default void setSize(float w, float h)
		{
			setScale(new Vector2(w, h));
		}
	}
	
	
	@Override
	public default Vector2 Scale()
	{
		return new Vector2(Width(), Height());
	}
	
	
	/**
	 * Returns the height of the {@code Scaled2D}.
	 * 
	 * @return  a height factor
	 */
	public default float Height()
	{
		return Scale().Y();
	}
	
	/**
	 * Returns the width of the {@code Scaled2D}.
	 * 
	 * @return  a width factor
	 */
	public default float Width()
	{
		return Scale().X();
	}
}