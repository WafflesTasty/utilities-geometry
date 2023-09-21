package waffles.utils.geom.collision.general.align;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Alignable;
import waffles.utils.geom.collidable.axial.AxialSet;
import waffles.utils.geom.maps.AlignedMap;
import waffles.utils.geom.maps.affine.align.ALGComposition;
import waffles.utils.geom.maps.affine.align.ALGInverse;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCAlign} computes the intersection between alignable objects.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCAlign implements Response
{
	private Response rsp;
	private Alignable src, tgt;
	
	/**
	 * Creates a new {@code ISCGeneral}.
	 * 
	 * @param s  a source alignable
	 * @param t  a target alignable
	 * 
	 * 
	 * @see Alignable
	 */
	public ISCAlign(Alignable s, Alignable t)
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
		
		return rsp.Penetration();
	}
	
	@Override
	public Vector Distance()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return rsp.Distance();
	}
	
	@Override
	public int Cost()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim;
	}
	
	
	private Response computeResponse()
	{
		AlignedMap m1 = tgt.Transform();
		AlignedMap m2 = new ALGInverse(src.Transform());
		AlignedMap c = new ALGComposition(m2, m1);
	
		AxialSet s1 = tgt.Shape().map(c);
		AxialSet s2 = src.Shape();
		return s2.intersect(s1);
	}
}