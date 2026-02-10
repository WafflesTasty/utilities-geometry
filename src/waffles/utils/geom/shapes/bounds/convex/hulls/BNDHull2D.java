package waffles.utils.geom.shapes.bounds.convex.hulls;

import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code BNDHull2D} defines dynamic {@code Bounds2D} for a {@code Hull}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 *
 *
 * @see Bounds2D
 * @see BNDHull
 */
public class BNDHull2D extends BNDHull implements Bounds2D
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