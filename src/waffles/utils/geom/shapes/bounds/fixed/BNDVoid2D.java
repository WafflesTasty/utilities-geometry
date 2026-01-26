package waffles.utils.geom.shapes.bounds.fixed;

import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code BNDVoid} defines dynamic {@code Bounds2D} for {@code Void} geometry.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 *
 * @see Bounds2D
 * @see BNDVoid
 */
public class BNDVoid2D extends BNDVoid implements Bounds2D
{
	/**
	 * Creates a new {@code BNDVoid2D}.
	 *
	 * @param s  a source void
	 *
	 *
	 * @see Void
	 */
	public BNDVoid2D(Void s)
	{
		super(s);
	}
}