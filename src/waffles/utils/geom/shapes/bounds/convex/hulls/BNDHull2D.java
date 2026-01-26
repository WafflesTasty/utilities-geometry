package waffles.utils.geom.shapes.bounds.convex.hulls;

import waffles.utils.geom.shapes.bounds.BNDGeometry2D;
import waffles.utils.geom.shapes.convex.hulls.Hull;

/**
 * A {@code BNDHull2D} defines dynamic {@code Bounds} for a two-dimensional {@code Hull}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 *
 *
 * @see BNDGeometry2D
 * @see BNDHull
 */
public class BNDHull2D extends BNDHull implements BNDGeometry2D
{
	/**
	 * Creates a new {@code BNDHull2D}.
	 *
	 * @param s  a source hull
	 *
	 *
	 * @see Hull
	 */
	public BNDHull2D(Hull s)
	{
		super(s);
	}
}