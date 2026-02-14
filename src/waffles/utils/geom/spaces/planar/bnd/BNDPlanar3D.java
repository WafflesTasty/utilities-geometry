package waffles.utils.geom.spaces.planar.bnd;

import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid3D;

/**
 * A {@code BNDPlanar} defines dynamic {@code Bounds3D} for a {@code PlanarNode}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see BNDCuboid3D
 * @see BNDPlanar
 */
public interface BNDPlanar3D extends BNDPlanar, BNDCuboid3D
{
	@Override
	public default int Dimension()
	{
		return 3;
	}
}