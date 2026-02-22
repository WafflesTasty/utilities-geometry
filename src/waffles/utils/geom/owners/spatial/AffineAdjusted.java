package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Adjustable;
import waffles.utils.geom.spatial.maps.global.SpatialMap;

/**
 * An {@code AffineAdjusted} defines an n-dimensional {@code Adjustable Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Adjustable
 */
public interface AffineAdjusted extends Adjustable, Geometrical
{
	@Override
	public default Point Origin()
	{
		return Adjustable.super.Origin();
	}
	
	@Override
	public abstract SpatialMap.Mutable Transform();

	@Override
	public default int Dimension()
	{
		return Geometrical.super.Dimension();
	}
}