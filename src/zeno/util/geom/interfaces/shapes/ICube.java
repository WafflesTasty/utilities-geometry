package zeno.util.geom.interfaces.shapes;

import zeno.util.geom.IShape;

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