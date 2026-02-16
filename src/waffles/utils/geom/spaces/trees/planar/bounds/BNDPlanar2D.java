package waffles.utils.geom.spaces.trees.planar.bounds;

import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid2D;

/**
 * A {@code BNDPlanar} defines dynamic {@code Bounds2D} for a {@code PlanarNode}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see BNDCuboid2D
 * @see BNDPlanar
 */
public interface BNDPlanar2D extends BNDPlanar, BNDCuboid2D
{
	@Override
	public default int Dimension()
	{
		return 2;
	}
}