package waffles.utils.geom.collide.response.convex.spheres;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code IHBGeometry} computes a habitation {@code Response} between a sphere and a geometry.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class IHBGeometry implements Response
{
	private Response rsp;
	private HyperSphere src;

	/**
	 * Creates a new {@code IHBGeometry}.
	 *
	 * @param s  a source sphere
	 * @param t  a target geometry
	 *
	 *
	 * @see HyperSphere
	 * @see Geometry
	 */
	public IHBGeometry(HyperSphere s, Geometry t)
	{
		rsp = t.contain(s.Origin());
		src = s;
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return src;
		}

		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public boolean hasImpact()
	{
		float r = src.Radius();
		Vector pnt = rsp.Penetration();
		float n = pnt.normSqr();
		return n > r * r;
	}
	
	@Override
	public HyperSphere Source()
	{
		return src;
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			Vector pnt = rsp.Penetration();
			
			float r = src.Radius();
			float d = pnt.norm();
			
			return pnt.times((d - r) / d);
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Point Contact()
	{
		return src.Origin();
	}

	@Override
	public int cost()
	{
		return 2 * Dimension() + rsp.cost();
	}
}