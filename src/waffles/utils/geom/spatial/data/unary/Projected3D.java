package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;

/**
 * A {@code Projected3D} object defines a three-dimensional projection oculus.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.1
 * 
 * 
 * @see Projected
 */
@FunctionalInterface
public interface Projected3D extends Projected
{
	/**
	 * A {@code Mutable Projected3D} can change its own oculus.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Projected3D
	 * @see Projected
	 */
	public static interface Mutable extends Projected.Mutable, Projected3D
	{
		/**
		 * Changes the oculus of the {@code Projected3D}.
		 * 
		 * @param x  an x-coordinate
		 * @param y  an y-coordinate
		 * @param z  an z-coordinate
		 */
		public default void setOculus(float x, float y, float z)
		{
			setOculus(new Vector3(x, y, z));
		}
	}

	
	@Override
	public abstract Vector3 Oculus();

	/**
	 * Returns the oculus x of the {@code Projected3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public default float OculusX()
	{
		return Oculus().X();
	}

	/**
	 * Returns the oculus y of the {@code Projected3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public default float OculusY()
	{
		return Oculus().Y();
	}
	
	/**
	 * Returns the oculus z of the {@code Projected3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public default float OculusZ()
	{
		return Oculus().Y();
	}
}