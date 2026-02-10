package waffles.utils.geom.shapes;

import waffles.utils.geom.Collideable2D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.owners.Bounded2D;
import waffles.utils.geom.spatial.maps.data.unary.Positioned2D;

/**
 * A {@code Geometry2D} defines a two-dimensional {@code Geometry}.
 *
 * @author Waffles
 * @since Aug 22, 2015
 * @version 1.0
 *
 *
 * @see Collideable2D
 * @see Positioned2D
 * @see Bounded2D
 * @see Geometry
 */
public interface Geometry2D extends Geometry, Collideable2D, Bounded2D, Positioned2D
{
	@Override
	public default int Dimension()
	{
		return 2;
	}

	@Override
	public default Point Origin()
	{
		return Positioned2D.super.Origin();
	}
}