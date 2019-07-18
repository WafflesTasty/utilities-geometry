package zeno.util.geom.collidables;

import zeno.util.geom.ICollidable;
import zeno.util.geom.transformables.IAdjustable;
import zeno.util.geom.utilities.bounds.IBounded;
import zeno.util.geom.utilities.Collisions;
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
	public default ICollidable intersect(ICollidable c)
	{
		if(c instanceof Affine)
		{
			return Shape().intersect(Transform().unmap((Affine) c));
		}
		
		throw new RuntimeException("Intersection between IShapeable and non-Affine has not been implemented.");
	}
		
	@Override
	public default boolean intersects(ICollidable c)
	{
		if(c instanceof Affine)
		{
			return Shape().intersects(Transform().unmap((Affine) c));
		}
		
		throw new RuntimeException("Intersection between IShapeable and non-Affine has not been implemented.");
	}
	
	@Override
	public default boolean contains(ICollidable c)
	{
		if(c instanceof Affine)
		{
			return Shape().contains(Transform().unmap((Affine) c));
		}
		
		throw new RuntimeException("Containment between IShapeable and non-Affine has not been implemented.");
	}

	@Override
	public default Bounds Bounds()
	{
		return Collisions.bounds(this);
	}
}