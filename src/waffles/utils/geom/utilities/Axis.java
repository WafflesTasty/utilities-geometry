package waffles.utils.geom.utilities;

import waffles.utils.geom.shapes.points.Arrow;

/**
 * An {@code Axis} defines a standard axis up to four dimensions.
 *
 * @author Waffles
 * @since Jul 13, 2026
 * @version 1.1
 */
public enum Axis
{
	/**
	 * A generic x axis.
	 */
	X_AXIS(1, 0, 0, 0),
	/**
	 * A generic y axis.
	 */
	Y_AXIS(0, 1, 0, 0),
	/**
	 * A generic z axis.
	 */
	Z_AXIS(0, 0, 1, 0),
	/**
	 * A generic w axis.
	 */
	W_AXIS(0, 0, 0, 1);
	
	
	private Arrow axis;
	
	private Axis(float... a)
	{
		axis = new Arrow(a);
	}
	
	/**
	 * Returns an {@code Axis} direction.
	 * 
	 * @return  a direction arrow
	 * 
	 * 
	 * @see Arrow
	 */
	public Arrow Direction()
	{
		return axis;
	}
	
	
	/**
	 * Returns an {@code Axis} x-coordinate.
	 * 
	 * @return  an x-coordinate
	 */
	public int X()
	{
		return (int) Direction().X();
	}

	/**
	 * Returns an {@code Axis} y-coordinate.
	 * 
	 * @return  an y-coordinate
	 */
	public int Y()
	{
		return (int) Direction().Y();
	}
	
	/**
	 * Returns an {@code Axis} z-coordinate.
	 * 
	 * @return  an z-coordinate
	 */
	public int Z()
	{
		return (int) Direction().Z();
	}
	
	/**
	 * Returns an {@code Axis} w-coordinate.
	 * 
	 * @return  an w-coordinate
	 */
	public int W()
	{
		return (int) Direction().W();
	}
}