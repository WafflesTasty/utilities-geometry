package waffles.utils.geom.shapes.bounds.convex.axial.cuboid;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code BNDCuboid} defines dynamic {@code Bounds} for a {@code CuboidSet}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDCuboid
 * @see Bounds2D
 */
public class BNDCuboid2D extends BNDCuboid implements Bounds2D
{
	/**
	 * Creates a new {@code BNDCuboid2D}.
	 *
	 * @param s  a cuboid set
	 * @param m  a linear map
	 *
	 *
	 * @see HyperCuboid
	 * @see LinearMap
	 */
	public BNDCuboid2D(HyperCuboid s, LinearMap m)
	{
		super(s, m);
	}
	
	/**
	 * Creates a new {@code BNDCuboid2D}.
	 *
	 * @param s  a cuboid set
	 *
	 *
	 * @see HyperCuboid
	 */
	public BNDCuboid2D(HyperCuboid s)
	{
		super(s);
	}
}