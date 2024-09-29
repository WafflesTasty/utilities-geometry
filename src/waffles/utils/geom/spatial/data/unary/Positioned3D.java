package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;

/**
 * A {@code Positioned3D} object defines a three-dimensional origin vector.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.1
 * 
 * 
 * @see Positioned
 */
public interface Positioned3D extends Positioned
{
	/**
	 * A {@code Mutable Positioned3D} can change its own origin.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Positioned3D
	 * @see Positioned
	 */
	public static interface Mutable extends Positioned.Mutable, Positioned3D
	{
		/**
		 * Changes the origin of the {@code Positioned}.
		 * 
		 * @param x  an x-coordinate
		 * @param y  an y-coordinate
		 * @param z  an z-coordinate
		 */
		public default void setOrigin(float x, float y, float z)
		{
			setOrigin(new Vector3(x, y, z));
		}
	}
	

	@Override
	public default Vector3 Origin()
	{
		return new Vector3(X(), Y(), Z());
	}

	
	/**
	 * Returns the x-coordinate of the {@code Positioned3D}.
	 * 
	 * @return  an x-coordinate
	 */
	public default float X()
	{
		return Origin().X();
	}

	/**
	 * Returns the y-coordinate of the {@code Positioned3D}.
	 * 
	 * @return  an y-coordinate
	 */
	public default float Y()
	{
		return Origin().Y();
	}
	
	/**
	 * Returns the z-coordinate of the {@code Positioned3D}.
	 * 
	 * @return  an z-coordinate
	 */
	public default float Z()
	{
		return Origin().Z();
	}
}