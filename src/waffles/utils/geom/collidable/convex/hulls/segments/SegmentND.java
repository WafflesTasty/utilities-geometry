package waffles.utils.geom.collidable.convex.hulls.segments;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.convex.hulls.HullND;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code SegmentND} defines an n-dimensional line segment.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see Segment
 * @see HullND
 */
public class SegmentND extends HullND implements Segment
{	
	/**
	 * Creates a new {@code SegmentND}.
	 * 
	 * @param p1  a point vector
	 * @param p2  a point vector
	 * 
	 * 
	 * @see Vector
	 */
	public SegmentND(Vector p1, Vector p2)
	{
		super(p1, p2);
	}
	
	/**
	 * Creates a new {@code SegmentND}.
	 * 
	 * @param p1  a segment point
	 * @param p2  a segment point
	 * 
	 * 
	 * @see Point
	 */
	public SegmentND(Point p1, Point p2)
	{
		this(p1.Generator(), p2.Generator());
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
}