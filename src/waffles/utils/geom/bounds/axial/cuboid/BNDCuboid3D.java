package waffles.utils.geom.bounds.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code BNDCuboid2D} defines {@code Bounds} for a transformed {@code Cuboid}.
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
	 * @param c  a cuboid
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 * @see HyperCuboid
	 */
	public BNDCuboid3D(HyperCuboid c, GlobalMap m)
	{
		super(c, m);
	}
	
	
	@Override
	public Vector3 Center()
	{
		return (Vector3) super.Center();
	}
	
	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
	}
}