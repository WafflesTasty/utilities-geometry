package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Axial3D;
import waffles.utils.geom.spatial.owners.Movable3D;
import waffles.utils.geom.spatial.owners.Scalable3D;

/**
 * An {@code Aligned3D} object can be axis-aligned in a three-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Scalable3D
 * @see Movable3D
 * @see Axial3D
 * @see Aligned
 */
public interface Aligned3D extends Aligned, Axial3D, Scalable3D, Movable3D
{
	// NOT APPLICABLE
}