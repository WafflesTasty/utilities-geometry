package waffles.utils.geom.bounds.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code BNDCuboid2D} defines {@code Bounds} for a transformed {@code Rectangle}.
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
	 * @param c  a cuboid
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 * @see HyperCuboid
	 */
	public BNDCuboid2D(HyperCuboid c, GlobalMap m)
	{
		super(c, m);
	}
	
	
	@Override
	public Vector2 Center()
	{
		return (Vector2) super.Center();
	}
	
	@Override
	public Vector2 Size()
	{
		return (Vector2) super.Size();
	}
}