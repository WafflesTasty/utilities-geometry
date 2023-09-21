package waffles.utils.geom.bounds.axial.sphere;

import waffles.utils.geom.bounds.axial.BNDAxial2D;
import waffles.utils.geom.collidable.axial.spheroid.Circle;

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
	 * @param c  a source circle
	 * 
	 * 
	 * @see Circle
	 */
	public BNDSphere2D(Circle c)
	{
		super(c);
	}

	@Override
	public float Diameter()
	{
		return Size().get(0);
	}
}