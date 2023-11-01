package waffles.utils.geom.response;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCASpace} computes the collision response from a geometrical object to an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCASpace implements Response
{
	private ASpace tgt;
	private Response rsp;
	private Geometrical src;
	
	/**
	 * Creates a new {@code ISCHull}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target space
	 * 
	 * 
	 * @see Geometrical
	 * @see ASpace
	 */
	public ISCASpace(Geometrical s, ASpace t)
	{
		src = s;
		tgt = t;
	}
	
	/**
	 * Creates a new {@code ISCHull}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target space
	 * 
	 * 
	 * @see Geometrical
	 * @see VSpace
	 */
	public ISCASpace(Geometrical s, VSpace t)
	{
		this(s, ASpace.Default(t));
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
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim * (dim - 1);
	}
	
	
	Response computeResponse()
	{
		Geometry s = src.Shape();
		GlobalMap map = src.Transform();
		ASpace h = (ASpace) map.unmap(tgt);
		
		return s.intersect(h);
	}
}