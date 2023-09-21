package waffles.utils.geom.collision.hulls.points;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.utilities.Geometries;

/**
 * A {@code CNTPoint} computes the collision response between two points.
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
	private Point src, tgt;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param p  a source point
	 * @param q  a target point
	 * 
	 * 
	 * @see Point
	 */
	public CNTPoint(Point p, Point q)
	{
		src = p;
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
			Vector v1 = src.Generator();
			Vector v2 = tgt.Generator();
			
			hasImpact = v1.equals(v2, 3);
		}
		
		return hasImpact;
	}
	
	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			int dim = src.Dimension();
			return Vectors.create(dim);
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector v1 = src.Generator();
			Vector v2 = tgt.Generator();
			
			return v2.minus(v1);
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		return src.Dimension();
	}
}