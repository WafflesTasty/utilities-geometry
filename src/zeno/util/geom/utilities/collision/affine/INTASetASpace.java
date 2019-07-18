package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Intersection;
import zeno.util.tools.helper.Array;

/**
 * The {@code INTASetASpace} defines intersection between an {@code Affine.Set} and an {@code Affine.Space}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Intersection
 */
public class INTASetASpace implements Intersection
{		
	@Override
	public ICollidable intersect(ICollidable c1, ICollidable c2)
	{
		Affine.Set   s1 = (Affine.Set)   c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		
		Point[] pts = new Point[0];
		for(Point p : s1)
		{
			if(s2.contains(p))
			{
				pts = Array.add.to(pts, p);
			}
		}
		
		return ASpaces.set(pts);
	}

	@Override
	public boolean intersects(ICollidable c1, ICollidable c2)
	{
		Affine.Set   s1 = (Affine.Set)   c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		
		for(Point p : s1)
		{
			if(s2.contains(p))
			{
				return true;
			}
		}
		
		return false;
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Affine.Set.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Affine.Space.TYPE;
	}
}