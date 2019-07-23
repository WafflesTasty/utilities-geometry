package zeno.util.geom.collidables.collisions.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.Affine.Set;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.tools.helper.Array;

/**
 * The {@code CLSASet} class defines collision for an {@code Affine} {@link Set}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSASet implements ICollision
{
	private Affine.Set s;
	
	/**
	 * Creates a new {@code CLSASet}.
	 * 
	 * @param s  a source set
	 * 
	 * 
	 * @see Affine
	 */
	public CLSASet(Affine.Set s)
	{
		this.s = s;
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
	public ICollidable intersect(ICollidable c)
	{
		Point[] pts = new Point[0];
		for(Point p : s)
		{
			if(c.contains(s))
			{
				pts = Array.add.to(pts, p);
			}
		}
		
		return ASpaces.set(pts);
	}
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof Affine.Set)
		{
			Affine.Set t = (Affine.Set) c;
			
			
			boolean[] list = new boolean[t.Size()];
			// Check if s1 is contained in s2.
			for(Point p : s)
			{
				int index = index(t, p, ulps);
				if(index == -1)
				{
					return false;
				}
				
				list[index] = true;
			}
			
			int index = 0;
			// Check if s2 is contained in s1.
			for(Point p : t)
			{
				// Skip the ones already found.
				if(list[index])
				{
					continue;
				}
				
				index++;
				if(index(s, p, ulps) == -1)
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean intersects(ICollidable c)
	{
		for(Point p : s)
		{
			if(c.contains(p))
			{
				return true;
			}
		}

		return false;
	}

	
	@Override
	public Boolean inhabits(ICollidable c)
	{
		for(Point p : s)
		{
			if(!c.contains(p))
			{
				return false;
			}
		}

		return true;
	}
	
	@Override
	public Boolean contains(ICollidable c)
	{
		if(c instanceof Affine.Set)
		{
			Affine.Set t = (Affine.Set) c;
			
			
			for(Point p : t)
			{
				if(!t.contains(p))
				{
					return false;
				}
			}

			return true;
		}
		
		return false;
	}
}