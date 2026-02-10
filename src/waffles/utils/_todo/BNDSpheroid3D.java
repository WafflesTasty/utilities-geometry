package waffles.utils.geom.shapes.bounds.convex.axial.spheroid;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code BNDSpheroid} defines dynamic {@code Bounds3D} for a {@code HyperSpheroid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDSpheroid
 * @see Bounds3D
 */
public class BNDSpheroid3D extends BNDSpheroid implements Bounds3D
{
	/**
	 * Creates a new {@code BNDSpheroid3D}.
	 *
	 * @param s  a spheroid
	 * @param m  a global map
	 *
	 *
	 * @see HyperSpheroid
	 * @see LinearMap
	 */
	public BNDSpheroid3D(HyperSpheroid s, LinearMap m)
	{
		super(s, m);
	}
	
	/**
	 * Creates a new {@code BNDSpheroid3D}.
	 *
	 * @param s  a spheroid
	 *
	 *
	 * @see HyperSpheroid
	 */
	public BNDSpheroid3D(HyperSpheroid s)
	{
		super(s);
	}
}