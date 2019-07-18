package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Intersection;

/**
 * The {@code INTPointASpace} defines intersection between a {@code Point} and an {@code Affine.Space}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Intersection
 */
public class INTPointASpace implements Intersection
{		
	@Override
	public ICollidable intersect(ICollidable c1, ICollidable c2)
	{
		Point        p = (Point)        c1;
		Affine.Space s = (Affine.Space) c2;
		
		
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
		return Point.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Affine.Space.TYPE;
	}
}