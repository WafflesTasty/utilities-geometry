package waffles.utils.geom.shapes.bounds.convex.spheroid;

import waffles.utils.alg.utilities.affine.LinearMap;
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
public interface BNDSpheroid3D extends BNDSpheroid, Bounds3D
{
	/**s
	 * A {@code BNDSpheroid3D.Transform} computes a transformed {@code BNDSpheroid3D}.
	 *
	 * @author Waffles
	 * @since 08 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDSpheroid3D
	 */
	public static class Transform extends BNDSpheroid.Transform implements BNDSpheroid3D
	{
		/**
		 * Creates a new {@code Transform}.
		 * 
		 * @param b  a base bounds
		 * @param m  a linear map
		 * 
		 * 
		 * @see BNDSpheroid
		 * @see LinearMap
		 */
		public Transform(BNDSpheroid b, LinearMap m)
		{
			super(b, m);
		}
	}


	@Override
	public default Factory Factory()
	{
		return m -> new Transform(this, m);
	}
}