package waffles.utils.geom.shapes.collision.convex;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.convex.CNTPoint;
import waffles.utils.geom.shapes.response.convex.ISCConvex;
import waffles.utils.geom.shapes.response.convex.ISCHLine;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSConvex} defines {@code Collision} for a {@code ConvexSet}.
 *
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 *
 *
 * @see Collision
 */
public class CLSConvex extends Collision
{
	/**
	 * Creates a new {@code CLSConvex}.
	 *
	 * @param s  a source convex
	 *
	 *
	 * @see ConvexSet
	 */
	public CLSConvex(ConvexSet s)
	{
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSConvex}.
	 *
	 * @param s  a source convex
	 * @param e  an error margin
	 * 
	 *
	 * @see ConvexSet
	 */
	public CLSConvex(ConvexSet s, double e)
	{
		super(s, e);
	}


	@Override
	public Response intersect(Collidable c)
	{
		ConvexSet s = Source();

		// Eliminate halflines.
		if(c instanceof HLine)
		{
			HLine t = (HLine) c;
			return new ISCHLine(s, t);
		}
		
		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new ISCConvex(s, t);
		}

		return super.intersect(c);
	}

	@Override
	public Response contain(Collidable c)
	{
		ConvexSet s = Source();

		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			return new CNTPoint(s, t);
		}

		return super.contain(c);
	}
	
	@Override
	public ConvexSet Source()
	{
		return (ConvexSet) super.Source();
	}
}