package waffles.utils.geom.shapes.convex.axial.sphere;

import waffles.utils.geom.shapes.bounds.convex.spheroid.BNDSpheroid2D;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code HyperSphere2D} defines a two-dimensional {@code HyperSphere}.
 * This interface exists purely for development convenience.
 * 
 * @author Waffles
 * @since 20 Jan 2026
 * @version 1.1
 *
 * 
 * @see HyperSpheroid2D
 * @see HyperSphere
 */
public interface HyperSphere2D extends HyperSphere, HyperSpheroid2D
{
	@Override
	public default Bounds2D Bounds()
	{
		return (BNDSpheroid2D) () -> this;
	}
}
