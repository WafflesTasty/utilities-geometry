package waffles.utils.geom.bounds.hulls;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.hulls.IHull;

/**
 * A {@code BNDHull2D} creates a bounding volume around a two-dimensional {@code IHull} object.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Bounds2D
 * @see BNDHull
 */
public class BNDHull2D extends BNDHull implements Bounds2D
{
	/**
	 * Creates a new {@code BNDHull2D}.
	 * 
	 * @param s  a source hull
	 * 
	 * 
	 * @see IHull
	 */
	public BNDHull2D(IHull s)
	{
		super(s);
	}
	
	
	@Override
	public Vector2 Minimum()
	{
		return (Vector2) super.Minimum();
	}
	
	@Override
	public Vector2 Maximum()
	{
		return (Vector2) super.Maximum();
	}
}