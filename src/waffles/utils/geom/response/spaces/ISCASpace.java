package waffles.utils.geom.response.spaces;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCASpace} computes the collision response from a geometrical object to an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class ISCASpace implements Response
{
	private int dim;
	private Response rsp;
	private GlobalMap map;
	
	/**
	 * Creates a new {@code ISCHull}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target space
	 * 
	 * 
	 * @see Geometrical
	 * @see ASpace
	 */
	public ISCASpace(Geometrical s, ASpace t)
	{
		map = s.Transform();
		Geometry shape = s.Shape();
		ASpace h = (ASpace) map.unmap(t);
		rsp = shape.intersect(h);
		dim = s.Dimension();
	}
	
	/**
	 * Creates a new {@code ISCHull}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target space
	 * 
	 * 
	 * @see Geometrical
	 * @see VSpace
	 */
	public ISCASpace(Geometrical s, VSpace t)
	{
		this(s, ASpace.Default(t));
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return null;
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
		return rsp.Cost() + 2 * dim * (dim - 1);
	}
}