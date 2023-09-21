package waffles.utils.geom.bounds.axial.sphere;

import waffles.utils.geom.bounds.axial.BNDAxial;
import waffles.utils.geom.collidable.axial.spheroid.ISphere;

/**
 * A {@code BNDSphere} creates a bounding volume around an {@code ISphere} object.
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
	 * @see ISphere
	 */
	public BNDSphere(ISphere s)
	{
		super(s);
	}

	@Override
	public float Diameter()
	{
		return Size().get(0);
	}
}