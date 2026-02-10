package waffles.utils.geom.shapes.response.linear.halved.line;

import java.util.Iterator;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.convex.hulls.Hull.Factory;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code CNTHull} computes a containment {@code Response} between a halfline and a convex hull.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 *
 *
 * @see Response
 */
public class CNTHull implements Response
{
	private Hull tgt;
	private HLine src;
	
	private Response rsp;
	private Iterator<Point> pts;
	
	/**
	 * Creates a new {@code CNTHull}.
	 * 
	 * @param s  a source line
	 * @param t  a target hull
	 * 
	 * 
	 * @see HLine
	 * @see Hull
	 */
	public CNTHull(HLine s, Hull t)
	{
		pts = t.Points().iterator();
		
		Point o = t.Origin();
		rsp = s.contain(o);
		
		src = s;
		tgt = t;
	}

	
	@Override
	public HLine Source()
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

		int dim = Dimension();
		return new Void(dim);
	}

	@Override
	public boolean hasImpact()
	{
		while(pts.hasNext())
		{
			Point p = pts.next();
			rsp = src.contain(p);
			if(!rsp.hasImpact())
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Point Contact()
	{
		Factory fc = tgt.Factory();
		return fc.Point(0);
	}

	@Override
	public int cost()
	{
		Factory fct = tgt.Factory();
		
		int p = fct.Count();
		int c = rsp.cost();
		
		return c * p;
	}
}