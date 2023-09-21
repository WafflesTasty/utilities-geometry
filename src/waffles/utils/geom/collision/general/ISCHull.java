package waffles.utils.geom.collision.general;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.hulls.IHull;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCHull} computes the collision response from a geometrical object to a convex hull.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCHull implements Response
{
	private IHull tgt;
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
	 * @see IHull
	 */
	public ISCHull(Geometrical s, IHull t)
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
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		GlobalMap map = src.Transform();
		return (Vector) map.map(rsp.Penetration());
	}

	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		GlobalMap map = src.Transform();
		return (Vector) map.map(rsp.Distance());
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
		
		IHull h = (IHull) map.unmap(tgt);
		return s.intersect(h);
	}
}