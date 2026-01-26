package waffles.utils.geom.shapes.bounds.convex;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.bounds.BNDGeometry;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.ConvexSet.Extremum;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code BNDConvex} defines dynamic {@code Bounds} for a {@code ConvexSet}.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see BNDGeometry
 */
public class BNDConvex implements BNDGeometry
{
	private ConvexSet src;
	private LinearMap map;
		
	/**
	 * Creates a new {@code BNDConvex}.
	 * 
	 * @param s  a source set
	 * @param m  a linear map
	 * 
	 * 
	 * @see LinearMap
	 * @see ConvexSet
	 */
	public BNDConvex(ConvexSet s, LinearMap m)
	{
		src = s;
		map = m;
	}
	
	/**
	 * Creates a new {@code BNDConvex}.
	 * 
	 * @param s  a source set
	 * 
	 * 
	 * @see Geometry
	 */
	public BNDConvex(ConvexSet s)
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
		int d = Dimension();
		Vector m = Vectors.create(d);		
		Extremum ext = Geometry().Extremum();

		Vector e;
		for(int k = 0; k < d; k++)
		{
			e = Vectors.create(d + 1);
			e.set(-1f, k);
			
			if(Map() != null)
			{
				e = (Vector) Map().unmap(e);
			}
			
			Point p = Point.create(e);
			p = ext.along(p);
			
			if(Map() != null)
			{
				p = (Point) Map().map(p);
			}
			
			m.set(p.aff(k), k);
		}
		
		return new Point(m, 1f);
	}
	
	@Override
	public Point Maximum()
	{		
		int d = Dimension();
		Vector m = Vectors.create(d);		
		Extremum ext = Geometry().Extremum();

		Vector e;
		for(int k = 0; k < d; k++)
		{
			e = Vectors.create(d + 1);
			e.set(+1f, k);
			
			if(Map() != null)
			{
				e = (Vector) Map().unmap(e);
			}
			
			Point p = Point.create(e);
			p = ext.along(p);
			
			if(Map() != null)
			{
				p = (Point) Map().map(p);
			}
			
			m.set(p.aff(k), k);
		}
		
		return new Point(m, 1f);
	}
	
	@Override
	public int Dimension()
	{
		return src.Dimension();
	}
	
	@Override
	public float Diameter()
	{
		return Scale().norm();
	}
	
	@Override
	public ConvexSet Geometry()
	{
		return src;
	}

	@Override
	public LinearMap Map()
	{
		return map;
	}
}