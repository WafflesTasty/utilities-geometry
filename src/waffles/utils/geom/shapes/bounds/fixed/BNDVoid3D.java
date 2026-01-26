package waffles.utils.geom.shapes.bounds.fixed;

import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code BNDVoid} defines dynamic {@code Bounds3D} for {@code Void} geometry.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see Bounds3D
 * @see BNDVoid
 */
public class BNDVoid3D extends BNDVoid implements Bounds3D
{
	/**
	 * Creates a new {@code BNDVoid3D}.
	 * 
	 * @param s  a source void
	 * 
	 * 
	 * @see Void
	 */
	public BNDVoid3D(Void s)
	{
		super(s);
	}
}