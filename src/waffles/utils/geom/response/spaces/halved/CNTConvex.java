package waffles.utils.geom.response.spaces.halved;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.HSpace;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code CNTConvex} computes the containment response between a halfspace and a convex set.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTConvex implements Response
{
	private HSpace src;
	private ConvexSet tgt;
	
	private Response rsp1, rsp2;
	
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
	public CNTConvex(HSpace s, ConvexSet t)
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
		if(rsp1 == null)
		{
			rsp1 = computeResponse();
		}
		
		return rsp2.hasImpact();
	}
	
	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			return rsp1.Distance();
		}

		return null;
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return rsp2.Distance();
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
		if(rsp1 == null)
		{
			rsp1 = computeResponse();
		}
		
		return rsp1.Cost() + rsp2.Cost();
	}
	
		
	Response computeResponse()
	{
		Vector n = src.Normal();
		Vector e = tgt.Extremum().along(n);
		rsp1 = src.contain(e);
		
		n = n.times(-1f);
		e = tgt.Extremum().along(n);
		rsp2 = src.contain(e);
		return rsp1;
	}
}