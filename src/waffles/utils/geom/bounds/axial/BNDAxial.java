package waffles.utils.geom.bounds.axial;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.spatial.data.Axial;

/**
 * A {@code BNDAxial} creates a bounding volume around an {@code Axial} object.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Bounds
 */
public class BNDAxial implements Bounds
{
	private Axial src;
	
	/**
	 * Creates a new {@code BNDAxial}.
	 * 
	 * @param s  a source axial
	 * 
	 * 
	 * @see Axial
	 */
	public BNDAxial(Axial s)
	{
		src = s;
	}
	
	
	@Override
	public float Diameter()
	{
		return Size().norm();
	}
	
	@Override
	public Vector Center()
	{
		return src.Origin();
	}
	
	@Override
	public Vector Size()
	{
		return src.Scale();
	}
}