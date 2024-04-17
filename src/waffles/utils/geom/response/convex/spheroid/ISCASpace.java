package waffles.utils.geom.response.convex.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geom.collidable.axial.spheroid.HyperSpheroid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.spatial.maps.spatial.StandardMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.geom.utilities.Transforms;

/**
 * An {@code ISCASpace} computes the collision response from an ellipsoid to an affine space.
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
	private StandardMap map;
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source ellipsoid
	 * @param t  a target space
	 * 
	 * 
	 * @see HyperSpheroid
	 * @see ASpace
	 */
	public ISCASpace(HyperSpheroid s, ASpace t)
	{
		dim = s.Dimension();
		map = Transforms.fromUSphere(s);
		HyperSphere u = Geometries.Sphere(dim);
		ASpace h = (ASpace) map.unmap(t);
		rsp =  u.intersect(h);
	}
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source ellipsoid
	 * @param t  a target space
	 * 
	 * 
	 * @see HyperSpheroid
	 * @see VSpace
	 */
	public ISCASpace(HyperSpheroid s, VSpace t)
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
		Vector pnt = rsp.Penetration();
		if(pnt != null)
		{
			return (Vector) map.map(pnt);
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		Vector dst = rsp.Distance();
		if(dst != null)
		{
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
			return (Point) map.map(cnt);
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		return rsp.Cost() + 2 * dim * (dim - 1);
	}
}