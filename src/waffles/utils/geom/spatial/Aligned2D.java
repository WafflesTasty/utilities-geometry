package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Axial2D;
import waffles.utils.geom.spatial.owners.Movable2D;
import waffles.utils.geom.spatial.owners.Scalable2D;

/**
 * An {@code Aligned2D} object can be axis-aligned in a two-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 *
 *
 * @see Scalable2D
 * @see Movable2D
 * @see Axial2D
 * @see Aligned
 */
public interface Aligned2D extends Aligned, Axial2D, Scalable2D, Movable2D
{
	// NOT APPLICABLE
}