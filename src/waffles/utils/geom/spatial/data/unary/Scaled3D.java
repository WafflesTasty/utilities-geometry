package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;

/**
 * A {@code Scaled3D} object defines a three-dimensional size vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.1
 * 
 * 
 * @see Scaled
 */
public interface Scaled3D extends Scaled
{
	/**
	 * A {@code Mutable Scaled3D} can change its own size.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Scaled3D
	 * @see Scaled
	 */
	public static interface Mutable extends Scaled.Mutable, Scaled3D
	{
		/**
		 * Changes the size of the {@code Scaled3D}.
		 * 
		 * @param w  a size width
		 * @param h  a size height
		 * @param d  a size depth
		 */
		public default void setSize(float w, float h, float d)
		{
			setSize(new Vector3(w, h, d));
		}
	}
	
	
	@Override
	public default Vector3 Size()
	{
		return new Vector3(Width(), Height(), Depth());
	}
	
	
	/**
	 * Returns the height of the {@code Scaled3D}.
	 * 
	 * @return  a height factor
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the width of the {@code Scaled3D}.
	 * 
	 * @return  a width factor
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the depth of the {@code Scaled3D}.
	 * 
	 * @return  a depth factor
	 */
	public default float Depth()
	{
		return Size().Z();
	}
}