package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical2D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Adjustable2D;

/**
 * An {@code AffineAdjusted2D} defines a two-dimensional {@code Adjustable Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see AffineAdjusted
 * @see Geometrical2D
 * @see Adjustable2D
 */
public interface AffineAdjusted2D extends AffineAdjusted, Adjustable2D, Geometrical2D
{	
	@Override
	public default int Dimension()
	{
		return 2;
	}

	@Override
	public default Point Origin()
	{
		return AffineAdjusted.super.Origin();
	}
}