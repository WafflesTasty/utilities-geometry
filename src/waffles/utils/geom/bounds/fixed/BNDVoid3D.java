package waffles.utils.geom.bounds.fixed;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.collidable.fixed.Void;

/**
 * A {@code BNDVoid3D} creates a bounding volume around a three-dimensional {@code Void} object.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see Bounds3D
 * @see BNDVoid
 */
public class BNDVoid3D extends BNDVoid implements Bounds3D
{
	/**
	 * Creates a new {@code BNDVoid3D}.
	 * 
	 * @param s  a source void
	 * 
	 * 
	 * @see Void
	 */
	public BNDVoid3D(Void s)
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