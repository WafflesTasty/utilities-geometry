package zeno.util.geom.utilities.collision.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.Affine.Set;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTASetASet} defines containment between two {@code Affine.Set} objects.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTASetASet implements Containment
{	
	int index(Set set, Point p, int ulps)
	{
		Vector vec = p.VMatrix();
		Matrix mat = set.VMatrix();
		for(int i = 0; i < mat.Columns(); i++)
		{
			if(vec.equals(mat.Column(i), ulps))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Affine.Set s1 = (Affine.Set) c1;
		Affine.Set s2 = (Affine.Set) c2;
		
		
		boolean[] list = new boolean[s2.Size()];
		// Check if s1 is contained in s2.
		for(Point p : s1)
		{
			int index = index(s2, p, ulps);
			if(index == -1)
			{
				return false;
			}
			
			list[index] = true;
		}
		
		int index = 0;
		// Check if s2 is contained in s1.
		for(Point p : s2)
		{
			// Skip the ones already found.
			if(list[index])
			{
				continue;
			}
			
			index++;
			if(index(s1, p, ulps) == -1)
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean contains(ICollidable c1, ICollidable c2)
	{
		Affine.Set s1 = (Affine.Set) c1;
		Affine.Set s2 = (Affine.Set) c2;
		
		
		for(Point p : s1)
		{
			if(!s2.contains(p))
			{
				return false;
			}
		}

		return true;
	}
	
	
	@Override
	public ICollidable SRCType()
	{
		return Affine.Set.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Affine.Set.TYPE;
	}
}