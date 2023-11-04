package waffles.utils.geom.bounds.hulls;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.convex.hulls.Hull;

/**
 * A {@code BNDHull2D} creates a bounding volume around a two-dimensional {@code Hull} object.
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
	 * @see Hull
	 */
	public BNDHull2D(Hull s)
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