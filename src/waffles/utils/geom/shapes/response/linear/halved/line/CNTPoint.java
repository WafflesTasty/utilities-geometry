package waffles.utils.geom.shapes.response.linear.halved.line;

import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.linear.APXPoint;
import waffles.utils.geom.shapes.response.linear.APXPoint.Hints;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code CNTPoint} computes a containment {@code Response} between a halfline and a point.
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
	 * @param s  a source line
	 * @param t  a target point
	 *
	 *
	 * @see Point
	 * @see HLine
	 */
	public CNTPoint(HLine s, Point t)
	{
		this(new Hints()
		{
			@Override
			public HLine S()
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
		return 9 * Dimension() - 3;
	}
	
	@Override
	public float L()
	{
		return Floats.min(0f, super.L());
	}
}