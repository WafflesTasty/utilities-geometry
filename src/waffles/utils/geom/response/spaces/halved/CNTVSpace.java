package waffles.utils.geom.response.spaces.halved;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.HSpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTVSpace} computes the containment response between a halfspace and a vector space.
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
	private VSpace tgt;
	private HSpace src;
	
	private Vector pnt;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTVSpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see HSpace
	 * @see VSpace
	 */
	public CNTVSpace(HSpace s, VSpace t)
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
		if(pnt == null)
		{
			int dim = src.Dimension();
			Vector o = Vectors.create(dim);
			pnt = src.contain(o).Penetration();
		}
		
		if(hasImpact())
		{
			return pnt;
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		return null;
	}
	
	@Override
	public Point Contact()
	{
		return null;
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return 2 * dim * (dim + 1);
	}


	boolean computeImpact()
	{		
		Vector n = src.Normal();
		Vector p = src.Origin().Generator();
		
		Matrix m = tgt.Generator();
		int cols = m.Columns();
		int rows = m.Rows();
		
		
		if(p.dot(n) > 0)
		{
			return false;
		}
		
		for(int i = 0; i < cols; i++)
		{
			Vector v = m.Column(i);
			if(!Floats.isZero(v.dot(n), rows))
			{
				return false;
			}
		}

		return true;
	}
}