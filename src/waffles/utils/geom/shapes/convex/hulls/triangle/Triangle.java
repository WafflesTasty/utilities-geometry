package waffles.utils.geom.shapes.convex.hulls.triangle;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Transformator;

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
	 * @return   a transformator
	 * 
	 * 
	 * @see Transformator
	 * @see Matrix
	 */
	public static Transformator create(Matrix s)
	{
		if(s.Columns() == 0)
		{
			return new Void(s.Rows());
		}

		if(s.Columns() == 3)
		{
			Point p = Point.create(s.Column(0));
			Point q = Point.create(s.Column(1));
			Point r = Point.create(s.Column(2));
			
			return Triangle.create(p, q, r);
		}
		
		return null;
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