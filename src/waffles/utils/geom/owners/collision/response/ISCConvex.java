package waffles.utils.geom.owners.collision.response;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.convex.ConjugateSet;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code ISCConvex} computes an intersection {@code Response} between a geometrical and a convex set.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class ISCConvex implements Response
{
	private Response rsp;
	private Geometrical src;
	private LinearMap map;

	/**
	 * Creates a new {@code ISCConvex}.
	 *
	 * @param s  a source spheroid
	 * @param t  a target set
	 *
	 *
	 * @see Geometrical
	 * @see ConvexSet
	 */
	public ISCConvex(Geometrical s, ConvexSet t)
	{
		int n = s.Dimension();
		
		map = s.Transform();
		ConvexSet r = new ConjugateSet(t, map);
		rsp = s.Shape().intersect(r);
		src = s;
	}

	
	@Override
	public boolean hasImpact()
	{
		return rsp.hasImpact();
	}
	
	@Override
	public Geometrical Source()
	{
		return src;
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			Vector p = rsp.Penetration();
			p = (Vector) map.map(p);
			return p;			
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector d = rsp.Distance();
			d = (Vector) map.map(d);
			return d;			
		}
		
		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Point Contact()
	{
		Point c = rsp.Contact();
		if(hasImpact())
		{
			c = (Point) map.map(c);
		}
		
		return c;
	}

	@Override
	public int cost()
	{
		int n = Dimension();
		return 4 * n * n * (n - 1)
			 + rsp.cost();
	}
}