package waffles.utils.geom.bounds.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.axial.cuboid.Rectangle;
import waffles.utils.geom.maps.GlobalMap;

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
	 * @param r  a rectangle
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 * @see Rectangle
	 */
	public BNDCuboid2D(Rectangle r, GlobalMap m)
	{
		super(r, m);
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