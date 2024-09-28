package waffles.utils.geom.response.lines;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTPoint} computes the collision response between an affine line and a point.
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
	private Point tgt;
	private Point p1, p2;
	
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
	 * @see Line
	 */
	public CNTPoint(Line l, Point p)
	{
		p1 = l.P1();
		p2 = l.P2();
		tgt = p;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		int dim = p1.Dimension();
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
			int dim = p1.Dimension();
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
		Vector p = p1.Generator();
		Vector q = p2.Generator();

		Vector qp = q.minus(p);
		Vector xp = x.minus(p);
		
		
		float xdot = xp.dot(qp);
		float qdot = qp.dot(qp);
		
		// If the points are together...
		if(Floats.isZero(qdot, 2*dim-1))
		{
			// Return the average vector.
			return xp.plus(qp).times(0.5f);
		}
		
		dst = qp.times(xdot / qdot);
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