package waffles.utils.geom.shapes.bounds.convex.axial;

import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code BNDAxial3D} defines dynamic {@code Bounds} for an {@code AxialSet3D}.
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
	 * @param s  an axial set
	 * 
	 * 
	 * @see AxialSet
	 */
	public BNDAxial3D(AxialSet s)
	{
		super(s);
	}
}