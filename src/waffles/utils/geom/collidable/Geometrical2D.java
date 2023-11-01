package waffles.utils.geom.collidable;

import waffles.utils.geom.Collideable2D;
import waffles.utils.geom.bounds.Bounded2D;
import waffles.utils.geom.bounds.Bounds2D;

/**
 * A {@code Geometrical2D} object defines an object with a two-dimensional geometric shape.
 * It defines its own global transformation, and admits collision detection.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Collideable2D
 * @see Bounded2D
 */
public interface Geometrical2D extends Geometrical, Bounded2D, Collideable2D
{
	@Override
	public abstract Geometry2D Shape();

	@Override
	public default Bounds2D Bounds()
	{
		return Shape().Bounds(Transform());
	}
}