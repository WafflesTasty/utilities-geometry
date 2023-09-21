package waffles.utils.geom.collidable;

import waffles.utils.geom.collidable.axial.AxialSet3D;
import waffles.utils.geom.spatial.owners.Aligned3D;

/**
 * An {@code Alignable3D} object defines an axis-aligned, three-dimensional geometric object.
 * It defines a shape, an affine transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical3D
 * @see Aligned3D
 * @see Alignable
 */
public interface Alignable3D extends Alignable, Aligned3D, Geometrical3D
{
	@Override
	public abstract AxialSet3D Shape();
}