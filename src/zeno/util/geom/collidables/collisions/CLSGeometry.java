package zeno.util.geom.collidables.collisions;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

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
	 * @param src  a source geometry
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
			Point p = new Point(src.Center(), 1f);
			return c.equals(p, 1);
		}
		
		// Eliminate point containment.
		if(c instanceof Point)
		{
			return contains((Point) c);
		}
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			return c.equals(Geometries.VOID);
		}
		
		return null;
	}
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		// Eliminate degenerate geometry.
		if(isDegenerate())
		{
			Point p = new Point(src.Center(), 1f);
			return c.equals(p, ulps);
		}
		
		// Eliminate point equality.
		if(c instanceof Point)
		{
			return false;
		}
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
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
			if(c.contains(src.Center()))
			{
				return new Point(src.Center(), 1f);
			}
			
			return Geometries.VOID;
		}
		
		// Eliminate point intersection.
		if(c instanceof Point)
		{
			if(!contains((Point) c))
			{
				return Geometries.VOID;
			}
			
			return c;
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(isDegenerate())
		{
			return c.contains(src.Center());
		}
		
		// Eliminate point intersection.
		if(c instanceof Point)
		{
			return contains((Point) c);
		}
		
		return null;
	}
		
	@Override
	public Boolean inhabits(ICollidable c)
	{
		// Eliminate degenerate geometry.
		if(isDegenerate())
		{
			return c.contains(src.Center());
		}
		
		return null;
	}
}