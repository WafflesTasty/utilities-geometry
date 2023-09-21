package waffles.utils.geom.collision.general;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.maps.fixed.Composition;
import waffles.utils.geom.maps.fixed.Inverse;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCGeometric} computes the collision response between geometrical objects.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCGeometric implements Response
{
	private Response rsp;
	private Geometrical src, tgt;
	
	/**
	 * Creates a new {@code ISCGeometric}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target geometrical
	 * 
	 * 
	 * @see Geometrical
	 */
	public ISCGeometric(Geometrical s, Geometrical t)
	{
		src = s;
		tgt = t;
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
		
		if(hasImpact())
		{
			GlobalMap map = src.Transform();
			return (Vector) map.map(rsp.Penetration());
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		if(!hasImpact())
		{
			GlobalMap map = src.Transform();
			return (Vector) map.map(rsp.Distance());
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim * (dim - 1);
	}
	
	
	Geometrical computeInverse()
	{
		return new Geometrical()
		{
			@Override
			public GlobalMap Transform()
			{
				GlobalMap m1 = tgt.Transform();
				GlobalMap m2 = new Inverse(src.Transform());
				return new Composition(m2, m1);
			}
			
			@Override
			public Geometry Shape()
			{
				return tgt.Shape();
			}
		};
	}
	
	Response computeResponse()
	{
		Geometrical inv = computeInverse();
		return src.Shape().intersect(inv);
	}
}