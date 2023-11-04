package waffles.utils.geom.collidable.convex.hulls.segments;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collision.convex.hulls.CLSSegment;

/**
 * The {@code Segment} interface is the base for convex hulls of two point sets.
 * 
 * @author Waffles
 * @since Mar 25, 2017
 * @version 1.0
 * 
 * 
 * @see Hull
 */
public interface Segment extends Hull
{		
	/**
	 * Returns the first point of the {@code Segment}.
	 * 
	 * @return  a segment point
	 * 
	 * 
	 * @see Point
	 */
	public default Point P1()
	{
		Vector v = Generator().Column(0);
		return new Point(v, 1f);
	}
	
	/**
	 * Returns the second point of the {@code Segment}.
	 * 
	 * @return  a segment point
	 * 
	 * 
	 * @see Point
	 */
	public default Point P2()
	{
		Vector v = Generator().Column(1);
		return new Point(v, 1f);
	}


	@Override
	public default CLSSegment Collisions()
	{
		return new CLSSegment(this);
	}
}