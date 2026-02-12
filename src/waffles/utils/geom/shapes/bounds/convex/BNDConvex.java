package waffles.utils.geom.shapes.bounds.convex;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.convex.ConjugateSet;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.ConvexSet.Extremum;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;

/**
 * A {@code BNDConvex} defines dynamic {@code Bounds} for a {@code ConvexSet}.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see Bounds
 */
public class BNDConvex implements Bounds
{
	private ConvexSet src;

	/**
	 * Creates a new {@code BNDConvex}.
	 * 
	 * @param s  a source set
	 * 
	 * 
	 * @see ConvexSet
	 */
	public BNDConvex(ConvexSet s)
	{
		src = s;
	}

	
	@Override
	public int Dimension()
	{
		return src.Dimension();
	}
	
	@Override
	public Factory Factory()
	{
		return m -> new ConjugateSet(src, m).Bounds();
	}

	@Override
	public float Diameter()
	{
		return Scale().norm();
	}
	
	@Override
	public Point Minimum()
	{
		int d = Dimension();
		Vector e = Vectors.create(d);
		Extremum ext = src.Extremum();

		Vector m = Vectors.create(d);
		for(int k = 0; k < d; k++)
		{
			e.set(-1f, k);
			if(0 < k)
			{
				e.set(0f, k - 1);
			}
			
			Point p = ext.along(e);
			m.set(p.aff(k), k);
		}
		
		return new Point(m, 1f);
	}
	
	@Override
	public Point Maximum()
	{		
		int d = Dimension();
		Vector e = Vectors.create(d);
		Extremum ext = src.Extremum();

		Vector m = Vectors.create(d);
		for(int k = 0; k < d; k++)
		{
			e.set(+1f, k);
			if(0 < k)
			{
				e.set(0f, k - 1);
			}
			
			Point p = ext.along(e);
			m.set(p.aff(k), k);
		}
		
		return new Point(m, 1f);
	}
}