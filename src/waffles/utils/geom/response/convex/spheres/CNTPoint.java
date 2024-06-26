package waffles.utils.geom.response.convex.spheres;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTPoint} computes the collision response from a sphere to a point.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTPoint implements Response
{
	private HyperSphere src;
	private Vector dst, tgt;	
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source sphere
	 * @param p  a target point
	 * 
	 * 
	 * @see HyperSphere
	 * @see Point
	 */
	public CNTPoint(HyperSphere s, Point p)
	{
		src = s;
		tgt = p.Generator();
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return new Point(tgt);
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
	public Point Contact()
	{
		if(hasImpact())
		{
			return new Point(tgt, 1f);
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		return 3 * src.Dimension();
	}
	
	
	boolean computeImpact()
	{
		float rad = src.Radius();
		int dim = src.Dimension();
		
		Vector c = src.Origin();		
		Vector x = tgt.minus(c);
		
		
		float sum = 0f;
		for(int i = 0; i < dim; i++)
		{
			sum += Floats.pow(x.get(i), 2);
			if(sum > rad * rad)
			{
				return false;
			}
		}
		
		return true;
	}
	
	Vector computeVector()
	{
		float rad = src.Radius();
		Vector c = src.Origin();
		
		
		dst = tgt.minus(c);
		float nrm = dst.norm();
		nrm = (nrm - rad) / nrm;
		dst = dst.times(nrm);
		return dst;
	}
}