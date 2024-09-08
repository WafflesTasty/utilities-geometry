package waffles.utils.geom.utilities;

/**
 * A {@code Dimensional} object defines a single integer dimension.
 *
 * @author Waffles
 * @since 08 Sep 2024
 * @version 1.1
 */
public interface Dimensional
{
	/**
	 * Returns the dimension of the {@code Dimensional}.
	 * This is usually equal to the dimension of the surrounding space.
	 * 
	 * @return  an object dimension
	 */
	public abstract int Dimension();
}