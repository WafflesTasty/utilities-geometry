package zeno.util.geom.collidables.collisions;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;

/**
 * The {@code CLSGeometry} class defines collision for an {@link IGeometry}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSGeometry implements ICollision
{
	private IGeometry src;
	
	/**
	 * Creates a new {@code CLSGeometry}.
	 * 
	 * @param src  a source geometry$
	 * 
	 * 
	 * @see IGeometry
	 */
	public CLSGeometry(IGeometry src)
	{
		this.src = src;
	}
	
	protected IGeometry Source()
	{
		return src;
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(src.isPoint())
		{
			Point p = new Point(src.Center());
			return p.equals(c, 1);
		}
		
		// Eliminate affine spaces.
		if(c instanceof Affine.Space)
		{
			return c.isEmpty();
		}
		
		return null;
	}
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		// Eliminate degenerate geometry.
		if(src.isPoint())
		{
			Point p = new Point(src.Center());
			return p.equals(c, ulps);
		}
		
		return null;
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(src.isPoint())
		{
			Point p = new Point(src.Center());
			if(!c.contains(p))
			{
				return new TrivialASpace();
			}
			
			return p;
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(src.isPoint())
		{
			Point p = new Point(src.Center());
			return c.contains(p);
		}
		
		return null;
	}
		
	@Override
	public Boolean inhabits(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(src.isPoint())
		{
			Point p = new Point(src.Center());
			return c.contains(p);
		}
		
		// Eliminate isolated point sets.
		if(c instanceof Affine.Set)
		{
			return false;
		}
		
		// Eliminate affine spaces.
		if(c instanceof Affine.Space)
		{
			Vector v1 = src.Minimum();
			Vector v2 = src.Maximum();
			
			return c.contains(v1)
				&& c.contains(v2);
		}
		
		return null;
	}
}