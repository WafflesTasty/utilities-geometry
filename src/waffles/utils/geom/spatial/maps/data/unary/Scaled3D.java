package waffles.utils.geom.spatial.maps.data.unary;

import waffles.utils.geom.shapes.points.Arrow;

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
		public default void setScale(float w, float h, float d)
		{
			setScale(new Arrow(w, h, d));
		}
	}
	
	
	@Override
	public default Arrow Scale()
	{
		return new Arrow(Width(), Height(), Depth());
	}
	
	/**
	 * Returns the height of the {@code Scaled3D}.
	 * 
	 * @return  a height factor
	 */
	public default float Height()
	{
		return Scale().aff(1);
	}
	
	/**
	 * Returns the width of the {@code Scaled3D}.
	 * 
	 * @return  a width factor
	 */
	public default float Width()
	{
		return Scale().aff(0);
	}
	
	/**
	 * Returns the depth of the {@code Scaled3D}.
	 * 
	 * @return  a depth factor
	 */
	public default float Depth()
	{
		return Scale().aff(2);
	}
}