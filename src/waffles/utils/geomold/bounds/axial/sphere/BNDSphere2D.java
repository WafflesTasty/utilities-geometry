package waffles.utils.geom.bounds.axial.sphere;

import waffles.utils.geom.bounds.axial.BNDAxial2D;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;

/**
 * A {@code BNDSphere2D} creates a bounding volume around a {@code Circle} object.
 *
 * @author Waffles
 * @since 18 Sep 2023
 * @version 1.0
 * 
 * 
 * @see BNDAxial2D
 */
public class BNDSphere2D extends BNDAxial2D
{
	/**
	 * Creates a new {@code BNDSphere2D}.
	 * 
	 * @param s  a source sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public BNDSphere2D(HyperSphere s)
	{
		super(s);
	}

	@Override
	public float Diameter()
	{
		return Size().get(0);
	}
}