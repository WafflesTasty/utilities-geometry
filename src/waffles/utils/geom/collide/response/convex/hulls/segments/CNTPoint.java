package waffles.utils.geom.collide.response.convex.hulls.segments;

import waffles.utils.geom.collide.response.linear.APXPoint;
import waffles.utils.geom.collide.response.linear.APXPoint.Hints;
import waffles.utils.geom.shapes.convex.hulls.line.Segment;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code CNTPoint} computes a containment {@code Response} between a line segment and a point.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 *
 *
 * @see APXPoint
 */
public class CNTPoint extends APXPoint
{
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public CNTPoint(Hints h)
	{
		super(h);
	}

	/**
	 * Creates a new {@code CNTPoint}.
	 *
	 * @param s  a source segment
	 * @param t  a target point
	 *
	 *
	 * @see Segment
	 * @see Point
	 */
	public CNTPoint(Segment s, Point t)
	{
		this(new Hints()
		{
			@Override
			public Segment S()
			{
				return s;
			}
			
			@Override
			public Point T()
			{
				return t;
			}
		});
	}
	
	
	@Override
	public int cost()
	{
		return 11 * Dimension() - 3;
	}
	
	@Override
	public float L()
	{
		return Floats.clamp(super.L(), 0f, 1f);
	}
}