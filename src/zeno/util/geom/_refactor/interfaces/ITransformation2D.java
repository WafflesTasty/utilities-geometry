package zeno.util.geom._refactor.interfaces;

import zeno.util.geom._refactor.ITransformation;

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