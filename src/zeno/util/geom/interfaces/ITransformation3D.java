package zeno.util.geom.interfaces;

import zeno.util.geom.ITransformation;

/**
 * The {@code ITransformation3D} interface defines an object
 * capable of transforming an {@code ITransformable3D} object.
 *
 * @author Zeno
 * @since Apr 27, 2016
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