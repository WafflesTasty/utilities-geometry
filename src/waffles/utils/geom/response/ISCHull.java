package waffles.utils.geom.response;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCHull} computes the collision response from a geometrical object to a convex hull.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class ISCHull implements Response
{
	private Hull tgt;
	private Response rsp;
	private Geometrical src;
	
	/**
	 * Creates a new {@code ISCHull}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target hull
	 * 
	 * 
	 * @see Geometrical
	 * @see Hull
	 */
	public ISCHull(Geometrical s, Hull t)
	{
		src = s;
		tgt = t;
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return null;
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{			
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return rsp.hasImpact();
	}
	
	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			Vector pnt = rsp.Penetration();
			GlobalMap map = src.Transform();
			return (Vector) map.map(pnt);
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector dst = rsp.Distance();
			GlobalMap map = src.Transform();
			return (Vector) map.map(dst);
		}

		return null;
	}
	
	@Override
	public Point Contact()
	{
		Point cnt = rsp.Contact();
		if(cnt != null)
		{
			GlobalMap map = src.Transform();
			cnt = (Point) map.map(cnt);
			return cnt;
		}

		return null;
	}
	
	@Override
	public int Cost()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim * (dim - 1);
	}
	
	
	Response computeResponse()
	{
		Geometry s = src.Shape();
		GlobalMap map = src.Transform();
		
		Hull h = (Hull) map.unmap(tgt);
		return s.intersect(h);
	}
}