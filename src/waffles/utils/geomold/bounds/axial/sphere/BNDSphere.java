package waffles.utils.geom.bounds.axial.sphere;

import waffles.utils.geom.bounds.axial.BNDAxial;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;

/**
 * A {@code BNDSphere} creates a bounding volume around an {@code HyperSphere} object.
 *
 * @author Waffles
 * @since 18 Sep 2023
 * @version 1.0
 * 
 * 
 * @see BNDAxial
 */
public class BNDSphere extends BNDAxial
{
	/**
	 * Creates a new {@code BNDSphere}.
	 * 
	 * @param s  a source sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public BNDSphere(HyperSphere s)
	{
		super(s);
	}

	@Override
	public float Diameter()
	{
		return Size().get(0);
	}
}