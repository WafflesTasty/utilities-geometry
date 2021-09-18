package zeno.util.geom.collidables.collisions.convex;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.AffineMap;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.IHull;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.utilities.Geometries;
import zeno.util.geom.utilities.Transforms;

/**
 * The {@code CLSHull} class defines collision for an {@code IHull}.
 * </br> It extends most algorithms from the {@code CLSConvex} parent class.
 * 
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see CLSConvex
 */
public class CLSHull extends CLSConvex
{
	/**
	 * The {@code RSPEllipsoid} class defines collision response for ellipsoids.
	 *
	 * @author Waffles
	 * @since 13 May 2021
	 * @version 1.0
	 */
	public class RSPEllipsoid implements Response
	{
		private Response rsp;
		private AffineMap map;
		
		/**
		 * Creates a new {@code RSPEllipsoid}.
		 * 
		 * @param e  a target ellipsoid
		 * 
		 * 
		 * @see IEllipsoid
		 */
		public RSPEllipsoid(IEllipsoid e)
		{
			map = Transforms.elliptic(e);
			ISphere sphr = Geometries.sphere(e.Dimension());
			IHull hull = (IHull) map.unmap(Source());
			rsp = hull.intersect(sphr);
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return rsp.isEmpty();
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
				return Geometries.VOID;			
			return null;
		}

		@Override
		public Vector Penetration()
		{
			Vector pnt = rsp.Penetration();
			pnt = Vectors.resize(pnt, pnt.Size()+1);
			return (Vector) map.map(pnt);
		}
		
		@Override
		public Vector Distance()
		{			
			Vector dst = rsp.Distance();
			dst = Vectors.resize(dst, dst.Size()+1);
			return (Vector) map.map(dst);
		}
	}


	/**
	 * Creates a new {@code CLSHull}.
	 * 
	 * @param src  a source hull
	 * 
	 * 
	 * @see IHull
	 */
	public CLSHull(IHull src)
	{
		super(src);
	}
	

	@Override
	public Response intersect(ICollidable c)
	{
		// Eliminate spheroid geometry.
		if(c instanceof ISphere)
		{
			return super.intersect(c);
		}
		
		// Eliminate ellipsoid geometry.
		if(c instanceof IEllipsoid)
		{
			return new RSPEllipsoid((IEllipsoid) c);
		}

		return super.intersect(c);
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{		
		// Eliminate ellipsoid geometry.
		if(c instanceof IEllipsoid)
		{
			return !intersect(c).isEmpty();
		}
		
		return super.intersects(c);
	}

	@Override
	protected IHull Source()
	{
		return (IHull) super.Source();
	}
}