package zeno.util.geom.collidables.collisions;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;

/**
 * The {@code CLSGeometry} class defines collision for an {@link IGeometry}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSGeometry implements ICollision
{
	@Override
	public Boolean contains(ICollidable c)
	{
		// Eliminate affine spaces.
		if(c instanceof Affine.Space)
		{
			return c.isEmpty();
		}
		
		return null;
	}
}