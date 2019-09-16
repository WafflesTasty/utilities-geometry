package zeno.util.geom.collidables.geometry.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.planar.Ellipse;
import zeno.util.geom.transforms.AffineMap;

/**
 * The {@code BNDEllipsoid2D} class defines the bounds of a transformed {@link Ellipse}.
 *
 * @author Zeno
 * @since Sep 11, 2019
 * @version 1.0
 * 
 * 
 * @see BNDEllipsoid
 * @see Bounds2D
 */
public class BNDEllipsoid2D extends BNDEllipsoid implements Bounds2D
{
	/**
	 * Creates a new {@code BNDEllipsoid2D}.
	 * 
	 * @param e  a target ellipse
	 * @param m  an affine map
	 * 
	 * 
	 * @see AffineMap
	 * @see Ellipse
	 */
	public BNDEllipsoid2D(Ellipse e, AffineMap m)
	{
		super(e, m);
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