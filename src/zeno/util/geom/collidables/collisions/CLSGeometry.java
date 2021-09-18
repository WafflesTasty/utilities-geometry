package zeno.util.geom.collidables.collisions;

import zeno.util.algebra.linear.vector.Vector;
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
	/**
	 * The {@code RSPPoint} class defines collision response for a point.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPPoint implements Response
	{
		private Point point;
		private Response rsp;
		
		/**
		 * Creates a new {@code RSPPoint}.
		 * 
		 * @param p  a target point
		 * 
		 * 
		 * @see Point
		 */
		public RSPPoint(Point p)
		{
			point = p;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Response().isEmpty();
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
			{
				return Geometries.VOID;
			}
			
			return point;
		}

		@Override
		public Vector Penetration()
		{
			return Response().Penetration();
		}
		
		@Override
		public Vector Distance()
		{
			return Response().Distance();
		}
		
		Response Response()
		{
			if(rsp == null)
			{
				rsp = contain(point);
			}
			
			return rsp;
		}
	}
	
	
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

	
	@Override
	public Boolean contains(ICollidable c)
	{
		// Eliminate void geometry.
		if(c.equals(Geometries.VOID))
		{
			return true;
		}
				
		// Eliminate point containment.
		if(c instanceof Point)
		{
			return !contain((Point) c).isEmpty();
		}
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			return c.equals(Geometries.VOID);
		}
		
		return null;
	}
	
	@Override
	public Response intersect(ICollidable c)
	{
		// Eliminate void geometry.
		if(c.equals(Geometries.VOID))
		{
			return c.intersect(Source());
		}
		
		// Eliminate point intersection.
		if(c instanceof Point)
		{
			return contain((Point) c);
		}
		
		return null;
	}
			
	@Override
	public Boolean intersects(ICollidable c)
	{
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
		return null;
	}

	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
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
	
	protected IGeometry Source()
	{
		return src;
	}
	
	boolean isDegenerate()
	{
		return Floats.isZero(src.Size().norm(), src.Dimension());
	}
}