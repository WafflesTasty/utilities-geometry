package waffles.utils.geom.response.hulls.cuboids;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code CNTCuboid} computes the containment response between cuboids.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTCuboid implements Response
{
	private Response rsp;
	private HyperCuboid src, tgt;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTCuboid}.
	 * 
	 * @param s  a source cuboid
	 * @param t  a target cuboid
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public CNTCuboid(HyperCuboid s, HyperCuboid t)
	{
		src = s;
		tgt = t;
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
		if(rsp == null)
		{
			rsp = computeVectors();
		}
		
		return rsp.Penetration();
	}
	
	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeVectors();
		}
		
		return rsp.Distance();
	}
	
	@Override
	public int Cost()
	{
		return 5 * src.Dimension();
	}
	
	
	Response computeVectors()
	{
		Collision cls = src.Collisions();
		return cls.intersect(tgt);
	}
	
	boolean computeImpact()
	{
		int dim = Integers.min(src.Dimension(), tgt.Dimension());
		for(int i = 0; i < dim; i++)
		{
			float si = src.Size().get(i);
			float ti = tgt.Size().get(i);
			
			float pi = src.Origin().get(i);
			float qi = tgt.Origin().get(i);
			
			if(si - ti < 2 * Floats.abs(pi - qi))
			{
				return false;
			}
		}
		
		return true;
	}
}