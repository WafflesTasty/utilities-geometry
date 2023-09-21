package waffles.utils.geom.collision.hulls.segments;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.hulls.segments.ISegment;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTPoint} computes the collision response from a line segment to a point.
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
	private Float min;
	private ISegment src;
	private Boolean hasImpact;
	private Vector tgt;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source segment
	 * @param p  a target point
	 * 
	 * 
	 * @see ISegment
	 * @see Point
	 */
	public CNTPoint(ISegment s, Point p)
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
		if(hasImpact())
		{
			return computeVector();
		}
		
		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return computeVector();
		}
		
		return null;
	}

	@Override
	public int Cost()
	{
		return 9 * src.Dimension() - 2;
	}
	
		
	boolean computeImpact()
	{
		int dim = src.Dimension();
		Vector p = src.P1().Generator();
		Vector q = src.P2().Generator();
		
		Vector xp = tgt.minus(p);
		Vector qp = q.minus(p);


		float dq = qp.dot(qp);
		float dx = qp.dot(xp);
		
		if(0 <= dx && dx <= dq)
		{
			Vector qpx = qp.times(dx);
			Vector xpq = xp.times(dq);
			
			float dst = qpx.distSqr(xpq);
			if(Floats.isZero(dst, 4 + dim / 2))
			{
				if(2 * dx < dq)
					min = 0f;
				else
					min = 1f;
				return true;
			}
		}
		
		min = Floats.clamp(dx / dq, 0f, 1f);
		return false;
	}
	
	Vector computeVector()
	{
		Point p = src.P1();
		Point q = src.P2();
		
		Vector v = q.minus(p).times(min);
		return tgt.minus(p.plus(v));
	}
}