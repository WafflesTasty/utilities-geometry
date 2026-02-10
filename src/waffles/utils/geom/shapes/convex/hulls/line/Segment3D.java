package waffles.utils.geom.shapes.convex.hulls.line;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.Geometry3D;
import waffles.utils.geom.shapes.bounds.convex.hulls.BNDHull3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code Segment3D} implements three-dimensional {@code Segment} geometry.
 *
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 *
 *
 * @see Geometry3D
 * @see SegmentND
 */
public class Segment3D extends SegmentND implements Geometry3D
{
	/**
	 * Creates a new {@code Segment3D}.
	 *
	 * @param x1  an x-coordinate
	 * @param y1  an y-coordinate
	 * @param z1  an z-coordinate
	 * @param x2  an x-coordinate
	 * @param y2  an y-coordinate
	 * @param z2  an z-coordinate
	 */
	public Segment3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		this(new Point(x1, y1, z1, 1f), new Point(x2, y2, z2, 1f));
	}

	/**
	 * Creates a new {@code Segment3D}.
	 *
	 * @param p  a segment point
	 * @param q  a segment point
	 *
	 *
	 * @see Point
	 */
	public Segment3D(Point p, Point q)
	{
		super(p, q);
	}
	
	/**
	 * Creates a new {@code Segment3D}.
	 *
	 * @param s  a matrix span
	 *
	 *
	 * @see Matrix
	 */
	public Segment3D(Matrix s)
	{
		super(s);
	}


	/**
	 * Returns a first x-coordinate of the {@code Segment3D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return P1().aff(0);
	}

	/**
	 * Returns a first y-coordinate of the {@code Segment3D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return P1().aff(1);
	}

	/**
	 * Returns a first z-coordinate of the {@code Segment3D}.
	 *
	 * @return  an z-coordinate
	 */
	public float Z1()
	{
		return P1().aff(2);
	}

	/**
	 * Returns a second x-coordinate of the {@code Segment3D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return P2().aff(0);
	}

	/**
	 * Returns a second y-coordinate of the {@code Segment3D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return P2().aff(1);
	}

	/**
	 * Returns a second z-coordinate of the {@code Segment3D}.
	 *
	 * @return  an z-coordinate
	 */
	public float Z2()
	{
		return P2().aff(2);
	}


	@Override
	public Bounds3D Bounds()
	{
		return new BNDHull3D(this);
	}
	
	@Override
	public int Dimension()
	{
		return 3;
	}
}