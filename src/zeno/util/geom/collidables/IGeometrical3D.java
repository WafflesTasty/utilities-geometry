package zeno.util.geom.collidables;

import zeno.util.geom.ICollideable3D;
import zeno.util.geom.collidables.bounds.Bounds3D;
import zeno.util.geom.collidables.bounds.IBounded3D;

/**
 * The {@code IGeometrical3D} interface defines an object that has a 3D geometric shape.
 * It admits a homogeneous transformations and can be collided with through affine spaces.
 *
 * @author Zeno
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see IGeometrical
 * @see ICollideable3D
 * @see IBounded3D
 */
public interface IGeometrical3D extends IBounded3D, ICollideable3D, IGeometrical
{
	@Override
	public abstract IGeometry3D Shape();

	@Override
	public default Bounds3D Bounds()
	{
		return Shape().Bounds(Transform());
	}
}