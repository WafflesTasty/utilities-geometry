package waffles.utils.geom.collide.response.convex;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.ConvexSet.Extremum;
import waffles.utils.geom.shapes.convex.hulls.line.Segment;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code ISCHLine} computes an intersection {@code Response} between a convex set and a halfline.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCHLine implements Response
{
	private Point e;
	private HLine tgt;
	private ConvexSet src;
	private Response rsp1, rsp2;
	
	/**
	 * Creates a new {@code ISCHLine}.
	 * 
	 * @param s  a source convex
	 * @param t  a target line
	 * 
	 * 
	 * @see HLine
	 */
	public ISCHLine(ConvexSet s, HLine t)
	{
		Extremum ext = s.Extremum();
		e = ext.along(t.Direction());
		rsp1 = t.contain(e);
		
		src = s;
		tgt = t;
	}

	
	Response compute()
	{
		Point q = e;
		Point p = tgt.Origin();
		if(!rsp1.hasImpact())
		{
			q = q.plus(rsp1.Distance());
		}
		
		Segment s = Segment.create(p, q);
		return src.intersect(s);
	}
	
	@Override
	public ConvexSet Source()
	{
		return src;
	}

	@Override
	public boolean hasImpact()
	{
		if(rsp2 == null)
		{
			rsp2 = compute();
		}
		
		return rsp2.hasImpact();
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			return rsp2.Penetration();
		}
		
		int dim = Dimension();
		return Vectors.create(dim);
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return rsp2.Distance();
		}
		
		int dim = Dimension();
		return Vectors.create(dim);
	}
	
	@Override
	public Point Contact()
	{
		if(hasImpact())
		{
			return rsp2.Contact();
		}
		
		int dim = Dimension();
		return new Point(dim);
	}

	@Override
	public int cost()
	{		
		return 2 * rsp1.cost();
	}
}