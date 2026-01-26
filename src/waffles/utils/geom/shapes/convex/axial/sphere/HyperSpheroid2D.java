package waffles.utils.geom.shapes.convex.axial.sphere;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.convex.axial.spheroid.BNDSpheroid2D;
import waffles.utils.geom.shapes.convex.axial.AxialSet2D;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code HyperSpheroid2D} defines a two-dimensional {@code HyperSpheroid}.
 * This interface exists purely for development convenience.
 * 
 * @author Waffles
 * @since 20 Jan 2026
 * @version 1.1
 *
 * 
 * @see HyperSpheroid
 * @see AxialSet2D
 */
public interface HyperSpheroid2D extends HyperSpheroid, AxialSet2D
{
	@Override
	public default Bounds2D Bounds(LinearMap m)
	{
		return new BNDSpheroid2D(this, m);
	}

	@Override
	public default Bounds2D Bounds()
	{
		return new BNDSpheroid2D(this);
	}
}
