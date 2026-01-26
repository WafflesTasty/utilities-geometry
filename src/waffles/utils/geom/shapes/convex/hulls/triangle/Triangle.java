package waffles.utils.geom.shapes.convex.hulls.triangle;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Triangle} defines a three-point hull in n-dimensional space.
 *
 * @author Waffles
 * @since 13 Jan 2021
 * @version 1.0
 *
 *
 * @see Hull
 */
public interface Triangle extends Hull
{
	/**
	 * Creates a {@code Triangle} from three points.
	 * 
	 * @param p  a triangle point
	 * @param q  a triangle point
	 * @param r  a triangle point
	 * @return   a triangle
	 * 
	 * 
	 * @see Point
	 */
	public static Triangle create(Point p, Point q, Point r)
	{
		switch(p.Dimension())
		{
		case 2:
			return new Triangle2D(p, q, r);
		case 3:
			return new Triangle3D(p, q, r);
		default:
			return new TriangleND(p, q, r);
		}
	}
	
	/**
	 * Creates a {@code Triangle} from a matrix span.
	 * 
	 * @param s  a matrix span
	 * @return   a line segment
	 * 
	 * 
	 * @see Matrix
	 */
	public static Triangle create(Matrix s)
	{
		switch(s.Rows())
		{
		case 3:
			return new Triangle2D(s);
		case 4:
			return new Triangle3D(s);
		default:
			return new TriangleND(s);
		}
	}
	
	
	/**
	 * Returns the first point of the {@code Triangle}.
	 *
	 * @return  a triangle point
	 *
	 *
	 * @see Point
	 */
	public default Point P1()
	{
		return Factory().Point(0);
	}

	/**
	 * Returns the second point of the {@code Triangle}.
	 *
	 * @return  a triangle point
	 *
	 *
	 * @see Point
	 */
	public default Point P2()
	{
		return Factory().Point(1);
	}

	/**
	 * Returns the third point of the {@code Triangle}.
	 *
	 * @return  a triangle point
	 *
	 *
	 * @see Point
	 */
	public default Point P3()
	{
		return Factory().Point(2);
	}
}