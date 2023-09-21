package waffles.utils.geom.bounds.fixed;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.fixed.Universe;

/**
 * A {@code BNDUniverse2D} creates a bounding volume around a two-dimensional {@code Universe} object.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see BNDUniverse
 * @see Bounds2D
 */
public class BNDUniverse2D extends BNDUniverse implements Bounds2D
{
	/**
	 * Creates a new {@code BNDUniverse}.
	 * 
	 * @param s  a source universe
	 * 
	 * 
	 * @see Universe
	 */
	public BNDUniverse2D(Universe s)
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