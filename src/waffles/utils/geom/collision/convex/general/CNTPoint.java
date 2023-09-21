package waffles.utils.geom.collision.convex.general;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code CNTPoint} computes the collision response from a convex set to a point.
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
	private Vector tgt;
	private ConvexSet src;
	
	private Vector dst, pnt;	
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source convex
	 * @param p  a target point
	 * 
	 * 
	 * @see ConvexSet
	 * @see Point
	 */
	public CNTPoint(ConvexSet s, Point p)
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
		if(dst == null)
			dst = Phase1();
		return hasImpact;
	}

	@Override
	public Vector Penetration()
	{
		if(dst == null)
			dst = Phase1();
		if(pnt == null)
			pnt = Phase2();
		
		if(hasImpact())
		{
			return tgt.minus(pnt);
		}
		
		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return tgt.minus(dst);
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return Integers.pow(dim, 2* dim);
	}
	
	
	Vector Phase1()
	{
		int i;
		hasImpact = false;
		return Vectors.create(src.Dimension());
	}
	
	Vector Phase2()
	{
		int j;
		hasImpact = false;
		return Vectors.create(src.Dimension());
	}
}