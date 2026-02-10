package waffles.utils.geom.shapes.bounds.convex.axial.spheroid;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code BNDSpheroid} defines dynamic {@code Bounds2D} for a {@code HyperSpheroid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDSpheroid
 * @see Bounds2D
 */
public class BNDSpheroid2D extends BNDSpheroid implements Bounds2D
{
	/**
	 * Creates a new {@code BNDSpheroid2D}.
	 *
	 * @param s  a spheroid
	 * @param m  a global map
	 *
	 *
	 * @see HyperSpheroid
	 * @see LinearMap
	 */
	public BNDSpheroid2D(HyperSpheroid s, LinearMap m)
	{
		super(s, m);
	}
	
	/**
	 * Creates a new {@code BNDSpheroid2D}.
	 *
	 * @param s  a spheroid
	 *
	 *
	 * @see HyperSpheroid
	 */
	public BNDSpheroid2D(HyperSpheroid s)
	{
		super(s);
	}
}