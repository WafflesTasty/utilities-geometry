package waffles.utils.geom.shapes.response.convex;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.ConvexSet.Extremum;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.convex.MinkowskiSet;

/**
 * An {@code ISCConvex} computes an intersection {@code Response} between convex sets.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
@Deprecated
public class ISCConvex implements Response
{
	private Response rsp;
	private ConvexSet src;
	
	/**
	 * Creates a new {@code ISCConvex}.
	 * 
	 * @param s  a source convex
	 * @param t  a target convex
	 * 
	 * 
	 * @see ConvexSet
	 */
	public ISCConvex(ConvexSet s, ConvexSet t)
	{
		src = s;
//		int dim = s.Dimension();
//		ConvexSet diff = new MinkowskiSet(s, t);
//		rsp = diff.contain(new Point(dim));
		rsp = () -> s;
	}

	
	@Override
	public ConvexSet Source()
	{
		return src;
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
		Extremum e = src.Extremum();

		Vector v1 = Penetration();
		Vector v2 = v1.times(-1f);
		
		Point p = e.along(v2);
		return p.plus(v1);
	}

	@Override
	public int cost()
	{		
		return rsp.cost();
	}
}