package waffles.utils.geom.collision.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.convex.hulls.segments.Segment;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.response.hulls.segments.CNTPoint;

/**
 * The {@code CLSSegment} class defines collision responses for {@code Segment} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSHull
 */
public class CLSSegment extends CLSHull
{	
	/**
	 * Creates a new {@code CLSSegment}.
	 * 
	 * @param s  a source segment
	 * 
	 * 
	 * @see Segment
	 */
	public CLSSegment(Segment s)
	{
		super(s);
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			Segment s = Source();
			return new CNTPoint(s, p);
		}
		
		return super.contain(c);
	}
	
	@Override
	public Segment Source()
	{
		return (Segment) super.Source();
	}
}