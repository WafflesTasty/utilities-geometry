package zeno.util.geom.collidables.collisions.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;

/**
 * The {@code CLSATrivial} class defines collision for a trivial affine space.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSATrivial implements ICollision
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
		return new TrivialASpace();
	}
}