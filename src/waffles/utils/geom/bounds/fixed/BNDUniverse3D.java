package waffles.utils.geom.bounds.fixed;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.collidable.fixed.Universe;

/**
 * A {@code BNDUniverse3D} creates a bounding volume around a three-dimensional {@code Universe} object.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see BNDUniverse
 * @see Bounds3D
 */
public class BNDUniverse3D extends BNDUniverse implements Bounds3D
{
	/**
	 * Creates a new {@code BNDUniverse}.
	 * 
	 * @param s  a source universe
	 * 
	 * 
	 * @see Universe
	 */
	public BNDUniverse3D(Universe s)
	{
		super(s);
	}
	
	
	@Override
	public Vector3 Minimum()
	{
		return (Vector3) super.Minimum();
	}
	
	@Override
	public Vector3 Maximum()
	{
		return (Vector3) super.Maximum();
	}
}