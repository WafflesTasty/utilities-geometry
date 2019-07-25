package zeno.util.geom.collidables.geometry.generic;

import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.collisions.geometry.CLSSphere;

/**
 * The {@code ISphere} interface defines the collision operations for sphere geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IEllipsoid
 */
public interface ISphere extends IEllipsoid
{	
	@Override
	public default ICollision Collisions()
	{
		return new CLSSphere(this);
	}
	
	// Optional Bounds overrides.
	
	@Override
	public default float Diameter()
	{
		return Size().get(0);
	}
	
	@Override
	public default ISphere Ball()
	{
		return this;
	}
}