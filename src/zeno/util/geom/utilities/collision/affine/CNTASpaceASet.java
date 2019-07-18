package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTASpaceASet} defines containment between an {@code Affine.Space} and an {@code Affine.Set}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTASpaceASet implements Containment
{		
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Set   s2 = (Affine.Set)   c2;
		
		if(s1.isFinite())
		{
			return s2.equals(s1.Origin(), ulps);
		}
		
		return false;
	}	

	@Override
	public boolean contains(ICollidable c1, ICollidable c2)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Set   s2 = (Affine.Set)   c2;
		
		
		if(s1.isFinite())
		{
			return s2.contains(s1.Origin());
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