package waffles.utils.geom.spatial.maps.data.unary;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.tools.patterns.properties.Immutable;

/**
 * A {@code Scaled} object defines a size vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 *
 *
 * @see Dimensional
 * @see Immutable
 */
@FunctionalInterface
public interface Scaled extends Immutable, Dimensional
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
		 * @param s  a size point
		 *
		 *
		 * @see Point
		 */
		public abstract void setScale(Point s);
	}

	
	@Override
	public default int Dimension()
	{
		return Scale().Dimension();
	}

	/**
	 * Returns the size of the {@code Scaled}.
	 *
	 * @return  a scale arrow
	 *
	 *
	 * @see Arrow
	 */
	public abstract Arrow Scale();
}