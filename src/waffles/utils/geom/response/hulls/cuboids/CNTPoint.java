package waffles.utils.geom.response.hulls.cuboids;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTPoint} computes the collision response from a cuboid to a point.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class CNTPoint implements Response
{
	private Point tgt;
	private HyperCuboid src;
	private Vector dst, pnt;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source cuboid
	 * @param p  a target point
	 * 
	 * 
	 * @see HyperCuboid
	 * @see Point
	 */
	public CNTPoint(HyperCuboid s, Point p)
	{
		src = s;
		tgt = p;
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
			if(hasImpact())
			{
				pnt = computePenetration();
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
				dst = computeDistance();
			}
		}
		
		return dst;
	}
	
	@Override
	public Point Contact()
	{
		return tgt;
	}
	
	@Override
	public int Cost()
	{
		return 4 * src.Dimension();
	}
	
	
	Vector computePenetration()
	{
		int dim = src.Dimension();
		Vector cen = src.Origin();
		Vector siz = src.Scale();
		float m = tgt.Mass();
		

		int lDim = -1; int uDim = -1;
		float lMin = Floats.MAX_VALUE;
		float uMin = Floats.MAX_VALUE;
		
		pnt = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			float xi = tgt.get(i);
			float si = siz.get(i);
			float pi = cen.get(i);
			
			float lVal = xi / m - pi + si / 2;
			if(Floats.abs(lVal) < Floats.abs(lMin))
			{
				lMin = lVal;
				lDim = i;
			}
			
			float uVal = xi / m - pi - si / 2;
			if(Floats.abs(uVal) < Floats.abs(uMin))
			{
				uMin = uVal;
				uDim = i;
			}
		}
		
		
		if(Floats.abs(lMin) < Floats.abs(uMin))
			pnt.set(lMin, lDim);
		else
			pnt.set(uMin, uDim);
		
		return pnt;
	}
	
	Vector computeDistance()
	{
		int dim = src.Dimension();
		Vector cen = src.Origin();
		Vector siz = src.Scale();
		float m = tgt.Mass();
		
		
		dst = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			float xi = tgt.get(i);
			float si = siz.get(i);
			float pi = cen.get(i);
			
			float val = 0f;
			if(xi < m * (pi - si / 2))
				val = xi / m - pi + si / 2;
			if(xi > m * (pi + si / 2))
				val = xi / m - pi - si / 2;

			dst.set(val, i);
		}
		
		return dst;
	}
	
	boolean computeImpact()
	{
		int dim = src.Dimension();
		Vector cen = src.Origin();
		Vector siz = src.Scale();
		float xm = tgt.Mass();
		
		
		for(int i = 0; i < dim; i++)
		{
			float xi = tgt.get(i);
			float si = siz.get(i);
			float pi = cen.get(i);
			
			if(si * Floats.abs(xm) < 2 * Floats.abs(xi - xm * pi))
			{
				return false;
			}
		}
		
		return true;
	}
}