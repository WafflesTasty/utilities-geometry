package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Axial2D;
import waffles.utils.geom.spatial.types.Movable2D;
import waffles.utils.geom.spatial.types.Scalable2D;

/**
 * An {@code Aligned2D} object can be axis-aligned in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Aligned
 * @see Axial2D
 * @see Movable2D
 * @see Scalable2D
 */
public interface Aligned2D extends Aligned, Axial2D, Scalable2D, Movable2D
{
	// NOT APPLICABLE
}