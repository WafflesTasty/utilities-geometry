package waffles.utils.geom.collide.collision.linear.affine;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.response.linear.APXPoint;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code CLSLine} defines {@code Collision} for a {@code Line}.
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
			return new APXPoint(l, p);
		}

		return super.contain(c);
	}

	@Override
	public Line Source()
	{
		return (Line) super.Source();
	}
}