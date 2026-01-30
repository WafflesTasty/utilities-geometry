package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical3D;
import waffles.utils.geom.spatial.Viewpoint3D;

/**
 * A {@code ViewProjected2D} defines a three-dimensional {@code Viewpoint Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see ViewProjected
 * @see Geometrical3D
 * @see Viewpoint3D
 */
public interface ViewProjected3D extends ViewProjected, Viewpoint3D, Geometrical3D
{
	@Override
	public default int Dimension()
	{
		return 3;
	}
}