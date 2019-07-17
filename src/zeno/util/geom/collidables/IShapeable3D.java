package zeno.util.geom.collidables;

import zeno.util.geom.ICollideable3D;
import zeno.util.geom.transformables.IAdjustable3D;

/**
 * The {@code IShapeable3D} interface defines an object that has a 3D geometric shape.
 * It can be collided with through affine spaces and can be transformed.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see IAdjustable3D
 * @see ICollideable3D
 * @see IShapeable
 */
public interface IShapeable3D extends IAdjustable3D, ICollideable3D, IShapeable
{
	@Override
	public abstract IGeometry3D Shape();
}