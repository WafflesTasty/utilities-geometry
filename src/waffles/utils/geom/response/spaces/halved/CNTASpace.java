package waffles.utils.geom.response.spaces.halved;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.HSpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code CNTASpace} computes the containment response between a halfspace and an affine space.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTASpace implements Response
{
	private ASpace tgt;
	private HSpace src;
	
	private Response rsp;
	
	/**
	 * Creates a new {@code CNTVSpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see HSpace
	 * @see ASpace
	 */
	public CNTASpace(HSpace s, ASpace t)
	{
		src = s;
		tgt = t;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
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
		
		return rsp.Penetration();
	}

	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return rsp.Distance();
	}
	
	@Override
	public int Cost()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return rsp.Cost();
	}


	Response computeResponse()
	{		
		Vector n = src.Normal();
		VSpace s = tgt.Direction();
		
		Vector o = src.Origin().Generator();
		Vector p = tgt.Origin().Generator();
		
		
		Point q = new Point(o.minus(p), 1f);
		HSpace h = new HSpace(q, n);
		return h.contain(s);
	}
}