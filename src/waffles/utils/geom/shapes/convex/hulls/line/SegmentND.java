package waffles.utils.geom.shapes.convex.hulls.line;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code SegmentND} implements n-dimensional {@code Segment} geometry.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see Segment
 */
public class SegmentND implements Segment
{	
	private Factory fct;
	
	/**
	 * Creates a new {@code SegmentND}.
	 * 
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public SegmentND(Matrix s)
	{
		fct = new Factory(s);
	}
	
	/**
	 * Creates a new {@code SegmentND}.
	 * 
	 * @param p  a segment point
	 * @param q  a segment point
	 * 
	 * 
	 * @see Point
	 */
	public SegmentND(Point p, Point q)
	{
		fct = new Factory(p, q);
	}

		
	@Override
	public Factory Factory()
	{
		return fct;
	}
	
	@Override
	public Point Origin()
	{
		return P1();
	}
}