package waffles.utils.geom.response.spaces.halved;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.HSpace;

/**
 * An {@code ISCConvex} computes the intersection response between a halfspace and a convex set.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCConvex implements Response
{
	private HSpace src;
	private ConvexSet tgt;
	private Response rsp;
	
	/**
	 * Creates a new {@code CNTConvex}.
	 * 
	 * @param s  a source space
	 * @param t  a target convex
	 * 
	 * 
	 * @see ConvexSet
	 * @see HSpace
	 */
	public ISCConvex(HSpace s, ConvexSet t)
	{
		src = s;
		tgt = t;
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
			return rsp.Penetration();	
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return rsp.Distance();
		}

		return null;
	}
	
	@Override
	public Point Contact()
	{
		return null;
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
		Vector e = tgt.Extremum().along(n);
		return src.contain(e);
	}
}