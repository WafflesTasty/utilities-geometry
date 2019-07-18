package zeno.util.geom.collidables;

import zeno.util.geom.ICollideable2D;
import zeno.util.geom.transformables.IAdjustable2D;
import zeno.util.geom.utilities.bounds.Bounds2D;
import zeno.util.geom.utilities.bounds.IBounded2D;

/**
 * The {@code IShapeable2D} interface defines an object that has a 2D geometric shape.
 * It can be collided with through affine spaces and can be transformed.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see IBounded2D
 * @see IAdjustable2D
 * @see ICollideable2D
 * @see IShapeable
 */
public interface IShapeable2D extends IAdjustable2D, IBounded2D, ICollideable2D, IShapeable
{
	@Override
	public abstract IGeometry2D Shape();

	@Override
	public default Bounds2D Bounds()
	{
		return (Bounds2D) IShapeable.super.Bounds();
	}
}