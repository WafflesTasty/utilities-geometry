package waffles.utils.geom.shapes.convex.axial.sphere;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.convex.axial.spheroid.BNDSpheroid3D;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code HyperSphere3D} defines a three-dimensional {@code HyperSphere}.
 * This interface exists purely for development convenience.
 * 
 * @author Waffles
 * @since 20 Jan 2026
 * @version 1.1
 *
 * 
 * @see HyperSpheroid3D
 * @see HyperSphere
 */
public interface HyperSphere3D extends HyperSphere, HyperSpheroid3D
{
	@Override
	public default Bounds3D Bounds(LinearMap m)
	{
		return new BNDSpheroid3D(this, m);
	}

	@Override
	public default Bounds3D Bounds()
	{
		return new BNDSpheroid3D(this);
	}
}
