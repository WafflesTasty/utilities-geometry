package waffles.utils.geom.collidable.geometric;

import waffles.utils.geom.collidable.Geometrical2D;
import waffles.utils.geom.collidable.axial.AxialSet2D;
import waffles.utils.geom.spatial.Aligned2D;

/**
 * An {@code AxisAligned2D} object defines an axis-aligned, two-dimensional geometric object.
 * It defines a shape, an affine transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see AxisAligned
 * @see Geometrical2D
 * @see Aligned2D
 */
public interface AxisAligned2D extends AxisAligned, Aligned2D, Geometrical2D
{
	@Override
	public abstract AxialSet2D Shape();
}