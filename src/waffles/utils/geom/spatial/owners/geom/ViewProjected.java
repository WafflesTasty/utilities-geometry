package waffles.utils.geom.spatial.owners.geom;

import waffles.utils.geom.shapes.Geometrical;
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
	public abstract WatcherMap.Mutable Transform();

	@Override
	public default int Dimension()
	{
		return Geometrical.super.Dimension();
	}
}