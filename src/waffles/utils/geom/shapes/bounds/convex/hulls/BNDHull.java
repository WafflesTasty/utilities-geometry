package waffles.utils.geom.shapes.bounds.convex.hulls;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.bounds.BNDGeometry;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.convex.hulls.Hull.Factory;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDHull} defines dynamic {@code Bounds} for an n-dimensional {@code Hull}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 *
 *
 * @see BNDGeometry
 */
public class BNDHull implements BNDGeometry
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
	public Point Origin()
	{
		Point min = Minimum();
		Point max = Maximum();
		
		return max.minus(min).times(0.5f);
	}
		
	@Override
	public Point Minimum()
	{
		Factory fc = src.Factory();
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
		Factory fc = src.Factory();
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
	public float Diameter()
	{
		return Scale().norm();
	}
	
	@Override
	public Hull Geometry()
	{
		return src;
	}
}