package zeno.util.geom.collidables.geometry.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.spatial.Ellipsoid;
import zeno.util.geom.transforms.AffineMap;
import zeno.util.geom.utilities.bounds.Bounds3D;

/**
 * The {@code BNDEllipsoid2D} class defines the bounds of a transformed {@link Ellipsoid}.
 *
 * @author Zeno
 * @since Sep 11, 2019
 * @version 1.0
 * 
 * 
 * @see BNDEllipsoid
 * @see Bounds3D
 */
public class BNDEllipsoid3D extends BNDEllipsoid implements Bounds3D
{
	/**
	 * Creates a new {@code BNDEllipsoid3D}.
	 * 
	 * @param e  a target ellipsoid
	 * @param m  an affine map
	 * 
	 * 
	 * @see AffineMap
	 * @see Ellipsoid
	 */
	public BNDEllipsoid3D(Ellipsoid e, AffineMap m)
	{
		super(e, m);
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