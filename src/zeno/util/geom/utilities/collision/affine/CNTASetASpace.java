package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTASetASpace} defines containment between an {@code Affine.Set} and an {@code Affine.Space}.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTASetASpace implements Containment
{		
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Affine.Set   s1 = (Affine.Set)   c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		if(s2.isFinite())
		{
			return s1.equals(s2.Origin(), ulps);
		}
		
		return false;
	}

	@Override
	public boolean contains(ICollidable c1, ICollidable c2)
	{
		Affine.Set   s1 = (Affine.Set)   c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		if(s2.isFinite())
		{
			return s1.contains(s2.Origin());
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