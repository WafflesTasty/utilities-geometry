package zeno.util.geom.collidables.geometry.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.AffineMap;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.planar.Rectangle;

/**
 * The {@code BNDCuboid2D} class defines the bounds of a transformed {@link Rectangle}.
 *
 * @author Zeno
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
	 * @param r  a target rectangle
	 * @param m  an affine map
	 * 
	 * 
	 * @see AffineMap
	 * @see Rectangle
	 */
	public BNDCuboid2D(Rectangle r, AffineMap m)
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