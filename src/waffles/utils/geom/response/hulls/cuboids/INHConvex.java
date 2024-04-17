package waffles.utils.geom.response.hulls.cuboids;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code INHConvex} computes an inhabiting response between a cuboid and a convex set.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class INHConvex implements Response
{
	private HyperCuboid src;
	private ConvexSet tgt;
	
	private Response[] rsp;
	private Response rspv;
	
	/**
	 * Creates a new {@code INHConvex}.
	 * 
	 * @param s  a source cuboid
	 * @param t  a target convex
	 * 
	 * 
	 * @see ConvexSet
	 * @see HyperCuboid
	 */
	public INHConvex(HyperCuboid s, ConvexSet t)
	{
		src = s;
		tgt = t;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return src;
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
		
		for(Response r : rsp)
		{
			if(!r.hasImpact())
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Vector Penetration()
	{
		if(rspv == null)
		{
			rspv = computeVectors();
		}
		
		return rspv.Penetration();
	}
	
	@Override
	public Vector Distance()
	{
		if(rspv == null)
		{
			rspv = computeVectors();
		}
		
		return rspv.Distance();
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
		
		int cost = 0;
		for(Response r : rsp)
		{
			cost += r.Cost();
		}
		
		return cost;
	}
	
	
	Response computeVectors()
	{
		Collision cls = src.Collisions();
		return cls.intersect(tgt);
	}

	Response[] computeResponse()
	{
		Collision c = tgt.Collisions();
		
		Vector vMin = src.Bounds().Minimum();
		Vector vMax = src.Bounds().Maximum();

		
		rsp = new Response[2];
		rsp[0] = c.contain(new Point(vMin, 1f));
		rsp[1] = c.contain(new Point(vMax, 1f));
		
		return rsp;
	}
}