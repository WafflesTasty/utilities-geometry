package zeno.util.geom.interfaces;

import zeno.util.geom.ITransformation;

/**
 * The {@code ITransformation2D} interface defines an object
 * capable of transforming an {@code ITransformable2D} object.
 *
 * @since Apr 27, 2016
 * @author Zeno
 * 
 * @see ITransformable2D
 * @see ITransformation
 */
public interface ITransformation2D extends ITransformation, ITransformable2D
{
	@Override
	public default ITransformation2D Transform()
	{
		return this;
	}
}