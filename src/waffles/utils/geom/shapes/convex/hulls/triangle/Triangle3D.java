package waffles.utils.geom.shapes.convex.hulls.triangle;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.convex.hulls.Hull3D;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Triangle3D} implements three-dimensional {@code Triangle} geometry.
 *
 * @author Waffles
 * @since 13 Jan 2021
 * @version 1.0
 *
 *
 * @see Triangle
 * @see Hull3D
 */
public class Triangle3D extends Hull3D implements Triangle
{
	/**
	 * Creates a new {@code Triangle3D}.
	 *
	 * @param x1  an x-coordinate
	 * @param y1  an y-coordinate
	 * @param z1  an z-coordinate
	 * @param x2  an x-coordinate
	 * @param y2  an y-coordinate
	 * @param z2  an z-coordinate
 	 * @param x3  an x-coordinate
	 * @param y3  an y-coordinate
	 * @param z3  an z-coordinate
	 */
	public Triangle3D(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3)
	{
		this(new Point(x1, y1, z1, 1f), new Point(x2, y2, z2, 1f), new Point(x3, y3, z3, 1f));
	}

	/**
	 * Creates a new {@code Triangle3D}.
	 *
	 * @param p1  a triangle point
	 * @param p2  a triangle point
	 * @param p3  a triangle point
	 *
	 *
	 * @see Point
	 */
	public Triangle3D(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
	}

	/**
	 * Creates a new {@code Triangle3D}.
	 * 
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public Triangle3D(Matrix s)
	{
		super(s);
	}


	/**
	 * Returns a first x-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return P1().aff(0);
	}

	/**
	 * Returns a first y-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return P1().aff(1);
	}

	/**
	 * Returns a first z-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an z-coordinate
	 */
	public float Z1()
	{
		return P1().aff(2);
	}

	/**
	 * Returns a second x-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return P2().aff(0);
	}

	/**
	 * Returns a second y-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return P2().aff(1);
	}

	/**
	 * Returns a second z-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an z-coordinate
	 */
	public float Z2()
	{
		return P2().aff(2);
	}

	/**
	 * Returns a third x-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X3()
	{
		return P3().aff(0);
	}

	/**
	 * Returns a third y-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y3()
	{
		return P3().aff(1);
	}

	/**
	 * Returns a third z-coordinate of the {@code Triangle3D}.
	 *
	 * @return  an z-coordinate
	 */
	public float Z3()
	{
		return P3().aff(2);
	}
}