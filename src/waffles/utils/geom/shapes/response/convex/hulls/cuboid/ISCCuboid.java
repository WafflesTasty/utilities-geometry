package waffles.utils.geom.shapes.response.convex.hulls.cuboid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code ISCCuboid} computes an intersection {@code Response} between cuboids.
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
	private Vector v, w;
	private Boolean hasImpact;
	private HyperCuboid src, tgt;

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
		src = s;
		tgt = t;
	}

	
	private void compute()
	{
		if(v == null && w == null)
		{
			Point p = src.Origin();
			Point q = tgt.Origin();
			
			Point s = src.Scale();
			Point t = tgt.Scale();
			
			int n = Dimension();
			
			
			v = Vectors.create(n);
			w = Vectors.create(n);
			
			for(int k = 0; k < n; k++)
			{
				float rk = s.aff(k) + t.aff(k);
				float ok = p.aff(k) - q.aff(k);
				
				v.set(ok - rk / 2, k);
				w.set(ok + rk / 2, k);
			}
		}
	}
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			int d = Dimension();
			
			Point p = src.Origin();
			Point q = tgt.Origin();
			
			Point s = src.Scale();
			Point t = tgt.Scale();
			
			float m = p.Mass();
			float n = q.Mass();
			

			Vector a = Vectors.create(d);
			Vector b = Vectors.create(d);
			
			for(int k = 0; k < d; k++)
			{
				float vk = Floats.abs(v.get(k));
				float wk = Floats.abs(w.get(k));
				
				float xk = 2 * (p.aff(k) - q.aff(k));
				float yk = 1 * (s.aff(k) - t.aff(k));
				
				if(vk <= wk)
				{
					a.set(xk + yk, k);
					b.set(vk, k);
				}
				else
				{
					a.set(xk - yk, k);
					b.set(wk, k);					
				}
			}

			
			Arrow r = new Arrow(b);
			Point o = new Point(a, 4f);			
			return HyperCuboid.create(o, r);
		}

		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public boolean hasImpact()
	{
		if(hasImpact == null)
		{
			Point p = src.Origin();
			Point q = tgt.Origin();
			
			Point s = src.Scale();
			Point t = tgt.Scale();


			hasImpact = true;
			for(int k = 0; k < Dimension(); k++)
			{
				float rk = s.aff(k) + t.aff(k);
				float ok = p.aff(k) - q.aff(k);

				if(rk < 2 * Floats.abs(ok))
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
		compute();
		int n = Dimension();
		Vector pnt = Vectors.create(n);
		
		if(hasImpact())
		{
			int m = 0;
			float d = Floats.MAX_VALUE;
			for(int k = 0; k < n; k++)
			{
				float vk = Floats.abs(v.get(k));
				float wk = Floats.abs(w.get(k));
				
				if(vk < Floats.abs(d))
				{
					d = v.get(k);
					m = k;
				}
				
				if(wk < Floats.abs(d))
				{
					d = w.get(k);
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
		compute();
		int n = Dimension();
		Vector dst = Vectors.create(n);
		
		if(!hasImpact())
		{
			for(int k = 0; k < n; k++)
			{
				float vk = Floats.max(0f, v.get(k));
				float wk = Floats.min(0f, w.get(k));
				
				dst.set(vk + wk, k);
			}
		}

		return dst;
	}

	@Override
	public Point Contact()
	{
		return tgt.Origin();
	}
}