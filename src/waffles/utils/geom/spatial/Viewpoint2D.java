package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.owners.Projectable2D;

/**
 * An {@code Viewpoint2D} object defines a transformable viewpoint in a two-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Projectable2D
 * @see Adjustable2D
 * @see Viewpoint
 */
public interface Viewpoint2D extends Viewpoint, Adjustable2D, Projectable2D
{
	@Override
	public default int Dimension()
	{
		return 2;
	}
}