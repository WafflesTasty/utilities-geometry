package waffles.utils.geom.collision.spaces.lines;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.collision.spaces.CLSASpace;
import waffles.utils.geom.response.lines.CNTPoint;

/**
 * The {@code CLSLine} class defines collision responses for {@code Line} objects.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 * 
 * 
 * @see CLSASpace
 */
public class CLSLine extends CLSASpace
{
	/**
	 * Creates a new {@code CLSLine}.
	 * 
	 * @param l  a source line
	 * 
	 * 
	 * @see Line
	 */
	public CLSLine(Line l)
	{
		super(l);
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		Line l = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(l, p);
		}

		return super.contain(c);
	}
	
	@Override
	public Line Source()
	{
		return (Line) super.Source();
	}
}