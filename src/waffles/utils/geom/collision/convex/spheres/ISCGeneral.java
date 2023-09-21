package waffles.utils.geom.collision.convex.spheres;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.ISphere;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCGeneral} computes the intersection from a sphere to any object.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCGeneral implements Response
{
	private ISphere src;
	private Response rsp;
	
	/**
	 * Creates a new {@code ISCGeneral}.
	 * 
	 * @param s  a source sphere
	 * @param c  a target collidable
	 * 
	 * 
	 * @see Collidable
	 * @see ISphere
	 */
	public ISCGeneral(ISphere s, Collidable c)
	{
		rsp = c.contain(s.Origin());
		src = s;
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

		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector dst = rsp.Distance();
			
			float r = src.Radius();
			float l = dst.norm();
			l = (r - l) / l;
			
			return dst.times(l);
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim;
	}
}