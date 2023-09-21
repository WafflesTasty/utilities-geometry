package waffles.utils.geom.maps;

/**
 * A {@code Transformable} object defines a transform as a {@code GlobalMap}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 */
public interface Transformable
{
	/**
	 * Returns the transform of the {@code Transformable}.
	 * 
	 * @return  a global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public abstract GlobalMap Transform();
}