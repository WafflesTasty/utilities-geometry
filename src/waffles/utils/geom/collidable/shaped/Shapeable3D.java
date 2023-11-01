package waffles.utils.geom.collidable.shaped;

import waffles.utils.geom.collidable.Geometrical3D;
import waffles.utils.geom.spatial.Adjustable3D;

/**
 * A {@code Shapeable} object defines a fully adjustable, three-dimensional geometric object.
 * It defines a shape, an affine transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical3D
 * @see Adjustable3D
 * @see Shapeable
 */
public interface Shapeable3D extends Adjustable3D, Geometrical3D, Shapeable
{
	// NOT APPLICABLE
}