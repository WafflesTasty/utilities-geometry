package zeno.util.geom.utilities.collision.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.Affine.Set;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Intersection;
import zeno.util.tools.helper.Array;

/**
 * The {@code INTASetASet} defines intersection between two {@code Affine.Set} objects.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Intersection
 */
public class INTASetASet implements Intersection
{	
	@Override
	public ICollidable intersect(ICollidable c1, ICollidable c2)
	{
		Affine.Set s1 = (Affine.Set) c1;
		Affine.Set s2 = (Affine.Set) c2;
		
		
		Point[] pts = new Point[0];
		for(Point p : s1)
		{
			int index = index(s2, p, 2);
			if(index != -1)
			{
				pts = Array.add.to(pts, p);
			}
		}

		return ASpaces.set(pts);
	}

	@Override
	public boolean intersects(ICollidable c1, ICollidable c2)
	{
		Affine.Set s1 = (Affine.Set) c1;
		Affine.Set s2 = (Affine.Set) c2;
		
		
		for(Point p : s1)
		{
			if(s2.contains(p))
			{
				return true;
			}
		}

		return false;
	}

	
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