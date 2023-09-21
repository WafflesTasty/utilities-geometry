package waffles.utils.geom.collision.spaces.halved;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.HSpace;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code CNTPoint} computes the collision response between a halfspace and a point.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTPoint implements Response
{
	private Point tgt;
	private HSpace src;
	
	private Vector dst;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source space
	 * @param p  a target point
	 * 
	 * 
	 * @see HSpace
	 * @see Point
	 */
	public CNTPoint(HSpace s, Point p)
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
		if(dst == null)
		{
			dst = computeDistance();
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
			dst = computeDistance();
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
		int dim = src.Dimension();
		return 2 * dim * (dim + 1);
	}
	
		
	Vector computeDistance()
	{
		Vector n = src.Normal();
		Vector p = src.Origin().Generator();
		Vector x = tgt.Generator();
		
		
		float d1 = n.dot(n);
		float d2 = p.minus(x).dot(n);
		dst = n.times(d2 / d1);
		return dst;
	}
	
	boolean computeImpact()
	{
		Vector n = src.Normal();
		Vector p = src.Origin().Generator();
		Vector x = tgt.Generator();
		
		
		float dot = x.minus(p).dot(n);
		hasImpact = dot >= 0;
		return hasImpact;
	}
}