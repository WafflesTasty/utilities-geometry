package waffles.utils.geom.shapes.bounds.fixed;

import waffles.utils.geom.shapes.fixed.Universe;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code BNDUniverse} defines dynamic {@code Bounds2D} for {@code Universe} geometry.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see BNDUniverse
 * @see Bounds2D
 */
public class BNDUniverse2D extends BNDUniverse implements Bounds2D
{
	/**
	 * Creates a new {@code BNDUniverse2D}.
	 * 
	 * @param s  a source universe
	 * 
	 * 
	 * @see Universe
	 */
	public BNDUniverse2D(Universe s)
	{
		super(s);
	}
}