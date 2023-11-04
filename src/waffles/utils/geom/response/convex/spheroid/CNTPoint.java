package waffles.utils.geom.response.convex.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.HyperSpheroid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collision.convex.CLSConvex;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTPoint} computes the collision response from an ellipsoid to a point.
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
	private Point tgt;
	private Response rsp;
	private HyperSpheroid src;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source ellipsoid
	 * @param p  a target point
	 * 
	 * 
	 * @see HyperSpheroid
	 * @see Point
	 */
	public CNTPoint(HyperSpheroid s, Point p)
	{
		src = s;
		tgt = p;
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
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return rsp.Penetration();
	}

	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return rsp.Distance();
	}
	
	@Override
	public int Cost()
	{
		return 5 * src.Dimension();
	}
	
	
	Response computeResponse()
	{
		return new CLSConvex(src).contain(tgt);
	}
	
	boolean computeImpact()
	{
		int dim = src.Dimension();
		Vector cen = src.Origin();
		Vector siz = src.Size();
		float mass = tgt.Mass();
		
		
		float val, sum = 0;
		for(int i = 0; i < dim; i++)
		{
			val = tgt.get(i) - mass * cen.get(i);
			
			if(!Floats.isZero(val, 1))
			{
				val /= siz.get(i);
				
				sum += val * val;
				if(4 * sum > mass * mass)
				{
					return false;
				}
			}
		}
		
		return true;
	}
}