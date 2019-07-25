package zeno.util.geom.collidables;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.collisions.CLSShaped;
import zeno.util.geom.transformables.IAdjustable;
import zeno.util.geom.utilities.bounds.IBounded;
import zeno.util.geom.utilities.bounds.Bounds;

/**
 * The {@code IShapeable} interface defines an object that has a geometric shape.
 * It can be collided with through affine spaces and can be transformed.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see IAdjustable
 * @see ICollidable
 * @see IBounded
 */
public interface IShapeable extends IAdjustable, IBounded, ICollidable
{
	/**
	 * Returns the shape of the {@code IShapeable}.
	 * 
	 * @return  the object's shape
	 * 
	 * 
	 * @see IGeometry
	 */
	public abstract IGeometry Shape();

	
	@Override
	public default boolean isEmpty()
	{
		return Shape().isEmpty();
	}

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