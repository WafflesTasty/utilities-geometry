package waffles.utils.geom.shapes.bounds.convex.cuboid;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code BNDCuboid2D} defines dynamic {@code Bounds2D} for a {@code HyperCuboid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDCuboid
 * @see Bounds2D
 */
@FunctionalInterface
public interface BNDCuboid2D extends BNDCuboid, Bounds2D
{
	/**
	 * A {@code BNDCuboid2D.Transform} computes a transformed {@code BNDCuboid2D}.
	 *
	 * @author Waffles
	 * @since 08 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDCuboid2D
	 */
	public static class Transform extends BNDCuboid.Transform implements BNDCuboid2D
	{
		/**
		 * Creates a new {@code Transform}.
		 * 
		 * @param b  a base bounds
		 * @param m  a linear map
		 * 
		 * 
		 * @see BNDCuboid
		 * @see LinearMap
		 */
		public Transform(BNDCuboid b, LinearMap m)
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