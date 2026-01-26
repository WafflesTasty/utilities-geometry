package waffles.utils.geom.collide.response.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.Geometry;
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
	private Response[] rsp;
	private Geometry tgt;
	
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
		Factory fc = s.Factory();
		rsp = new Response[fc.Count()];
		for(int k = 0; k < fc.Count(); k++)
		{
			Point p = fc.Point(k);
			rsp[k] = t.contain(p);
		}
		
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
		Factory fc = src.Factory();
		return fc.Point(0);
	}

	@Override
	public int cost()
	{
		return rsp[0].cost() * rsp.length;
	}
}