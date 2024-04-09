package waffles.utils.geom.response.hulls.cuboids;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.tools.primitives.Array;
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
	private int[] defects;
	private Vector dlt, pnt, dst;
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
		defects = new int[0];
		src = s; tgt = t;
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
		if(pnt == null)
		{
			if(hasImpact())
			{
				
			}
		}
		
		return pnt;
	}
	
	@Override
	public Vector Distance()
	{
		if(dst == null)
		{
			if(!hasImpact())
			{
				
			}
		}
		
		return dst;
	}
	
	@Override
	public int Cost()
	{
		return 5 * src.Dimension();
	}

	
	Vector computeDelta()
	{
		int d1 = src.Dimension();
		int d2 = tgt.Dimension();
		
		int dim = Integers.min(d1, d2);
		dlt = Vectors.create(dim);
		
		for(int i = 0; i < dim; i++)
		{
			float ti = src.Size().get(i);
			float si = tgt.Size().get(i);
			
			float qi = src.Origin().get(i);
			float pi = tgt.Origin().get(i);

			float val = qi - pi;
			if(pi <= qi)
			{
				val -= (ti - si) / 2;
			}
			else
			{
				val += (ti - si) / 2;
			}

			dlt.set(val, i);
		}
		
		return dlt;
	}
	
	Vector computeDistance()
	{
		if(dlt == null)
		{
			dlt = computeDelta();
		}
		
		
		int d1 = src.Dimension();
		int d2 = tgt.Dimension();
		
		int dim = Integers.min(d1, d2);
		dst = Vectors.create(dim);
		for(int i : defects)
		{
			dst.set(dlt.get(i), i);
		}
		
		return dst;
	}
	
	Vector computePenetration()
	{
		if(dlt == null)
		{
			dlt = computeDelta();
		}
		
		
		int kMin = -1;
		float vMin = Floats.MAX_VALUE;

		
		int d1 = src.Dimension();
		int d2 = tgt.Dimension();
		
		int dim = Integers.min(d1, d2);		
		for(int i = 0; i < dim; i++)
		{
			float val = Floats.abs(dlt.get(i));
			if(val < vMin)
			{
				vMin = val;
				kMin = i;
			}
		}
		
		
		pnt = Vectors.create(dim);
		pnt.set(vMin, kMin);
		return pnt;
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
				defects = Array.add.to(defects, i);
			}
		}
		
		return defects.length == 0;
	}
}