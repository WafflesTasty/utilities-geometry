package waffles.utils.geom.shapes.bounds.convex.axial;

import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code BNDAxial2D} defines dynamic {@code Bounds} for an {@code AxialSet2D}.
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
	 * @param s  an axial set
	 *
	 *
	 * @see AxialSet
	 */
	public BNDAxial2D(AxialSet s)
	{
		super(s);
	}
}