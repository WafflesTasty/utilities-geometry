package zeno.util.geom.collidables;

import zeno.util.geom.ICollideable2D;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.bounds.IBounded2D;

/**
 * The {@code IGeometrical2D} interface defines an object that has a 2D geometric shape.
 * It admits a homogeneous transformations and can be collided with through affine spaces.
 *
 * @author Zeno
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see IGeometrical
 * @see ICollideable2D
 * @see IBounded2D
 */
public interface IGeometrical2D extends IBounded2D, ICollideable2D, IGeometrical
{
	@Override
	public abstract IGeometry2D Shape();

	@Override
	public default Bounds2D Bounds()
	{
		return Shape().Bounds(Transform());
	}
}