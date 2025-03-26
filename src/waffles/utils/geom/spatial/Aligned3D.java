package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Axial3D;
import waffles.utils.geom.spatial.types.Movable3D;
import waffles.utils.geom.spatial.types.Scalable3D;

/**
 * An {@code AxisAligned3D} object can be axis-aligned in a three-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Aligned
 * @see Axial3D
 * @see Movable3D
 * @see Scalable3D
 */
public interface Aligned3D extends Aligned, Axial3D, Scalable3D, Movable3D
{
	// NOT APPLICABLE
}