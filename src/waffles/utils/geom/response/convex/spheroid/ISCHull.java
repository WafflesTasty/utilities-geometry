package waffles.utils.geom.response.convex.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.ISphere;
import waffles.utils.geom.collidable.axial.spheroid.ISpheroid;
import waffles.utils.geom.collidable.convex.hulls.IHull;
import waffles.utils.geom.spatial.maps.spatial.StandardMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.geom.utilities.Transforms;

/**
 * An {@code ISCHull} computes the collision response from an ellipsoid to a convex hull.
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
	private ISpheroid src;
	private StandardMap map;
	
	/**
	 * Creates a new {@code ISCHull}.
	 * 
	 * @param s  a source ellipsoid
	 * @param t  a target hull
	 * 
	 * 
	 * @see ISpheroid
	 * @see IHull
	 */
	public ISCHull(ISpheroid s, IHull t)
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
		
		return (Vector) map.map(rsp.Penetration());
	}

	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
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
		int dim = src.Dimension();
		
		map = Transforms.fromUSphere(src);
		ISphere s = Geometries.Sphere(dim);
		IHull h = (IHull) map.unmap(tgt);
		
		return s.intersect(h);
	}
}