package waffles.utils.geom.shapes.response.convex.hulls;

import java.util.Iterator;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.convex.hulls.Hull.Factory;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code IHBGeometry} computes a habitation {@code Response} between a convex hull and a geometry.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 *
 *
 * @see Response
 */
public class IHBGeometry implements Response
{
	private Hull src;
	private Geometry tgt;
	private Iterator<Point> pts;
	private Response rsp;
	
	/**
	 * Creates a new {@code IHBGeometry}.
	 * 
	 * @param s  a source hull
	 * @param t  a target geometry
	 * 
	 * 
	 * @see Geometry
	 * @see Hull
	 */
	public IHBGeometry(Hull s, Geometry t)
	{
		pts = s.Points().iterator();
		
		Point o = s.Origin();
		rsp = t.contain(o);

		src = s;
		tgt = t;
	}


	@Override
	public Hull Source()
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
		Factory fc = src.Factory();
		return fc.Point(0);
	}

	@Override
	public int cost()
	{
		Factory fct = src.Factory();
		
		int p = fct.Count();
		int c = rsp.cost();
		
		return c * p;
	}
}