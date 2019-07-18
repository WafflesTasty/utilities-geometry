package zeno.util.geom.utilities.collision.affine;

import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTASpacePoint} defines containment between an {@code Affine.Space} and a {@code Point}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTASpacePoint implements Containment
{		
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Affine.Space s = (Affine.Space) c1;
		Point p = (Point) c2;
		
		
		if(s.isFinite())
		{
			return p.equals(s.Origin(), ulps);
		}

		return false;
	}

	@Override
	public boolean contains(ICollidable c1, ICollidable c2)
	{
		Affine.Space s = (Affine.Space) c1;
		Point p = (Point) c2;
		
		
		VSpace dir = s.Direction();
		Vector ov = p.minus(s.Origin());
		return dir.contains(ov);
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Affine.Space.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Point.TYPE;
	}
}