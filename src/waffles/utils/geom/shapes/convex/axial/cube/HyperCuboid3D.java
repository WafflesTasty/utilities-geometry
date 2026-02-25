package waffles.utils.geom.shapes.convex.axial.cube;

import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid3D;
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
	public default Bounds3D Bounds()
	{
		return (BNDCuboid3D) () -> this;
	}

	@Override
	public default int Dimension()
	{
		return 3;
	}
}
