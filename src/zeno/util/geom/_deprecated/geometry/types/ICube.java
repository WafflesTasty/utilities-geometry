package zeno.util.geom._deprecated.geometry.types;

import zeno.util.geom._deprecated.geometry.IShape;

/**
 * The {@code Cube} interface defines behavior for n-dimensional cubes.
 * 
 * @since Mar 23, 2017
 * @author Zeno
 * 
 * @see IShape
 */
public interface ICube extends IShape
{
	/**
	 * Returns the length of the {@code Cube}.
	 * 
	 * @return  the cube's length
	 */
	public default float Length()
	{
		return Size().get(0);
	}
}