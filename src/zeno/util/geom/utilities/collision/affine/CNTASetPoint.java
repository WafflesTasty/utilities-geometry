package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTASetPoint} defines containment between an {@code Affine.Set} and a {@code Point}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTASetPoint implements Containment
{	
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Affine.Set s = (Affine.Set) c1;
		Point p = (Point) c2;
		
		
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
		Affine.Set s = (Affine.Set) c1;
		Point p = (Point) c2;
		
		
		for(Point q : s.Span())
		{
			if(p.equals(q, 2))
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
		return Point.TYPE;
	}
}