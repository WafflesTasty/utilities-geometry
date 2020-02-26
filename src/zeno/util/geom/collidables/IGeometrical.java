package zeno.util.geom.collidables;

import zeno.util.geom.ICollidable;
import zeno.util.geom.ITransformable;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.collisions.CLSShaped;

/**
 * The {@code IGeometrical} interface defines an object that has a geometric shape.
 * It admits a homogeneous transformations and can be collided with through affine spaces.
 *
 * @author Zeno
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see ITransformable
 * @see ICollidable
 * @see IBounded
 */
public interface IGeometrical extends IBounded, ICollidable, ITransformable
{
	/**
	 * Returns the shape of the {@code IGeometrical}.
	 * 
	 * @return  an object shape
	 * 
	 * 
	 * @see IGeometry
	 */
	public abstract IGeometry Shape();


	@Override
	public default ICollision Collisions()
	{
		return new CLSShaped(this);
	}
		
	@Override
	public default Bounds Bounds()
	{
		return Shape().Bounds(Transform());
	}
}