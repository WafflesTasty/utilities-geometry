package waffles.utils.geom.shapes.bounds.convex.hulls;

import waffles.utils.geom.shapes.bounds.BNDGeometry3D;
import waffles.utils.geom.shapes.convex.hulls.Hull;

/**
 * A {@code BNDHull3D} defines dynamic {@code Bounds} for a three-dimensional {@code Hull}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 *
 *
 * @see BNDGeometry3D
 * @see BNDHull
 */
public class BNDHull3D extends BNDHull implements BNDGeometry3D
{
	/**
	 * Creates a new {@code BNDHull3D}.
	 *
	 * @param s  a source hull
	 *
	 *
	 * @see Hull
	 */
	public BNDHull3D(Hull s)
	{
		super(s);
	}
}