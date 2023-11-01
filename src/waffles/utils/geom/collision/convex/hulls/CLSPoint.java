package waffles.utils.geom.collision.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.response.fixed.RSPFlip;
import waffles.utils.geom.response.hulls.points.CNTPoint;

/**
 * The {@code CLSPoint} class defines collision responses for {@code Point} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSHull
 */
public class CLSPoint extends CLSHull
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
		super(p);
	}
	
			
	@Override
	public Response contain(Collidable c)
	{
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = Source();
			Point q = (Point) c;
			
			return new CNTPoint(p,q);
		}
		
		// Points can't contain
		// anything besides points.
		return () -> false;
	}
	
	@Override
	public Response intersect(Collidable c)
	{
		return new RSPFlip(c.contain(Source()));
	}
	
	@Override
	public Response inhabit(Collidable c)
	{
		// Points don't worry about it.
		return () -> false;
	}
	
	@Override
	public Point Source()
	{
		return (Point) super.Source();
	}
}