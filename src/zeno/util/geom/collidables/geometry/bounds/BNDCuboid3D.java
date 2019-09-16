package zeno.util.geom.collidables.geometry.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.bounds.Bounds3D;
import zeno.util.geom.collidables.geometry.spatial.Cuboid;
import zeno.util.geom.transforms.AffineMap;

/**
 * The {@code BNDCuboid3D} class defines the bounds of a transformed {@link Cuboid}.
 *
 * @author Zeno
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
	 * @param c  a target cuboid
	 * @param m  an affine map
	 * 
	 * 
	 * @see AffineMap
	 * @see Cuboid
	 */
	public BNDCuboid3D(Cuboid c, AffineMap m)
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