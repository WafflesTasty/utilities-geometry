package waffles.utils.geom.response.hulls;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code INHConvex} computes an inhabiting response between a convex hull and a convex set.
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
	private Hull src;
	private ConvexSet tgt;
	private Response[] rsp;
	private Response rspv;
	
	/**
	 * Creates a new {@code INHConvex}.
	 * 
	 * @param s  a source hull
	 * @param t  a target convex
	 * 
	 * 
	 * @see ConvexSet
	 * @see Hull
	 */
	public INHConvex(Hull s, ConvexSet t)
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
		Matrix m = src.Generator();
		int cols = m.Columns();
		
		rsp = new Response[cols];
		for(int i = 0; i < cols;  i++)
		{
			Vector v = m.Column(i);
			Point p = new Point(v, 1f);
			rsp[i] = c.contain(p);
		}
		
		return rsp;
	}
}