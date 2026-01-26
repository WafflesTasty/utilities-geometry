package waffles.utils.geom.shapes.convex.axial.sphere;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.convex.axial.spheroid.BNDSpheroid3D;
import waffles.utils.geom.shapes.convex.axial.AxialSet3D;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code HyperSpheroid3D} defines a three-dimensional {@code HyperSpheroid}.
 * This interface exists purely for development convenience.
 * 
 * @author Waffles
 * @since 20 Jan 2026
 * @version 1.1
 *
 * 
 * @see HyperSpheroid
 * @see AxialSet3D
 */
public interface HyperSpheroid3D extends HyperSpheroid, AxialSet3D
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
