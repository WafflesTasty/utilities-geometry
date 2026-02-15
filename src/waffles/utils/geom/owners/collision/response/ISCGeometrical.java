package waffles.utils.geom.owners.collision.response;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.ConjugateSet;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.tform.linear.LinearCompose;
import waffles.utils.geom.utilities.tform.linear.LinearInverse;

/**
 * An {@code ISCGeometrical} computes an intersection {@code Response} between geometrical objects.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class ISCGeometrical implements Response
{
	private Response rsp;
	private Geometrical src;
	private LinearMap map;

	/**
	 * Creates a new {@code ISCGeometrical}.
	 *
	 * @param s  a source spheroid
	 * @param t  a target set
	 *
	 *
	 * @see Geometrical
	 */
	public ISCGeometrical(Geometrical s, Geometrical t)
	{
		int n = s.Dimension();
		
		LinearMap m1 = s.Transform();
		LinearMap m2 = t.Transform();
		
		Geometry g = s.Shape();
		Geometry h = t.Shape();
		
		map = new LinearInverse(m2);
		map = new LinearCompose(map, m1);
		h = new ConjugateSet((ConvexSet) h, map);
		rsp = s.Shape().intersect(h);
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