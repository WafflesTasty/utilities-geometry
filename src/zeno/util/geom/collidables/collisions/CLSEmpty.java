package zeno.util.geom.collidables.collisions;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;

/**
 * The {@code CLSEmpty} class defines collision for an empty shape.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSEmpty implements ICollision
{
	@Override
	public Boolean contains(ICollidable c)
	{
		return false;
	}

	@Override
	public Boolean inhabits(ICollidable c)
	{
		return true;
	}
	
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		return c.isEmpty();
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		return ICollidable.EMPTY;
	}
}