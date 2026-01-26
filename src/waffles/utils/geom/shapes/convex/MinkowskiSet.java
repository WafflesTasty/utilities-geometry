package waffles.utils.geom.shapes.convex;

import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code MinkowskiSet} defines a {@code ConvexSet} as a Minkowski difference.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see ConvexSet
 */
public class MinkowskiSet implements ConvexSet
{
	private ConvexSet s1, s2;

	/**
	 * Creates a new {@code MinkowskiSet}.
	 * 
	 * @param s1  a convex set
	 * @param s2  a convex set
	 * 
	 * 
	 * @see ConvexSet
	 */
	public MinkowskiSet(ConvexSet s1, ConvexSet s2)
	{
		this.s1 = s1;
		this.s2 = s2;
	}
		
	
	@Override
	public Extremum Extremum()
	{
		return p ->
		{
			Extremum e1 = s1.Extremum();
			Extremum e2 = s2.Extremum();
			
			Point x = e1.along(p.times(-1f));
			Point y = e2.along(p.times(+1f));

			return y.minus(x);
		};
	}
	
	@Override
	public int Dimension()
	{
		return s1.Dimension();
	}
}