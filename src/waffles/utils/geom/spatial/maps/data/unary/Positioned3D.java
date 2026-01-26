package waffles.utils.geom.spatial.maps.data.unary;

import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Positioned3D} object defines a three-dimensional origin vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.1
 * 
 * 
 * @see Positioned
 */
public interface Positioned3D extends Positioned
{
	/**
	 * A {@code Mutable Positioned3D} can change its own origin.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Positioned3D
	 * @see Positioned
	 */
	public static interface Mutable extends Positioned.Mutable, Positioned3D
	{
		/**
		 * Changes the origin of the {@code Positioned}.
		 * 
		 * @param x  an x-coordinate
		 * @param y  an y-coordinate
		 * @param z  an z-coordinate
		 */
		public default void setOrigin(float x, float y, float z)
		{
			setOrigin(new Point(x, y, z, 1f));
		}
	}

	
	@Override
	public default int Dimension()
	{
		return 3;
	}
	
	@Override
	public default Point Origin()
	{
		return new Point(X(), Y(), Z(), 1f);
	}
	
	/**
	 * Returns the x-coordinate of the {@code Positioned3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public default float X()
	{
		return Origin().aff(0);
	}

	/**
	 * Returns the y-coordinate of the {@code Positioned3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public default float Y()
	{
		return Origin().aff(1);
	}
	
	/**
	 * Returns the z-coordinate of the {@code Positioned3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public default float Z()
	{
		return Origin().aff(2);
	}
}