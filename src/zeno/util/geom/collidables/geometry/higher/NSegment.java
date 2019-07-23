package zeno.util.geom.collidables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ISegment;

/**
 * The {@code NSegment} class defines an n-dimensional line segment.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ISegment
 */
public class NSegment implements ISegment
{	
	private Point p1, p2;
		
	/**
	 * Creates a new {@code NSegment}.
	 * 
	 * @param p1  a segment endpoint
	 * @param p2  a segment endpoint
	 * 
	 * 
	 * @see Point
	 */
	public NSegment(Point p1, Point p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Creates a new {@code NSegment}.
	 * 
	 * @param v1  a segment endpoint
	 * @param v2  a segment endpoint
	 * 
	 * 
	 * @see Vector
	 */
	public NSegment(Vector v1, Vector v2)
	{
		this(new Point(v1), new Point(v2));
	}
	
	/**
	 * Creates a new {@code NSegment}.
	 * The parameter takes in a list containing
	 * the components of both points in sequence.
	 * 
	 * @param vals  a list of values
	 */
	public NSegment(float... vals)
	{
		Vector[] split = Vectors.split(2, vals);
		
		p1 = new Point(split[0]);
		p2 = new Point(split[1]);
	}
	
	/**
	 * Creates a new {@code NSegment}.
	 * The created line spans the unit cube diagonal.
	 * 
	 * @param dim  a line dimension
	 */
	public NSegment(int dim)
	{
		this(Vectors.create(-1f, dim), Vectors.create(1f, dim));
	}


	
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 17 + p1.hashCode();
		code = code * 43 + p2.hashCode();
		
		return code;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof NSegment)
		{
			NSegment oLine = (NSegment) o;
			return (p1.equals(oLine.p1)
				 && p2.equals(oLine.p2))
				 ||(p1.equals(oLine.p2)
				 && p2.equals(oLine.p1));
		}
		
		return false;
	}

		
	@Override
	public Point P1()
	{
		return p1;
	}

	@Override
	public Point P2()
	{
		return p2;
	}
}