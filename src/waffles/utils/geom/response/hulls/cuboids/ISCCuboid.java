package waffles.utils.geom.response.hulls.cuboids;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code ISCCuboid} computes an intersection response of cuboids.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class ISCCuboid implements Response
{
	private int[] defects;
	private Vector min, max;
	private HyperCuboid src, tgt;
	private Vector dst, pnt, dlt;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code ISCCuboid}.
	 * 
	 * @param s  a source cuboid
	 * @param t  a target cuboid
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public ISCCuboid(HyperCuboid s, HyperCuboid t)
	{
		defects = new int[0];
		src = s; tgt = t;
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			Vector siz = max.minus(min);
			Vector cen = max.plus(min).times(0.5f);
			return Geometries.Cuboid(cen, siz);
		}

		int dim = src.Dimension();
		return Geometries.Void(dim);
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
		if(hasImpact())
		{
			if(pnt == null)
			{
				pnt = computePenetration();
			}
			
			return pnt;
		}
		
		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			if(dst == null)
			{
				dst = computeDistance();
			}
			
			return dst;
		}
		
		return null;
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
	
	Vector computeDelta()
	{
		int d1 = src.Dimension();
		int d2 = tgt.Dimension();
		
		int dim = Integers.min(d1, d2);
		dlt = Vectors.create(dim);
		
		for(int i = 0; i < dim; i++)
		{
			float si = src.Size().get(i);
			float ti = tgt.Size().get(i);
			
			float pi = src.Origin().get(i);
			float qi = tgt.Origin().get(i);

			float val = pi - qi;
			if(qi <= pi)
			{
				val -= (si + ti) / 2;
			}
			else
			{
				val += (si + ti) / 2;
			}

			dlt.set(val, i);
		}
		
		return dlt;
	}

	boolean computeImpact()
	{
		int d1 = src.Dimension();
		int d2 = tgt.Dimension();
		
		int dim = Integers.min(d1, d2);
		min = Vectors.create(dim);
		max = Vectors.create(dim);
		
		for(int i = 0; i < dim; i++)
		{
			float si = src.Size().get(i);
			float ti = tgt.Size().get(i);
			
			float pi = src.Origin().get(i);
			float qi = tgt.Origin().get(i);
			
			if(si + ti < 2 * Floats.abs(pi - qi))
			{
				Array.add.to(defects, i);
				continue;
			}
			
			float iMin = Floats.max(pi - si / 2, qi - ti / 2);
			float iMax = Floats.min(pi + si / 2, qi + ti / 2); 
			
			min.set(iMin, i);
			max.set(iMax, i);
		}
		
		return defects.length == 0;
	}
}