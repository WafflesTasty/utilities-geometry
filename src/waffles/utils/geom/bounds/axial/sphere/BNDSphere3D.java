package waffles.utils.geom.bounds.axial.sphere;

import waffles.utils.geom.bounds.axial.BNDAxial3D;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;

/**
 * A {@code BNDSphere3D} creates a bounding volume around a {@code Sphere} object.
 *
 * @author Waffles
 * @since 18 Sep 2023
 * @version 1.0
 * 
 * 
 * @see BNDAxial3D
 */
public class BNDSphere3D extends BNDAxial3D
{
	/**
	 * Creates a new {@code BNDSphere3D}.
	 * 
	 * @param s  a source sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public BNDSphere3D(HyperSphere s)
	{
		super(s);
	}

	@Override
	public float Diameter()
	{
		return Size().get(0);
	}
}