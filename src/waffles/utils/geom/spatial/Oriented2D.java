package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.maps.data.Radial2D;
import waffles.utils.geom.spatial.owners.Movable2D;
import waffles.utils.geom.spatial.owners.Rotatable2D;

/**
 * An {@code Oriented2D} object can be affine-oriented in a two-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 *
 *
 * @see Rotatable2D
 * @see Movable2D
 * @see Radial2D
 * @see Oriented
 */
public interface Oriented2D extends Oriented, Radial2D, Rotatable2D, Movable2D
{
	// NOT APPLICABLE
}