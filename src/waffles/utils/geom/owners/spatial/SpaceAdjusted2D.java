package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical2D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Adjusted2D;

/**
 * An {@code SpaceAdjusted2D} defines a two-dimensional {@code Adjusted Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see SpaceAdjusted
 * @see Geometrical2D
 * @see Adjusted2D
 */
public interface SpaceAdjusted2D extends SpaceAdjusted, Adjusted2D, Geometrical2D
{	
	@Override
	public default int Dimension()
	{
		return 2;
	}

	@Override
	public default Point Origin()
	{
		return SpaceAdjusted.super.Origin();
	}
}