package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.complex.Quaternion;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.spatial.data.spin.Spin3D;

/**
 * A {@code Rotated3D} object defines a three-dimensional rotation spin.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.0
 * 
 * 
 * @see Rotated
 */
@FunctionalInterface
public interface Rotated3D extends Rotated
{
	/**
	 * A {@code Mutable Rotated3D} can change its own spin.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Rotated3D
	 * @see Rotated
	 */
	public static interface Mutable extends Rotated.Mutable, Rotated3D
	{
		/**
		 * Changes the spin of the {@code Rotated3D}.
		 * 
		 * @param v  a spin vector
		 * @param a  a spin angle
		 * 
		 * 
		 * @see Vector3
		 */
		public default void setSpin(Vector3 v, float a)
		{
			setSpin(new Spin3D(v, a));
		}
		
		/**
		 * Changes the spin of the {@code Rotated3D}.
		 * 
		 * @param q  a spin quaternion
		 * 
		 * 
		 * @see Quaternion
		 */
		public default void setSpin(Quaternion q)
		{
			setSpin(new Spin3D(q));
		}
	}

	
	@Override
	public abstract Spin3D Spin();
	
	
	/**
	 * Returns a forward {@code Vector3}.
	 * 
	 * @return  a forward vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Forward()
	{
		return Spin().Forward();
	}
	
	/**
	 * Returns a right {@code Vector3}.
	 * 
	 * @return  a right vector
	 * 
	 * 
	 * @see Vector2
	 */
	public default Vector3 Right()
	{
		return Spin().Right();
	}
	
	/**
	 * Returns an up {@code Vector3}.
	 * 
	 * @return  an up vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Up()
	{
		return Spin().Up();
	}
}