package waffles.utils.geom.bounds.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.axial.spheroid.HyperSpheroid;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code BNDSpheroid2D} defines {@code Bounds} for a transformed {@code Ellipse}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 * 
 *
 * @see BNDSpheroid
 * @see Bounds2D
 */
public class BNDSpheroid2D extends BNDSpheroid implements Bounds2D
{
	/**
	 * Creates a new {@code BNDSpheroid2D}.
	 * 
	 * @param s  a spheroid
	 * @param m  a global map
	 * 
	 * 
	 * @see HyperSpheroid
	 * @see GlobalMap
	 */
	public BNDSpheroid2D(HyperSpheroid s, GlobalMap m)
	{
		super(s, m);
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