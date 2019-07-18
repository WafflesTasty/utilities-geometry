package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTPointASet} defines containment between a {@code Point} and an {@code Affine.Set}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTPointASet implements Containment
{	
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Point p = (Point) c1;
		Affine.Set s = (Affine.Set) c2;
		
				
		for(Point q : s)
		{
			if(!p.equals(q, ulps))
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean contains(ICollidable c1, ICollidable c2)
	{
		return c1.equals(c2, 2);
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Point.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Affine.Set.TYPE;
	}
}