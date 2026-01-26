package waffles.utils.geom.spatial.maps.data.unary;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.tools.patterns.properties.Immutable;

/**
 * A {@code Positioned} object defines an origin point.
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
public interface Positioned extends Immutable, Dimensional
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
	public static interface Mutable extends Immutable.Mutable, Positioned
	{		
		/**
		 * Changes the origin of the {@code Positioned}.
		 * 
		 * @param o  an origin point
		 * 
		 * 
		 * @see Point
		 */
		public abstract void setOrigin(Point o);
	}


	/**
	 * Returns the origin of the {@code Positioned}.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public abstract Point Origin();
	
	@Override
	public default int Dimension()
	{
		return Origin().Dimension();
	}
}