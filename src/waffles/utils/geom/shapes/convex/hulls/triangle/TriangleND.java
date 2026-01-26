package waffles.utils.geom.shapes.convex.hulls.triangle;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.convex.hulls.HullND;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code TriangleND} implements n-dimensional {@code Triangle} geometry.
 *
 * @author Waffles
 * @since 13 Jan 2021
 * @version 1.0
 * 
 * 
 * @see Triangle
 * @see HullND
 */
public class TriangleND extends HullND implements Triangle
{	
	/**
	 * Creates a new {@code TriangleND}.
	 * 
	 * @param p1  a triangle point
	 * @param p2  a triangle point
	 * @param p3  a triangle point
	 * 
	 * 
	 * @see Point
	 */
	public TriangleND(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
	}
	
	/**
	 * Creates a new {@code TriangleND}.
	 * 
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public TriangleND(Matrix s)
	{
		super(s);
	}
}