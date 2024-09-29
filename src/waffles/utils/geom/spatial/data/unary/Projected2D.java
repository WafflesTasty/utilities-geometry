package waffles.utils.geom.spatial.data.unary;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;

/**
 * A {@code Projected2D} object defines a two-dimensional projection oculus.
 *
 * @author Waffles
 * @since 16 Oct 2023
 * @version 1.1
 * 
 * 
 * @see Projected
 */
public interface Projected2D extends Projected
{
	/**
	 * A {@code Mutable Projected2D} can change its own oculus.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Projected2D
	 * @see Projected
	 */
	public static interface Mutable extends Projected.Mutable, Projected2D
	{
		/**
		 * Changes the oculus of the {@code Projected2D}.
		 * 
		 * @param x  an x-coordinate
		 * @param y  an y-coordinate
		 */
		public default void setOculus(float x, float y)
		{
			setOculus(new Vector2(x, y));
		}
	}

	
	@Override
	public default Vector2 Oculus()
	{
		return new Vector2(OculusX(), OculusY());
	}

	/**
	 * Returns the oculus x of the {@code Projected2D}.
	 * 
	 * @return  an x-coordinate
	 */
	public default float OculusX()
	{
		return Oculus().X();
	}

	/**
	 * Returns the oculus y of the {@code Projected2D}.
	 * 
	 * @return  an y-coordinate
	 */
	public default float OculusY()
	{
		return Oculus().Y();
	}
}