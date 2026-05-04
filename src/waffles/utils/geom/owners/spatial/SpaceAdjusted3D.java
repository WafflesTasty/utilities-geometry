package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Adjusted3D;

/**
 * An {@code SpaceAdjusted3D} defines a three-dimensional {@code Adjusted Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see SpaceAdjusted
 * @see Geometrical3D
 * @see Adjusted3D
 */
public interface SpaceAdjusted3D extends SpaceAdjusted, Adjusted3D, Geometrical3D
{
	@Override
	public default int Dimension()
	{
		return 3;
	}

	@Override
	public default Point Origin()
	{
		return SpaceAdjusted.super.Origin();
	}
}