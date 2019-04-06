package zeno.util.geom.collidables;

import zeno.util.geom.ICollideable3D;
import zeno.util.geom.transformables.ITransformable3D;

/**
 * The {@code IShapeable2D} interface defines an object that has a 3D geometric shape.
 * It can be collided with through affine spaces and can be transformed.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformable3D
 * @see ICollideable3D
 * @see IShapeable
 */
public interface IShapeable3D extends IShapeable, ICollideable3D, ITransformable3D
{
	@Override
	public abstract IGeometry3D Shape();
}