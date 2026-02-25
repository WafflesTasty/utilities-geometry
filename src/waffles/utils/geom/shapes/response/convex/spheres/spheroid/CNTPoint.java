package waffles.utils.geom.shapes.response.convex.spheres.spheroid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.global.fixed.AxisMap;

/**
 * An {@code CNTPoint} computes a containment {@code Response} between a spheroid and a point.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class CNTPoint implements Response
{
	private AxisMap map;
	private HyperSpheroid src;
	private Response rsp;
	private Point tgt;

	/**
	 * Creates a new {@code CNTPoint}.
	 *
	 * @param s  a source spheroid
	 * @param t  a target point
	 *
	 *
	 * @see HyperSpheroid
	 * @see Point
	 */
	public CNTPoint(HyperSpheroid s, Point t)
	{
		int dim = s.Dimension();
		
		map = new AxisMap(s);
		HyperSphere u = HyperSphere.unit(dim);
		rsp = u.contain((Point) map.unmap(t));

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
	public boolean hasImpact()
	{
		return rsp.hasImpact();
	}
	
	@Override
	public HyperSpheroid Source()
	{
		return src;
	}

	@Override
	public Vector Penetration()
	{
		Vector v = rsp.Penetration();

		Point pnt = new Arrow(v);
		pnt = (Point) map.map(pnt);
		return pnt.Vector();
	}

	@Override
	public Vector Distance()
	{
		Vector v = rsp.Distance();
		System.out.print("OG distance: " + v.norm());
		Point dst = new Arrow(v);
		dst = (Point) map.map(dst);
		return dst.Vector();
	}

	@Override
	public Point Contact()
	{
		return tgt;
	}

	@Override
	public int cost()
	{
		int n = Dimension();
		return 4 * n * n * (n - 1)
			 * rsp.cost();
	}
}