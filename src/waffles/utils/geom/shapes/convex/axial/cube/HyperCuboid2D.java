package waffles.utils.geom.shapes.convex.axial.cube;

import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid2D;
import waffles.utils.geom.shapes.convex.axial.AxialSet2D;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code HyperCuboid2D} defines a three-dimensional {@code HyperCuboid}.
 * This interface exists purely for development convenience.
 * 
 * @author Waffles
 * @since 20 Jan 2026
 * @version 1.1
 *
 * 
 * @see HyperCuboid
 * @see AxialSet2D
 */
public interface HyperCuboid2D extends HyperCuboid, AxialSet2D
{
	@Override
	public default Bounds2D Bounds()
	{
		return (BNDCuboid2D) () -> this;
	}
	
	@Override
	public default int Dimension()
	{
		return 2;
	}
}
