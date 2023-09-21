package waffles.utils.geom.bounds.axial;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.spatial.Axial;

/**
 * A {@code BNDAxial3D} creates a bounding volume around a three-dimensional {@code Axial} object.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see BNDAxial
 * @see Bounds3D
 */
public class BNDAxial3D extends BNDAxial implements Bounds3D
{
	/**
	 * Creates a new {@code BNDAxial3D}.
	 * 
	 * @param s  a source axial
	 * 
	 * 
	 * @see Axial
	 */
	public BNDAxial3D(Axial s)
	{
		super(s);
	}
	
	
	@Override
	public Vector3 Center()
	{
		return (Vector3) super.Center();
	}
	
	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
	}
}