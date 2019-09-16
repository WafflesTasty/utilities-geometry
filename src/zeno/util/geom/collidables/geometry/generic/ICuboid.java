package zeno.util.geom.collidables.geometry.generic;

import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.collisions.geometry.CLSCuboid;

/**
 * The {ICuboid} interface defines the collision operations for cuboid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface ICuboid extends IGeometry
{	
	@Override
	public default ICollision Collisions()
	{
		return new CLSCuboid(this);
	}
	
	// Optional Bounds overrides.
	
	@Override
	public default ICuboid Box()
	{
		return this;
	}
}