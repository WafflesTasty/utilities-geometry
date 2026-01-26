package waffles.utils.geom.spatial.maps.data.unary;

import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Positioned2D} object defines a two-dimensional origin vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.1
 *
 *
 * @see Positioned
 */
public interface Positioned2D extends Positioned
{
	/**
	 * A {@code Mutable Positioned2D} can change its own origin.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 *
	 *
	 * @see Positioned2D
	 * @see Positioned
	 */
	public static interface Mutable extends Positioned.Mutable, Positioned2D
	{
		/**
		 * Changes the origin of the {@code Positioned2D}.
		 *
		 * @param x  an x-coordinate
		 * @param y  an y-coordinate
		 */
		public default void setOrigin(float x, float y)
		{
			setOrigin(new Point(x, y, 1f));
		}
	}

	
	@Override
	public default int Dimension()
	{
		return 2;
	}

	@Override
	public default Point Origin()
	{
		return new Point(X(), Y(), 1f);
	}

	
	/**
	 * Returns the x-coordinate of the {@code Positioned2D}.
	 *
	 * @return  an x-coordinate
	 */
	public default float X()
	{
		return Origin().aff(0);
	}

	/**
	 * Returns the y-coordinate of the {@code Positioned2D}.
	 *
	 * @return  an y-coordinate
	 */
	public default float Y()
	{
		return Origin().aff(1);
	}
}