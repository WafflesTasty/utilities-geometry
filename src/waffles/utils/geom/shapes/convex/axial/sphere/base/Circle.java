package waffles.utils.geom.shapes.convex.axial.sphere.base;

import waffles.utils._todo.utilities.constants.Dial;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Circle} implements two-dimensional {@code HyperSphere} geometry.
 *
 * @author Waffles
 * @since Mar 21, 2017
 * @version 1.0
 *
 *
 * @see HyperSphere2D
 * @see Ellipse
 */
public class Circle extends Ellipse implements HyperSphere2D
{
	/**
	 * Creates a circle through three two-dimensional points.
	 * This method returns null if the points are colinear.
	 *
	 * @param a  a circle point
	 * @param b  a circle point
	 * @param c  a circle point
	 * @return  a tangent circle
	 *
	 *
	 * @see Point
	 */
	public static Circle through(Point a, Point b, Point c)
	{
		if(Dial.isColinear(a, b, c))
		{
			return null;
		}

		float x1 = a.aff(0);
		float x2 = b.aff(0);
		float x3 = c.aff(0);
		float y1 = a.aff(1);
		float y2 = b.aff(1);
		float y3 = c.aff(1);

		float x31 = x3 - x1;
		float x23 = x2 - x3;
		float x12 = x1 - x2;
		float y21 = y2 - y1;
		float y32 = y3 - y2;
		float y13 = y1 - y3;

		float as = a.normSqr();
		float bs = b.normSqr();
		float cs = c.normSqr();

		float d = (x1 * y32 + x2 * y13 + x3 * y21) * 2;
		float x = (as * y32 + bs * y13 + cs * y21) / d;
		float y = (as * x23 + bs * x31 + cs * x12) / d;

		
		Point o = new Point(x, y, 1f);
		return new Circle(o, o.dist(a));
	}


	/**
	 * Creates a new {@code Circle}.
	 *
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param r  a circle radius
	 */
	public Circle(float x, float y, float r)
	{
		this(new Point(x, y, 1f), r);
	}

	/**
	 * Creates a new {@code Circle}.
	 *
	 * @param o  an origin point
	 * @param r  a circle radius
	 *
	 *
	 * @see Point
	 */
	public Circle(Point o, float r)
	{
		super(o, Arrow.create(2 * r, 2));
	}

	/**
	 * Creates a new {@code Circle}.
	 *
	 * @param r  a circle radius
	 */
	public Circle(float r)
	{
		this(new Point(2), r);
	}

	/**
	 * Creates a new {@code Circle}.
	 */
	public Circle()
	{
		this(1f);
	}
}