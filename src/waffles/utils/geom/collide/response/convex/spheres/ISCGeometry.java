package waffles.utils.geom.collide.response.convex.spheres;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code ISCGeometry} computes an intersection {@code Response} between a sphere and a geometry.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCGeometry implements Response
{
	private Response rsp;
	private HyperSphere src;

	/**
	 * Creates a new {@code ISCGeometry}.
	 *
	 * @param s  a source sphere
	 * @param t  a target geometry
	 *
	 *
	 * @see HyperSphere
	 * @see Geometry
	 */
	public ISCGeometry(HyperSphere s, Geometry t)
	{
		rsp = t.contain(s.Origin());
		src = s;
	}
	
	
	@Override
	public boolean hasImpact()
	{
		if(!rsp.hasImpact())
		{
			float r = src.Radius();
			Vector dst = rsp.Distance();

			float n = dst.normSqr();
			return n <= r * r;			
		}
		
		return true;
	}
	
	@Override
	public HyperSphere Source()
	{
		return src;
	}

	@Override
	public Vector Penetration()
	{
		if(rsp.hasImpact())
		{
			Vector pnt = rsp.Penetration();

			float r = src.Radius();
			float p = pnt.norm();
			
			return pnt.times(-(p + r) / p);
		}
		
		if(hasImpact())
		{
			Vector dst = rsp.Distance();

			float r = src.Radius();
			float d = dst.norm();

			return dst.times((r - d) / d);
		}
		
		int n = Dimension();
		return Vectors.create(n);
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
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
		Point cnt = src.Origin();
		if(rsp.hasImpact())
		{
			return cnt;
		}
		
		if(hasImpact())
		{
			Vector pnt = Penetration();
			return cnt.plus(pnt);			
		}

		int n = Dimension();
		return new Point(n);
	}

	@Override
	public int cost()
	{
		return 2 * Dimension() + rsp.cost();
	}
}