package waffles.utils.geom.utilities.errors;

import waffles.utils.geom.utilities.Dimensional;

/**
 * A {@code DimensionError} is thrown when two objects with incompatible dimensions are encountered.
 *
 * @author Waffles
 * @since 24 Sep 2025
 * @version 1.1
 *
 * 
 * @see RuntimeException
 */
public class DimensionError extends RuntimeException
{
	private static final long serialVersionUID = -1896172981566166550L;
	

	/**
	 * Creates a new {@code DimensionError}.
	 * 
	 * @param d1  a dimensional object
	 * @param d2  a dimensional object
	 * 
	 * 
	 * @see Dimensional
	 */
	public DimensionError(Dimensional d1, Dimensional d2)
	{
		super("Incompatible dimensions (" + d1.Dimension() + ", " + d2.Dimension() + ") for " + d1 + " and " + d2 + ".");
	}
}