package waffles.utils.geom.shapes.bounds.convex.hulls;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDHull} defines dynamic {@code Bounds} for a {@code Hull}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 *
 *
 * @see Bounds
 */
public class BNDHull implements Bounds
{
	private Hull src;

	/**
	 * Creates a new {@code BNDHull}.
	 *
	 * @param s  a source hull
	 *
	 *
	 * @see Hull
	 */
	public BNDHull(Hull s)
	{
		src = s;
	}

		
	@Override
	public Factory Factory()
	{
		return m -> 
		{
			Affine a = m.map(src);
			if(a instanceof Geometry)
			{
				return ((Geometry) a).Bounds();
			}
			
			return null;
		};
	}

	@Override
	public Point Minimum()
	{
		Hull.Factory fc = src.Factory();
		float max = Floats.MAX_VALUE;
		int d = Dimension();	

		Vector m = Vectors.create(max, d);
		for(int i = 0; i < fc.Count(); i++)
		{
			Point p = fc.Point(i);
			for(int j = 0; j < d; j++)
			{
				float val = p.aff(j);
				if(val < m.get(j))
				{
					m.set(val, j);
				}
			}
		}
		
		return new Point(m, 1f);
	}
	
	@Override
	public Point Maximum()
	{
		Hull.Factory fc = src.Factory();
		float min = Floats.MIN_VALUE;
		int d = Dimension();
		
		Vector m = Vectors.create(min, d);
		for(int i = 0; i < fc.Count(); i++)
		{
			Point p = fc.Point(i);
			for(int j = 0; j < d; j++)
			{
				float val = p.aff(j);
				if(val > m.get(j))
				{
					m.set(val, j);
				}
			}
		}
		
		return new Point(m, 1f);
	}

	@Override
	public float Radius()
	{
		float r = 0f;
		Point o = Origin();

		Hull.Factory f = src.Factory();
		for(int k = 0; k < f.Count(); k++)
		{
			Point p = f.Point(k);
			Point d = p.minus(o);
			
			float s = d.normSqr();
			if(r < s)
			{
				r = s;
			}
		}
		
		return r;
	}
}