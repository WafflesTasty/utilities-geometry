package waffles.utils.geom.collision.convex.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.spheroid.ISphere;
import waffles.utils.geom.collidable.axial.spheroid.ISpheroid;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.maps.affine.StandardMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.geom.utilities.Transforms;

/**
 * An {@code ISCASpace} computes the collision response from an ellipsoid to an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCASpace implements Response
{
	private ASpace tgt;
	private Response rsp;
	private ISpheroid src;
	private StandardMap map;
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source ellipsoid
	 * @param t  a target space
	 * 
	 * 
	 * @see ISpheroid
	 * @see ASpace
	 */
	public ISCASpace(ISpheroid s, ASpace t)
	{
		src = s;
		tgt = t;
	}
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source ellipsoid
	 * @param t  a target space
	 * 
	 * 
	 * @see ISpheroid
	 * @see VSpace
	 */
	public ISCASpace(ISpheroid s, VSpace t)
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
		
		return (Vector) map.map(rsp.Penetration());
	}

	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return (Vector) map.map(rsp.Distance());
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim * (dim - 1);
	}
	
	
	Response computeResponse()
	{
		int dim = src.Dimension();
		
		map = Transforms.fromUSphere(src);
		ISphere s = Geometries.sphere(dim);
		ASpace h = (ASpace) map.unmap(tgt);
		
		return s.intersect(h);
	}
}