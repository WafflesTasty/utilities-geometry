package waffles.utils.geom.collide.collision.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.response.convex.hulls.segments.CNTPoint;
import waffles.utils.geom.shapes.convex.hulls.line.Segment;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSSegment} defines {@code Collision} for a {@code Segment}.
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
		this(s, Doubles.pow(2, -8));
	}
	
	/**
	 * Creates a new {@code CLSSegment}.
	 *
	 * @param s  a source segment
	 * @param e  an error margin
	 * 
	 *
	 * @see CLSSegment
	 */
	public CLSSegment(Segment s, double e)
	{
		super(s, e);
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		Segment s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point x = (Point) c;
			return new CNTPoint(s, x);
		}
		
		return super.contain(c);
	}
	
	@Override
	public Segment Source()
	{
		return (Segment) super.Source();
	}
}