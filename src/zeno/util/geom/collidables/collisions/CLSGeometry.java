package zeno.util.geom.collidables.collisions;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;
import zeno.util.tools.Floats;
import zeno.util.tools.helper.Array;

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
public abstract class CLSGeometry implements ICollision
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
		
	
	protected abstract boolean contains(Point p);
	
	protected IGeometry Source()
	{
		return src;
	}
	
	boolean isDegenerate()
	{
		return Floats.isZero(src.Size().norm(), src.Dimension());
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(isDegenerate())
		{
			Point p = new Point(src.Center());
			return p.equals(c, 1);
		}
		
		// Eliminate point containment.
		if(c instanceof Point)
		{
			return contains((Point) c);
		}
		
		// Eliminate point set containment.
		if(c instanceof Affine.Set)
		{
			for(Point p : ((Affine.Set) c).Span())
			{
				if(!contains(p))
				{
					return false;
				}
			}
			
			return true;
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
		if(isDegenerate())
		{
			Point p = new Point(src.Center());
			return p.equals(c, ulps);
		}
		
		// Eliminate point equality.
		if(c instanceof Point)
		{
			return false;
		}
		
		// Eliminate affine spaces.
		if(c instanceof Affine.Space)
		{
			return false;
		}
		
		return null;
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(isDegenerate())
		{
			Point p = new Point(src.Center());
			if(!c.contains(p))
			{
				return new TrivialASpace();
			}
			
			return p;
		}
		
		// Eliminate point intersection.
		if(c instanceof Point)
		{
			if(!contains((Point) c))
			{
				return new TrivialASpace();
			}
			
			return c;
		}
		
		// Eliminate point set intersection.
		if(c instanceof Affine.Set)
		{
			Point[] pts = new Point[0];
			for(Point p : ((Affine.Set) c).Span())
			{
				if(contains(p))
				{
					pts = Array.add.to(pts, p);
				}
			}
			
			return ASpaces.set(pts);
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(isDegenerate())
		{
			Point p = new Point(src.Center());
			return c.contains(p);
		}
		
		// Eliminate point intersection.
		if(c instanceof Point)
		{
			return contains((Point) c);
		}
		
		// Eliminate point set intersection.
		if(c instanceof Affine.Set)
		{
			for(Point p : ((Affine.Set) c).Span())
			{
				if(contains(p))
				{
					return true;
				}
			}
			
			return false;
		}
		
		return null;
	}
		
	@Override
	public Boolean inhabits(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(isDegenerate())
		{
			Point p = new Point(src.Center());
			return c.contains(p);
		}
		
		return null;
	}
}