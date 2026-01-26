package waffles.utils.geom.shapes.linear.affine.lines;

import waffles.utils.alg.lin.measure.vector.fixed.Vector2;
import waffles.utils.geom.Collideable2D;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Line2D} defines an affine line in two-dimensional space.
 *
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 *
 *
 * @see Collideable2D
 * @see Line
 */
public class Line2D extends Line implements Collideable2D
{
	/**
	 * Creates a new {@code Line2D}.
	 *
	 * @param x1  an x-coordinate
	 * @param y1  a  y-coordinate
	 * @param x2  an x-coordinate
	 * @param y2  a  y-coordinate
	 */
	public Line2D(float x1, float y1, float x2, float y2)
	{
		this(new Point(x1, y1, 1f), new Point(x2, y2, 1f));
	}

	/**
	 * Creates a new {@code Line2D}.
	 *
	 * @param p1  a point vector
	 * @param p2  a point vector
	 *
	 *
	 * @see Vector2
	 */
	public Line2D(Vector2 p1, Vector2 p2)
	{
		super(new Point(p1, 1f), new Point(p2, 1f));
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 *
	 * @param p1  a line point
	 * @param p2  a line point
	 *
	 *
	 * @see Point
	 */
	public Line2D(Point p1, Point p2)
	{
		super(p1, p2);
	}
}