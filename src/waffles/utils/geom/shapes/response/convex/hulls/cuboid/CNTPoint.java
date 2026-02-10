package waffles.utils.geom.shapes.response.convex.hulls.cuboid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code CNTPoint} computes a containment {@code Response} between a cuboid and a point.
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
	private HyperCuboid src;
	private Boolean hasImpact;
	private Point v, w, tgt;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param c  a source cuboid
	 * @param x  a target point
	 * 
	 * 
	 * @see HyperCuboid
	 * @see Point
	 */
	public CNTPoint(HyperCuboid c, Point x)
	{
		Point s = c.Scale();
		Point p = c.Origin();
		
		
		p = p.minus(x);
		s = s.times(0.5f);

		v = p.minus(s);
		w = p.plus(s);
		
		src = c;
		tgt = x;
	}


	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public boolean hasImpact()
	{			
		if(hasImpact == null)
		{
			hasImpact = true;
			for(int k = 0; k < Dimension(); k++)
			{
				float vk = v.aff(k);
				float wk = w.aff(k);

				if(wk < 0 || 0 < vk)
				{
					hasImpact = false;
					break;
				}
			}
		}
		
		return hasImpact;
	}
	
	@Override
	public HyperCuboid Source()
	{
		return src;
	}
			
	@Override
	public Vector Penetration()
	{
		int n = Dimension();
		Vector pnt = Vectors.create(n);
		if(hasImpact())
		{
			int m = 0;
			float d = Floats.MAX_VALUE;
			for(int k = 0; k < n; k++)
			{
				float vk = Floats.abs(v.aff(k));
				float wk = Floats.abs(w.aff(k));
				
				if(vk < Floats.abs(d))
				{
					d = vk;
					m = k;
				}
				
				if(wk < Floats.abs(d))
				{
					d = wk;
					m = k;
				}
			}
			
			pnt.set(d, m);
		}

		return pnt;
	}

	@Override
	public Vector Distance()
	{
		int n = Dimension();
		Vector dst = Vectors.create(n);
		if(!hasImpact())
		{
			for(int k = 0; k < n; k++)
			{
				float vk = Floats.max(0f, v.aff(k));
				float wk = Floats.min(0f, w.aff(k));
				
				dst.set(vk + wk, k);
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
	public int cost()
	{
		return 4 * src.Dimension();
	}
}