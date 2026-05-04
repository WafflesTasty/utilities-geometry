package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.owners.Projectable3D;

/**
 * An {@code Viewpoint3D} object defines a transformable viewpoint in a three-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Projectable3D
 * @see Adjusted3D
 * @see Viewpoint
 */
public interface Viewpoint3D extends Viewpoint, Projectable3D, Adjusted3D
{
	@Override
	public default int Dimension()
	{
		return 3;
	}
}