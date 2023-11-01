package waffles.utils.geom.bounds.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.collidable.axial.spheroid.Spheroid;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code BNDSpheroid3D} defines {@code Bounds} for a transformed {@code Spheroid}.
 *
 * @author Zeno
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
	 * @param e  a spheroid
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 * @see Spheroid
	 */
	public BNDSpheroid3D(Spheroid e, GlobalMap m)
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