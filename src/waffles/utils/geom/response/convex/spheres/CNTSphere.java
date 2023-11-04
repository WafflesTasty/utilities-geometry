package waffles.utils.geom.response.convex.spheres;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geom.utilities.Geometries;

/**
 * A {@code CNTSphere} computes the containment of a sphere in a sphere.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTSphere implements Response
{
	private Vector dst;
	private HyperSphere src, tgt;	
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTSphere}.
	 * 
	 * @param s  a source sphere
	 * @param t  a target sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public CNTSphere(HyperSphere s, HyperSphere t)
	{
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
		return val <= (r2 - r1) * (r2 - r1);
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