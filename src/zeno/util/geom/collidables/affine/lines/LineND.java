package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.Point;

/**
 * The {@code LineND} class defines an n-dimensional line space.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ASpace
 * @see ILine
 */
public class LineND extends ASpace implements ILine
{			
	/**
	 * Creates a new {@code LineND}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Vector
	 */
	public LineND(Vector p1, Vector p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code LineND}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Point
	 */
	public LineND(Point p1, Point p2)
	{
		this(p1.VMatrix(), p2.VMatrix());
	}
	
			
	@Override
	public Vector P1()
	{
		return VMatrix().Column(0);
	}

	@Override
	public Vector P2()
	{
		return VMatrix().Column(1);
	}
}