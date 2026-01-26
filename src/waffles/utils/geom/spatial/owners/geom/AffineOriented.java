package waffles.utils.geom.spatial.owners.geom;

import waffles.utils.geom.shapes.Geometrical;
import waffles.utils.geom.spatial.Adjustable;
import waffles.utils.geom.spatial.maps.global.SpatialMap;

/**
 * The {@code AffineOriented} interface defines an n-dimensional {@code Adjustable Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Adjustable
 */
public interface AffineOriented extends Adjustable, Geometrical
{
	@Override
	public abstract SpatialMap.Mutable Transform();

	@Override
	public default int Dimension()
	{
		return Geometrical.super.Dimension();
	}
}