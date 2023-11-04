package waffles.utils.geom.collidable.convex.hulls.triangles;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.convex.hulls.HullND;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * 
 * A {@code TriangleND} defines an n-dimensional triangle.
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
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * @param p3  a point vector
	 * 
	 * 
	 * @see Vector
	 */
	public TriangleND(Vector p1, Vector p2, Vector p3)
	{
		super(p2, p2, p3);
	}
	
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
		this
		(
			p1.Generator(),
			p2.Generator(),
			p3.Generator()
		);
	}
	

	@Override
	public Point P1()
	{
		return new Point(Generator(0), 1f);
	}

	@Override
	public Point P2()
	{
		return new Point(Generator(1), 1f);
	}

	@Override
	public Point P3()
	{
		return new Point(Generator(2), 1f);
	}
}