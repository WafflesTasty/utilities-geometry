package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTPointASpace} defines containment between a {@code Point} and an {@code Affine.Space}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTPointASpace implements Containment
{		
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Point p = (Point) c1;
		Affine.Space s = (Affine.Space) c2;		
	
		
		if(s.isFinite())
		{
			return p.equals(s.Origin(), ulps);
		}

		return false;
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
		return Affine.Space.TYPE;
	}
}