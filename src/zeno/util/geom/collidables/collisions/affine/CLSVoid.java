package zeno.util.geom.collidables.collisions.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code CLSVoid} class defines collision for an empty affine object.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSVoid implements ICollision
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
		return c.equals(Geometries.VOID);
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		return Geometries.VOID;
	}
}