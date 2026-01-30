package waffles.utils.geom.owners.collision.response;

import java.util.Iterator;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.convex.hulls.Hull.Factory;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code CNTHull} computes a containment {@code Response} between a geometrical and a convex hull.
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
	private Geometrical src;
	private Iterator<Point> pts;
	private Response rsp;
	
	/**
	 * Creates a new {@code CNTHull}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target hull
	 * 
	 * 
	 * @see Geometrical
	 * @see Hull
	 */
	public CNTHull(Geometrical s, Hull t)
	{
		pts = t.Points().iterator();
		
		Point o = t.Origin();
		rsp = s.contain(o);

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

		int dim = Dimension();
		return new Void(dim);
	}
	
	@Override
	public Geometrical Source()
	{
		return src;
	}

	@Override
	public boolean hasImpact()
	{
		while(pts.hasNext())
		{
			Point p = pts.next();
			rsp = tgt.contain(p);
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