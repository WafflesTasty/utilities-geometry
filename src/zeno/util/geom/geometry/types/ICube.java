package zeno.util.geom.geometry.types;

import zeno.util.geom.geometry.IShape;

/**
 * The {@code ICube} interface defines behavior for n-dimensional cubes.
 * 
 * @since Mar 23, 2017
 * @author Zeno
 * 
 * @see IShape
 */
public interface ICube extends IShape
{
	/**
	 * Returns the length of the {@code ICube}.
	 * 
	 * @return  the cube's length
	 */
	public default float Length()
	{
		return Size().get(0);
	}
}