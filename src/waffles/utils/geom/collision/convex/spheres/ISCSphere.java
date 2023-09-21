package waffles.utils.geom.collision.convex.spheres;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.ISphere;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCSphere} computes the intersection from a sphere to a sphere.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCSphere implements Response
{
	private Vector dst;
	private ISphere src, tgt;	
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code ISCSphere}.
	 * 
	 * @param s  a source sphere
	 * @param t  a target sphere
	 * 
	 * 
	 * @see ISphere
	 */
	public ISCSphere(ISphere s, ISphere t)
	{
		src = s;
		tgt = t;
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
		if(hasImpact == null)
		{
			hasImpact = computeImpact();
		}
		
		return hasImpact;
	}

	@Override
	public Vector Penetration()
	{
		if(dst == null)
		{
			dst = computeVector();
		}
		
		if(hasImpact())
		{
			return dst;
		}
		
		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(dst == null)
		{
			dst = computeVector();
		}
		
		if(!hasImpact())
		{
			return dst;
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		return 3 * src.Dimension() + 2;
	}
	
	
	boolean computeImpact()
	{
		Vector c1 = src.Origin();
		Vector c2 = tgt.Origin();
		
		float r1 = src.Radius();
		float r2 = tgt.Radius();
		
		
		dst = c2.minus(c1);
		float val = dst.normSqr();
		return val <= (r1 + r2) * (r1 + r2);
	}
	
	Vector computeVector()
	{
		Vector c1 = src.Origin();
		Vector c2 = tgt.Origin();
		
		float r1 = src.Radius();
		float r2 = tgt.Radius();
		
		
		dst = c2.minus(c1);
		float norm = dst.norm();
		norm = (norm - r1 - r2) / norm;
		return dst.times(norm);
	}
}