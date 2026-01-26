package waffles.utils.geom.shapes.convex.axial.cube;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.convex.axial.cuboid.BNDCuboid3D;
import waffles.utils.geom.shapes.convex.axial.AxialSet3D;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code HyperCuboid3D} defines a three-dimensional {@code HyperCuboid}.
 * This interface exists purely for development convenience.
 * 
 * @author Waffles
 * @since 20 Jan 2026
 * @version 1.1
 *
 * 
 * @see HyperCuboid
 * @see AxialSet3D
 */
public interface HyperCuboid3D extends HyperCuboid, AxialSet3D
{
	@Override
	public default Bounds3D Bounds(LinearMap m)
	{
		return new BNDCuboid3D(this, m);
	}

	@Override
	public default Bounds3D Bounds()
	{
		return new BNDCuboid3D(this);
	}
}
