package zeno.util.geom.collidables.geometry.generic;

import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.collisions.geometry.CLSEllipsoid;
import zeno.util.tools.Floats;

/**
 * The {IEllipsoid} interface defines the collision operations for ellipsoid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface IEllipsoid extends IGeometry
{
	@Override
	public default ICollision Collisions()
	{
		return new CLSEllipsoid(this);
	}
	
	// Optional Bounds overrides.
	
	@Override
	public default float Diameter()
	{
		return Floats.max(Size().Values());
	}
}