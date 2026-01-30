package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical2D;
import waffles.utils.geom.spatial.Viewpoint2D;

/**
 * A {@code ViewProjected2D} defines a two-dimensional {@code Viewpoint Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see ViewProjected
 * @see Geometrical2D
 * @see Viewpoint2D
 */
public interface ViewProjected2D extends ViewProjected, Viewpoint2D, Geometrical2D
{
	@Override
	public default int Dimension()
	{
		return 2;
	}
}