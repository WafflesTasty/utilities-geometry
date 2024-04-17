package waffles.utils.geom.response;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code CNTPoint} computes the collision response from a geometrical to a point.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class CNTPoint implements Response
{
	private int dim;
	private Point tgt;
	private Response rsp;
	private GlobalMap map;
	
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
		map = s.Transform();
		
		Point q = (Point) map.unmap(p);
		Geometry shape = s.Shape();
		rsp = shape.contain(q);
		
		dim = s.Dimension();
		tgt = p;
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{			
		return rsp.hasImpact();
	}
	
	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			Vector pnt = rsp.Penetration();
			return (Vector) map.map(pnt);			
		}

		return null;
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector dst = rsp.Distance();
			return (Vector) map.map(dst);
		}
		
		return null;
	}
	
	@Override
	public Point Contact()
	{
		Point cnt = rsp.Contact();
		if(cnt != null)
		{
			cnt = (Point) map.map(cnt);
			return cnt;
		}

		return null;
	}
	
	@Override
	public int Cost()
	{
		return rsp.Cost() + dim * dim;
	}
}