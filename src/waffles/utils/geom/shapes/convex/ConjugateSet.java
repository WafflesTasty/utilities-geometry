package waffles.utils.geom.shapes.convex;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code ConjugateSet} defines a {@code ConvexSet} transformed by a {@code LinearMap}.
 * By convention, this set is chosen to represent the inverse map of the source set.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see ConvexSet
 */
public class ConjugateSet implements ConvexSet
{
	private ConvexSet src;
	private LinearMap map;

	/**
	 * Creates a new {@code ConjugateSet}.
	 * 
	 * @param s  a convex set
	 * @param m  a linear map
	 * 
	 * 
	 * @see ConvexSet
	 * @see LinearMap
	 */
	public ConjugateSet(ConvexSet s, LinearMap m)
	{
		src = s;
		map = m;
	}
		
	
	@Override
	public Extremum Extremum()
	{
		return p ->
		{
			Extremum ext = src.Extremum();
			
			Point q = p;
			q = (Point) map.map(p);
			q = ext.along(q);
			q = (Point) map.unmap(q);
			
			return q;
		};
	}
	
	@Override
	public int Dimension()
	{
		return src.Dimension();
	}
	
	@Override
	public Point Origin()
	{
		Point o = src.Origin();
		o = (Point) map.unmap(o);
		return o;
	}
	
	@Override
	public Arrow Scale()
	{
		Arrow s = src.Scale();
		s = (Arrow) map.unmap(s);
		return s;
	}
}