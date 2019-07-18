package zeno.util.geom.utilities.collision.affine;

import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTASpaceASpace} defines containment between {@code Affine.Space} objects.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTASpaceASpace implements Containment
{	
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		
		Point p = s1.Origin();
		VSpace dir1 = s1.Direction();
		VSpace dir2 = s2.Direction();
		return dir1.equals(dir2, ulps)
			&& s2.contains(p);
	}

	@Override
	public boolean contains(ICollidable c1, ICollidable c2)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		
		Vector pq = s1.Origin().minus(s2.Origin());
		VSpace dir = s1.Direction().add(s2.Direction());		
		return dir.Dimension() == s2.Dimension()
			&& dir.contains(pq);
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Affine.Space.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Affine.Space.TYPE;
	}
}