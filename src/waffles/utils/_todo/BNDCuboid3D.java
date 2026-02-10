package waffles.utils.geom.shapes.bounds.convex.axial.cuboid;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code BNDCuboid} defines dynamic {@code Bounds3D} for a {@code CuboidSet}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDCuboid
 * @see Bounds3D
 */
public class BNDCuboid3D extends BNDCuboid implements Bounds3D
{
	/**
	 * Creates a new {@code BNDCuboid3D}.
	 *
	 * @param s  an axial set
	 * @param m  a linear map
	 *
	 *
	 * @see HyperCuboid
	 * @see LinearMap
	 */
	public BNDCuboid3D(HyperCuboid s, LinearMap m)
	{
		super(s, m);
	}
	
	/**
	 * Creates a new {@code BNDCuboid3D}.
	 *
	 * @param s  an axial set
	 *
	 *
	 * @see HyperCuboid
	 */
	public BNDCuboid3D(HyperCuboid s)
	{
		super(s);
	}
}