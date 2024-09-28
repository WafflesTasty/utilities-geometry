package waffles.utils.geom.response.convex.spheres;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geom.collidable.convex.ConvexSet.Extremum;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCASpace} computes the intersection from a sphere to an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCASpace implements Response
{
	private Vector dst, pnt;
	private HyperSphere src;
	private Response rsp;
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source sphere
	 * @param t  a target space
	 * 
	 * 
	 * @see HyperSphere
	 * @see ASpace
	 */
	public ISCASpace(HyperSphere s, ASpace t)
	{
		rsp = t.contain(s.Origin());
		src = s;
	}
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source sphere
	 * @param t  a target space
	 * 
	 * 
	 * @see HyperSphere
	 * @see VSpace
	 */
	public ISCASpace(HyperSphere s, VSpace t)
	{
		this(s, ASpace.Default(t));
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return null;
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{			
		if(rsp.hasImpact())
		{
			return true;
		}
		
		float r = src.Radius();
		Vector dst = rsp.Distance();
		float nsq = dst.normSqr();
		return nsq < r * r;
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			if(pnt == null)
			{
				pnt = computePenetration();
			}
						
			return pnt;
		}

		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			if(dst == null)
			{
				dst = computeDistance();
			}
			
			return dst;
		}
		
		return null;
	}
	
	@Override
	public Point Contact()
	{
		if(hasImpact())
		{
			Vector pnt = Penetration();
			Extremum ext = src.Extremum();
			Vector v = ext.along(pnt.times(-1f));
			return new Point(v.plus(pnt), 1f);
		}
		
		Vector dst = Distance();
		Extremum ext = src.Extremum();
		Vector v = ext.along(dst.times(+1f));
		return new Point(v.plus(dst), 1f);
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim;
	}
	
	
	Vector computePenetration()
	{
		if(rsp.hasImpact())
		{
			Vector pnt = rsp.Penetration();
			
			float r = src.Radius();
			float l = pnt.norm();
			l = (r + l) / l;
			
			return pnt.times(l);
		}
		
		Vector dst = rsp.Distance();
		
		float r = src.Radius();
		float l = dst.norm();
		l = (r - l) / l;
		
		return dst.times(l);
	}
	
	Vector computeDistance()
	{
		Vector dst = rsp.Distance();
		
		float r = src.Radius();
		float l = dst.norm();
		l = (r - l) / l;
		
		return dst.times(l);
	}
}