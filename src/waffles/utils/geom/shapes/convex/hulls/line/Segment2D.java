package waffles.utils.geom.shapes.convex.hulls.line;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.Geometry2D;
import waffles.utils.geom.shapes.bounds.convex.hulls.BNDHull2D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code Segment2D} implements two-dimensional {@code Segment} geometry.
 *
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 *
 *
 * @see Geometry2D
 * @see SegmentND
 */
public class Segment2D extends SegmentND implements Geometry2D
{
	/**
	 * Creates a new {@code Segment2D}.
	 *
	 * @param x1  an x-coordinate
	 * @param y1  an y-coordinate
	 * @param x2  an x-coordinate
	 * @param y2  an y-coordinate
	 */
	public Segment2D(float x1, float y1, float x2, float y2)
	{
		this(new Point(x1, y1, 1f), new Point(x2, y2, 1f));
	}

	/**
	 * Creates a new {@code Segment2D}.
	 *
	 * @param p  a segment point
	 * @param q  a segment point
	 *
	 *
	 * @see Point
	 */
	public Segment2D(Point p, Point q)
	{
		super(p, q);
	}
	
	/**
	 * Creates a new {@code Segment2D}.
	 *
	 * @param s  a matrix span
	 *
	 *
	 * @see Matrix
	 */
	public Segment2D(Matrix s)
	{
		super(s);
	}


	/**
	 * Returns a first x-coordinate of the {@code Segment2D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X1()
	{
		return P1().aff(0);
	}

	/**
	 * Returns a first y-coordinate of the {@code Segment2D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y1()
	{
		return P1().aff(1);
	}

	/**
	 * Returns a second x-coordinate of the {@code Segment2D}.
	 *
	 * @return  an x-coordinate
	 */
	public float X2()
	{
		return P2().aff(0);
	}

	/**
	 * Returns a second y-coordinate of the {@code Segment2D}.
	 *
	 * @return  an y-coordinate
	 */
	public float Y2()
	{
		return P2().aff(1);
	}
	
	
	@Override
	public int Dimension()
	{
		return 2;
	}

	@Override
	public Bounds2D Bounds(LinearMap m)
	{
		return (Bounds2D) super.Bounds(m);
	}
	
	@Override
	public Bounds2D Bounds()
	{
		return new BNDHull2D(this);
	}
}