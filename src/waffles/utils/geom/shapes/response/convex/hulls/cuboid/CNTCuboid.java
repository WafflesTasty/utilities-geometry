package waffles.utils.geom.shapes.response.convex.hulls.cuboid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTCuboid} computes a containment {@code Response} between a cuboids.
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
	private Vector v, w;
	private Boolean hasImpact;
	private HyperCuboid src, tgt;

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
	
	/**
	 * Creates a new {@code CNTCuboid}.
	 *
	 * @param s  a source cuboid
	 * @param t  a target bounded
	 *
	 *
	 * @see HyperCuboid
	 * @see Bounded
	 */
	public CNTCuboid(HyperCuboid s, Bounded t)
	{
		this(s, t.Bounds().Box());
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
				float rk = s.aff(k) - t.aff(k);
				float ok = p.aff(k) - q.aff(k);
				
				v.set(ok - rk / 2, k);
				w.set(ok + rk / 2, k);
			}
		}
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
				float rk = s.aff(k) - t.aff(k);
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

	@Override
	public int cost()
	{
		return 5 * src.Dimension();
	}
}