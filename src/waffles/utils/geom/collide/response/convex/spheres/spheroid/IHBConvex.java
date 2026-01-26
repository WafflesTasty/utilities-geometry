package waffles.utils.geom.collide.response.convex.spheres.spheroid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.ConjugateSet;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.global.fixed.AxisMap;

/**
 * An {@code IHBConvex} computes a habitation {@code Response} between a spheroid and a convex set.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class IHBConvex implements Response
{
	private Response rsp;
	private HyperSpheroid src;
	private AxisMap map;

	/**
	 * Creates a new {@code IHBConvex}.
	 *
	 * @param s  a source spheroid
	 * @param t  a target set
	 *
	 *
	 * @see HyperSpheroid
	 * @see ConvexSet
	 */
	public IHBConvex(HyperSpheroid s, ConvexSet t)
	{
		int dim = s.Dimension();

		map = new AxisMap(s);
		HyperSphere u = HyperSphere.unit(dim);
		ConvexSet r = new ConjugateSet(t, map);
		rsp = r.contain(u);
		src = s;
	}


	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return src;
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
		if(hasImpact())
		{
			Vector pnt = rsp.Penetration();
			pnt = (Vector) map.map(pnt);
			return pnt.times(-1f);			
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
			return dst.times(-1f);			
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