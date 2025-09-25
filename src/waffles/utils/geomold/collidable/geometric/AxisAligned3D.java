package waffles.utils.geom.collidable.geometric;

import waffles.utils.geom.collidable.Geometrical3D;
import waffles.utils.geom.collidable.axial.AxialSet3D;
import waffles.utils.geom.spatial.Aligned3D;

/**
 * An {@code AxisAligned3D} object defines an axis-aligned, three-dimensional geometric object.
 * It defines a shape, an affine transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see AxisAligned
 * @see Geometrical3D
 * @see Aligned3D
 */
public interface AxisAligned3D extends AxisAligned, Aligned3D, Geometrical3D
{
	@Override
	public abstract AxialSet3D Shape();
}