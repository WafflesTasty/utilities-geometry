package waffles.utils.geom.bounds.axial;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.spatial.Axial;

/**
 * A {@code BNDAxial2D} creates a bounding volume around a two-dimensional {@code Axial} object.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see BNDAxial
 * @see Bounds2D
 */
public class BNDAxial2D extends BNDAxial implements Bounds2D
{
	/**
	 * Creates a new {@code BNDAxial2D}.
	 * 
	 * @param s  a source axial
	 * 
	 * 
	 * @see Axial
	 */
	public BNDAxial2D(Axial s)
	{
		super(s);
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