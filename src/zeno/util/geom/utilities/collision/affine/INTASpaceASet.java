package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Intersection;
import zeno.util.tools.helper.Array;

/**
 * The {@code INTASpaceASet} defines intersection between an {@code Affine.Space} and an {@code Affine.Set}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Intersection
 */
public class INTASpaceASet implements Intersection
{		
	@Override
	public ICollidable intersect(ICollidable c1, ICollidable c2)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Set   s2 = (Affine.Set)   c2;
		
		
		Point[] pts = new Point[0];
		for(Point p : s2)
		{
			if(s1.contains(p))
			{
				pts = Array.add.to(pts, p);
			}
		}
		
		return ASpaces.set(pts);
	}

	@Override
	public boolean intersects(ICollidable c1, ICollidable c2)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Set   s2 = (Affine.Set)   c2;
		
		
		for(Point p : s2)
		{
			if(s1.contains(p))
			{
				return true;
			}
		}
		
		return false;
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Affine.Space.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Affine.Set.TYPE;
	}
}