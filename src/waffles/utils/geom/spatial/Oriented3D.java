package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.maps.data.Radial3D;
import waffles.utils.geom.spatial.owners.Movable3D;
import waffles.utils.geom.spatial.owners.Rotatable3D;

/**
 * An {@code Oriented3D} object can be affine-oriented in a three-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 *
 *
 * @see Rotatable3D
 * @see Movable3D
 * @see Radial3D
 * @see Oriented
 */
public interface Oriented3D extends Oriented, Radial3D, Rotatable3D, Movable3D
{
	// NOT APPLICABLE
}