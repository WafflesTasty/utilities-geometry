package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Viewpoint;
import waffles.utils.geom.spatial.maps.global.WatcherMap;

/**
 * The {@code ViewProjected} interface defines an n-dimensional {@code Viewpoint Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Viewpoint
 */
public interface ViewProjected extends Viewpoint, Geometrical
{
	@Override
	public default Point Origin()
	{
		return Viewpoint.super.Origin();
	}
	
	@Override
	public abstract WatcherMap.Mutable Transform();

	@Override
	public default int Dimension()
	{
		return Geometrical.super.Dimension();
	}
}