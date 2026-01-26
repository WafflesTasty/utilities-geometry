package waffles.utils.geom.shapes.convex.hulls.triangle;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.convex.hulls.Hull2D;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Triangle2D} implements two-dimensional {@code Triangle} geometry.
 *
 * @author Waffles
 * @since 13 Jan 2021
 * @version 1.0
 *
 *
 * @see Triangle
 * @see Hull2D
 */
public class Triangle2D extends Hull2D implements Triangle
{
	/**
	 * Creates a new {@code Triangle2D}.
	 *
	 * @param x1  an x-coordinate
	 * @param y1  an y-coordinate
	 * @param x2  an x-coordinate
	 * @param y2  an y-coordinate
 	 * @param x3  an x-coordinate
	 * @param y3  an y-coordinate
	 */
	public Triangle2D(float x1, float y1, float x2, float y2, float x3, float y3)
	{
		this(new Point(x1, y1, 1f), new Point(x2, y2, 1f), new Point(x3, y3, 1f));
	}

	/**
	 * Creates a new {@code Triangle2D}.
	 *
	 * @param p1  a triangle point
	 * @param p2  a triangle point
	 * @param p3  a triangle point
	 *
	 *
	 * @see Point
	 */
	public Triangle2D(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
	}

	/**
	 * Creates a new {@code Triangle2D}.
	 * 
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public Triangle2D(Matrix s)
	{
		super(s);
	}

	
	/**
	 * Returns a first x-coordinate of the {@code Triangle2D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return P1().aff(0);
	}

	/**
	 * Returns a first y-coordinate of the {@code Triangle2D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return P1().aff(1);
	}

	/**
	 * Returns a second x-coordinate of the {@code Triangle2D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return P2().aff(0);
	}

	/**
	 * Returns a second y-coordinate of the {@code Triangle2D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return P2().aff(1);
	}

	/**
	 * Returns a third x-coordinate of the {@code Triangle2D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X3()
	{
		return P3().aff(0);
	}

	/**
	 * Returns a third y-coordinate of the {@code Triangle2D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y3()
	{
		return P3().aff(1);
	}
}