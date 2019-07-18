package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Intersection;

/**
 * The {@code INTASetPoint} defines intersection between an {@code Affine.Set} and a {@code Point}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Intersection
 */
public class INTASetPoint implements Intersection
{	
	@Override
	public ICollidable intersect(ICollidable c1, ICollidable c2)
	{
		Affine.Set s = (Affine.Set) c1;
		Point      p = (Point)      c2;
		
		
		if(!s.contains(p))
		{
			int dim = p.VMatrix().Rows();
			return ASpaces.trivial(dim);
		}
		
		return p;
	}

	@Override
	public boolean intersects(ICollidable c1, ICollidable c2)
	{
		return c1.contains(c2);
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Affine.Set.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Point.TYPE;
	}
}