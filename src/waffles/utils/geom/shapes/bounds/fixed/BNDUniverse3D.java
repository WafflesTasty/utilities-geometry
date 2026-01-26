package waffles.utils.geom.shapes.bounds.fixed;

import waffles.utils.geom.shapes.fixed.Universe;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code BNDUniverse3D} defines dynamic {@code Bounds3D} for {@code Universe} geometry.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see BNDUniverse
 * @see Bounds3D
 */
public class BNDUniverse3D extends BNDUniverse implements Bounds3D
{
	/**
	 * Creates a new {@code BNDUniverse3D}.
	 * 
	 * @param s  a source universe
	 * 
	 * 
	 * @see Universe
	 */
	public BNDUniverse3D(Universe s)
	{
		super(s);
	}
}