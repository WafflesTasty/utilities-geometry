package waffles.utils.geom.response.convex.spheres;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;
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
	private float norm;
	private Vector dst;
	private HyperSphere src, tgt;	
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code ISCSphere}.
	 * 
	 * @param s  a source sphere
	 * @param t  a target sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public ISCSphere(HyperSphere s, HyperSphere t)
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
		if(hasImpact())
		{
			return dst;
		}
		
		return null;
	}
	
	@Override
	public Vector Distance()
	{
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
		float r1 = src.Radius();
		float r2 = tgt.Radius();
		dst = computeVector();
		
		return norm < r1 + r2;
	}
	
	Vector computeVector()
	{
		Vector c1 = src.Origin();
		Vector c2 = tgt.Origin();
		
		float r1 = src.Radius();
		float r2 = tgt.Radius();
		
		dst = c2.minus(c1);
		norm = dst.norm();
		
		float val = norm - r1 - r2;
		return dst.times(val / norm);
	}
}