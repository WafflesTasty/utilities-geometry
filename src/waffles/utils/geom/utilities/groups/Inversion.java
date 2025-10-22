package waffles.utils.geom.utilities.groups;

/**
 * An {@code Inversion} defines an inverse operation.
 *
 * @author Waffles
 * @since 25 Sep 2025
 * @version 1.1
 *
 *
 * @param <O>  an object type
 */
@FunctionalInterface
public interface Inversion<O>
{
	/**
	 * Returns an inverse object.
	 *
	 * @return  an inverse
	 */
	public abstract O inverse();
}