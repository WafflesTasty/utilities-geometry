package waffles.utils.geom.shapes.response.convex.spheres.spheroid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.global.fixed.AxisMap;

/**
 * An {@code ISCASpace} computes an intersection {@code Response} between a spheroid and an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class ISCASpace implements Response
{
	private Response rsp;
	private HyperSpheroid src;
	private AxisMap map;
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source ellipsoid
	 * @param t  a target space
	 * 
	 * 
	 * @see HyperSpheroid
	 * @see ASpace
	 */
	public ISCASpace(HyperSpheroid s, ASpace t)
	{
		int dim = s.Dimension();
		
		map = new AxisMap(s);
		HyperSphere u = HyperSphere.unit(dim);
		ASpace r = (ASpace) map.unmap(t);
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
			return (Vector) map.map(pnt);
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
			return (Vector) map.map(dst);
		}
		
		int dim = Dimension();
		return Vectors.create(dim);
	}
	
	@Override
	public Point Contact()
	{
		Point c = rsp.Contact();
		if(c != null)
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