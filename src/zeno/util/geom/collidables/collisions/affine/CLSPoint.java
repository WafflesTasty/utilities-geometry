package zeno.util.geom.collidables.collisions.affine;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.convex.CLSHull;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code CLSPoint} class defines collision for a {@link Point}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSHull
 */
public class CLSPoint extends CLSHull
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
		private Response rsp;
		
		/**
		 * Creates a new {@code RSPPoint}.
		 * 
		 * @param c  a target object
		 * 
		 * 
		 * @see ICollidable
		 */
		public RSPPoint(ICollidable c)
		{
			rsp = c.contain(Source());
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return rsp.isEmpty();
		}
		
		@Override
		public Vector Penetration()
		{
			return rsp.Penetration().times(-1f);
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
			{
				return Geometries.VOID;
			}
			
			return Source();
		}

		@Override
		public Vector Distance()
		{
			return rsp.Distance().times(-1f);
		}
	}
	
	
	/**
	 * Creates a new {@code CLSPoint}.
	 * 
	 * @param p  a source point
	 * 
	 * 
	 * @see Point
	 */
	public CLSPoint(Point p)
	{
		super(p);
	}
	
	
	@Override
	public Response intersect(ICollidable c)
	{
		return new RSPPoint(c);
	}
					
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof Point)
		{
			Vector vp = Source().asVector();
			Vector vc = ((Point) c).asVector();	
			float norm = vp.minus(vc).normSqr();
			
			return Floats.isZero(norm, (4 + vp.Size()) / 2 * ulps);
		}

		return null;
	}

	@Override
	public Boolean intersects(ICollidable c)
	{
		return c.contains(Source());
	}
	
	@Override
	public Boolean contains(ICollidable c)
	{
		return equals(c, 2);
	}
	
	@Override
	protected Point Source()
	{
		return (Point) super.Source();
	}
}