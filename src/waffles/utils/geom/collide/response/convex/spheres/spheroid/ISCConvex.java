package waffles.utils.geom.collide.response.convex.spheres.spheroid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.ConjugateSet;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.global.fixed.AxisMap;

/**
 * An {@code ISCConvex} computes an intersection {@code Response} between a spheroid and a convex set.
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
	private HyperSpheroid src;
	private AxisMap map;

	/**
	 * Creates a new {@code ISCConvex}.
	 *
	 * @param s  a source spheroid
	 * @param t  a target set
	 *
	 *
	 * @see HyperSpheroid
	 * @see ConvexSet
	 */
	public ISCConvex(HyperSpheroid s, ConvexSet t)
	{
		int dim = s.Dimension();

		map = new AxisMap(s);
		HyperSphere u = HyperSphere.unit(dim);
		ConvexSet r = new ConjugateSet(t, map);
		rsp = u.intersect(r);
		src = s;
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
		if(hasImpact())
		{
			Vector pnt = rsp.Penetration();
			pnt = (Vector) map.map(pnt);
			return pnt;			
		}

		int dim = Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector dst = rsp.Distance();
			dst = (Vector) map.map(dst);
			return dst;			
		}
		
		int dim = Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Point Contact()
	{
		return src.Origin();
	}

	@Override
	public int cost()
	{
		int n = Dimension();
		return 4 * n * n * (n - 1)
			 + rsp.cost();
	}
}