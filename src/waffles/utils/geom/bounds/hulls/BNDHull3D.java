package waffles.utils.geom.bounds.hulls;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.collidable.hulls.IHull;

/**
 * A {@code BNDHull3D} creates a bounding volume around a three-dimensional {@code IHull} object.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Bounds3D
 * @see BNDHull
 */
public class BNDHull3D extends BNDHull implements Bounds3D
{
	/**
	 * Creates a new {@code BNDHull3D}.
	 * 
	 * @param s  a source hull
	 * 
	 * 
	 * @see IHull
	 */
	public BNDHull3D(IHull s)
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