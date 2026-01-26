package waffles.utils.geom.collide.response.linear.halved.line;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
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
	private HLine src;
	private Response[] rsp;
	private Hull tgt;
	
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
		Factory fc = t.Factory();
		rsp = new Response[fc.Count()];
		for(int k = 0; k < fc.Count(); k++)
		{
			Point p = fc.Point(k);
			rsp[k] = s.contain(p);
		}
		
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
	public Point Contact()
	{
		Factory fc = tgt.Factory();
		return fc.Point(0);
	}

	@Override
	public int cost()
	{
		return rsp[0].cost() * rsp.length;
	}
}