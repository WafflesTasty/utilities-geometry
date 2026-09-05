package waffles.utils.geom.spatial.owners;

import waffles.utils.geom.spatial.maps.data.spin.Spin2D;
import waffles.utils.geom.spatial.maps.data.unary.Rotated2D;

/**
 * An {@code Rotatable2D} object can be rotated in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.1
 *
 *
 * @see Rotatable
 * @see Rotated2D
 */
public interface Rotatable2D extends Rotatable, Rotated2D
{
	/**
	 * Rotates the {@code Rotatable2D} for a given angle.
	 *
	 * @param a  a rotation angle
	 */
	public default void rotateFor(float a)
	{
		rotateFor(new Spin2D(a));
	}

	/**
	 * Rotates the {@code Rotatable2D} to a new angle.
	 *
	 * @param a  a rotation angle
	 */
	public default void rotateTo(float a)
	{
		rotateTo(new Spin2D(a));
	}


	@Override
	public default Spin2D Spin()
	{
		return (Spin2D) Rotatable.super.Spin();
	}
}