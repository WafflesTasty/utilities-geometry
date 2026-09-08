package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Adjusted;
import waffles.utils.geom.spatial.maps.global.SpatialMap;

/**
 * An {@code SpaceAdjusted} defines an n-dimensional {@code Adjusted Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Adjusted
 */
public interface SpaceAdjusted extends Adjusted, Geometrical
{
	@Override
	public default Point Origin()
	{
		return Adjusted.super.Origin();
	}
	
	@Override
	public abstract SpatialMap Transform();

	@Override
	public default int Dimension()
	{
		return Geometrical.super.Dimension();
	}
}