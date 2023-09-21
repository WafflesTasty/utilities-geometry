package waffles.utils.geom.collision.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.hulls.segments.ISegment;
import waffles.utils.geom.collision.hulls.segments.CNTPoint;

/**
 * The {@code CLSSegment} class defines collision responses for {@code ISegment} objects.
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
	 * @see ISegment
	 */
	public CLSSegment(ISegment s)
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
			ISegment s = Source();
			return new CNTPoint(s, p);
		}
		
		return super.contain(c);
	}
	
	@Override
	public ISegment Source()
	{
		return (ISegment) super.Source();
	}
}