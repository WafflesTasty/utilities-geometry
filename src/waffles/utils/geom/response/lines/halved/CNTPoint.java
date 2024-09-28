package waffles.utils.geom.response.lines.halved;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.lines.HLine;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTPoint} computes the collision response between a half-line and a point.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 *
 *
 * @see Response
 */
public class CNTPoint implements Response
{
	private Vector v;
	private Point p0, tgt;
	
	private Boolean hasImpact;
	private Vector dst;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param l  a source line
	 * @param p  a target point
	 * 
	 * 
	 * @see Point
	 * @see HLine
	 */
	public CNTPoint(HLine l, Point p)
	{
		v = l.Direction();
		p0 = l.Origin();
		tgt = p;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		int dim = p0.Dimension();
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
			int dim = p0.Dimension();
			return Vectors.create(dim);
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
	public Point Contact()
	{
		return tgt;
	}
	
	@Override
	public int Cost()
	{
		int dim = tgt.Dimension();
		return 7 * dim - 1;
	}
	
	
	Vector computeDistance()
	{
		int dim = tgt.Dimension();
		Vector x = tgt.Generator();
		Vector p = p0.Generator();
		Vector xp = x.minus(p);
		
		
		float xdot = xp.dot(v);
		float vdot =  v.dot(v);

		if(xdot < 0)
		{
			return xp;
		}
		
		dst = v.times(xdot / vdot);
		dst = xp.minus(dst);
		return dst;
	}
	
	boolean computeImpact()
	{
		if(dst == null)
		{
			dst = computeDistance();
		}

		int dim = tgt.Dimension();
		return Floats.isZero(dst.normSqr(), 2*dim-1);
	}
}