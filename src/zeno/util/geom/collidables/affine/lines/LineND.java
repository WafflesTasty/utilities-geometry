package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.APoint;

/**
 * The {@code LineND} class defines an n-dimensional line space.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public class LineND extends ASpace
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
		this(new APoint(p1), p2.minus(p1));
	}
	
	/**
	 * Creates a new {@code LineND}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see APoint
	 * @see Vector
	 */
	public LineND(APoint p1, Vector p2)
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
	 * @see APoint
	 */
	public LineND(APoint p1, APoint p2)
	{
		this(p1, p2.minus(p1));
	}
}