package zeno.util.geom._refactor.interfaces;

import zeno.util.geom._refactor.ITransformation;

/**
 * The {@code ITransformation3D} interface defines an object
 * capable of transforming an {@code ITransformable3D} object.
 *
 * @since Apr 27, 2016
 * @author Zeno
 * 
 * @see ITransformable3D
 * @see ITransformation
 */
public interface ITransformation3D extends ITransformation, ITransformable3D
{
	@Override
	public default ITransformation3D Transform()
	{
		return this;
	}
}