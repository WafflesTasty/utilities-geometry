package waffles.utils.geom.collision.general;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code CNTPoint} computes the collision response from a geometrical to a point.
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
	private Geometrical src;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source geometrical
	 * @param p  a target point
	 * 
	 * 
	 * @see Geometrical
	 * @see Point
	 */
	public CNTPoint(Geometrical s, Point p)
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
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return rsp.hasImpact();
	}
	
	@Override
	public Vector Penetration()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}

		Vector pnt = rsp.Penetration();
		GlobalMap map = src.Transform();
		return (Vector) map.map(pnt);
	}

	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		Vector dst = rsp.Distance();
		GlobalMap map = src.Transform();
		return (Vector) map.map(dst);
	}
	
	@Override
	public int Cost()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		int dim = src.Dimension();
		return rsp.Cost() + dim * dim;
	}
	
	
	Response computeResponse()
	{
		Geometry shape = src.Shape();
		GlobalMap map = src.Transform();

		Point p = (Point) map.unmap(tgt);
		return shape.contain(p);
	}
}