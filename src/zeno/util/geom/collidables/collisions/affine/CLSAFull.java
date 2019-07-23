package zeno.util.geom.collidables.collisions.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.Affine.Space;
import zeno.util.geom.collidables.ICollision;

/**
 * The {@code CLSAFull} class defines collision for a full affine space.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSAFull implements ICollision
{
	@Override
	public Boolean contains(ICollidable c)
	{
		return true;
	}

	@Override
	public Boolean inhabits(ICollidable c)
	{
		return equals(c, 0);
	}
	
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof Affine.Space)
		{
			Affine.Space s = (Space) c;
			Matrix m = s.Direction().Complement();
			return m.Columns() == 0;
		}
		
		return false;
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		return c;
	}
}