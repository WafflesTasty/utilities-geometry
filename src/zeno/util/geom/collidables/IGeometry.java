package zeno.util.geom.collidables;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.utilities.bounds.IBounded;
import zeno.util.geom.utilities.bounds.Bounds;

/**
 * The {@code IGeometry} interface is the base for bounded n-dimensional shapes.
 * <br> It allows for basic collision detection and bounding volumes.
 * 
 * @author Zeno
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see ICollidable
 * @see IBounded
 * @see Bounds
 */
public interface IGeometry extends ICollidable, IBounded, Bounds
{			
	@Override
	public default ICollision Collisions()
	{
		return new CLSGeometry();
	}
	
	@Override
	public default Bounds Bounds()
	{
		return this;
	}
}