package waffles.utils.geom.shapes.collision.linear;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.CNTPoint;
import waffles.utils.geom.shapes.response.RSPFlipped;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSPoint} defines {@code Collision} for a {@code Point}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 *
 *
 * @see Collision
 */
public class CLSPoint extends Collision
{
	/**
	 * Creates a new {@code CLSPoint}.
	 *
	 * @param p  a source point
	 *
	 *
	 * @see Point
	 */
	public CLSPoint(Point p)
	{
		this(p, Doubles.pow(2, -16));
	}

	/**
	 * Creates a new {@code CLSPoint}.
	 *
	 * @param p  a source point
	 * @param e  an error margin
	 * 
	 *
	 * @see Point
	 */
	public CLSPoint(Point p, double e)
	{
		super(p, e);
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		Point p = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point q = (Point) c;
			return new CNTPoint(p, q);
		}

		// Points can't contain
		// anything besides points.
		return () -> p;
	}

	@Override
	public Response intersect(Collidable c)
	{
		// Intersections with points are containments.
		return new RSPFlipped(c.contain(Source()));
	}

	@Override
	public Response inhabit(Collidable c)
	{
		// Points don't worry about it.
		return () -> Source();
	}
		
	@Override
	public Point Source()
	{
		return (Point) super.Source();
	}
}