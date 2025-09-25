package waffles.utils.geom.collidable;

import waffles.utils.geom.Collideable3D;
import waffles.utils.geom.bounds.Bounded3D;
import waffles.utils.geom.bounds.Bounds3D;

/**
 * A {@code Geometrical3D} object defines an object with a three-dimensional geometric shape.
 * It defines its own global transformation, and admits collision detection.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Collideable3D
 * @see Bounded3D
 */
public interface Geometrical3D extends Bounded3D, Collideable3D, Geometrical
{
	@Override
	public abstract Geometry3D Shape();

	@Override
	public default Bounds3D Bounds()
	{
		return Shape().Bounds(Transform());
	}
}