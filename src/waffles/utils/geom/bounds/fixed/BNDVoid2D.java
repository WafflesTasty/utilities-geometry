package waffles.utils.geom.bounds.fixed;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.fixed.Void;

/**
 * A {@code BNDVoid2D} creates a bounding volume around a two-dimensional {@code Void} object.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see Bounds2D
 * @see BNDVoid
 */
public class BNDVoid2D extends BNDVoid implements Bounds2D
{
	/**
	 * Creates a new {@code BNDVoid2D}.
	 * 
	 * @param s  a source void
	 * 
	 * 
	 * @see Void
	 */
	public BNDVoid2D(Void s)
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