package waffles.utils.geom.shapes.response.linear.halved;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.ConvexSet.Extremum;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;

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
	private Response rsp;
	private ConvexSet tgt;
	
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
		src = s; tgt = t;
		Vector v = s.Normal();
		Extremum e = t.Extremum();
		rsp = s.contain(e.along(v));
	}

	
	@Override
	public HSpace Source()
	{
		return src;
	}
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public boolean hasImpact()
	{			
		return rsp.hasImpact();
	}
	
	@Override
	public Vector Penetration()
	{
		return rsp.Penetration();
	}

	@Override
	public Vector Distance()
	{
		return rsp.Distance();
	}
	
	@Override
	public Point Contact()
	{
		return rsp.Contact();
	}
	
	@Override
	public int cost()
	{
		return rsp.cost();
	}
}