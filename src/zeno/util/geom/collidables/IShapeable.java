package zeno.util.geom.collidables;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.APoint;
import zeno.util.geom.transformables.ITransformable;
import zeno.util.geom.utilities.bounds.IBounded;

/**
 * The {@code IShapeable} interface defines an object that has a geometric shape.
 * It can be collided with through affine spaces and can be transformed.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformable
 * @see ICollidable
 * @see IBounded
 */
public interface IShapeable extends IBounded, ICollidable, ITransformable
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
	public default boolean contains(APoint p)
	{
		return Shape().contains((APoint) Transform().unmap((Affine) p));
	}
	
	@Override
	public default boolean intersects(ASpace a)
	{
		return Shape().intersects(Transform().unmap(a));
	}
	
	@Override
	public default boolean contains(Vector p)
	{
		return contains(new APoint(p));
	}
}