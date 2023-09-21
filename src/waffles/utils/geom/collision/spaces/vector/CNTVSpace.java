package waffles.utils.geom.collision.spaces.vector;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code CNTVSpace} computes the collision response for vector space containment.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTVSpace implements Response
{
	private VSpace sum;
	private VSpace src, tgt;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTVSpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see VSpace
	 */
	public CNTVSpace(VSpace s, VSpace t)
	{
		src = s;
		tgt = t;
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
		if(hasImpact == null)
		{
			hasImpact = computeImpact();
		}
		
		return hasImpact;
	}
	
	@Override
	public Vector Penetration()
	{
		return null;
	}

	@Override
	public Vector Distance()
	{
		return null;
	}
	
	@Override
	public int Cost()
	{
		int d1 = src.Dimension();
		int d2 = sum.Dimension();
		
		int m1 = 2 * Integers.pow(d1, 4) * (d1 - 1);
		int m2 = 2 * Integers.pow(d2, 4) * (d2 - 1);
		
		return 2 * (m1 * d1 + m2 * d2);
	}
	
	
	boolean computeImpact()
	{
		sum = src.add(tgt);
		
		int d1 = src.Dimension();
		int d2 = sum.Dimension();
		
		return d1 == d2;
	}
}