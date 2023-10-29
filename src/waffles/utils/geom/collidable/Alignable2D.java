package waffles.utils.geom.collidable;

import waffles.utils.geom.collidable.axial.AxialSet2D;
import waffles.utils.geom.spatial.Aligned2D;

/**
 * An {@code Alignable2D} object defines an axis-aligned, two-dimensional geometric object.
 * It defines a shape, an affine transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Aligned2D
 * @see Alignable
 */
public interface Alignable2D extends Alignable, Aligned2D, Geometrical2D
{
	@Override
	public abstract AxialSet2D Shape();
}