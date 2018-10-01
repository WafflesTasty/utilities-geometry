package zeno.util.geom.collideables.geometry.higher.lines;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collideables.geometry.Line;

/**
 * The {@code Line} class defines an n-dimensional line segment.
 * 
 * @since Jul 5, 2016
 * @author Zeno
 * 
 * @see Line
 */
public class NLine implements Line
{
	private Vector p1, p2;
		
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @see Vector
	 */
	public NLine(Vector p1, Vector p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Creates a new {@code Line}.
	 * The parameter takes in a list containing
	 * the components of both points in sequence.
	 * 
	 * @param vals  a list of values
	 */
	public NLine(float... vals)
	{
		Vector[] split = Vectors.split(2, vals);
		
		p1 = split[0];
		p2 = split[1];
	}
	
	/**
	 * Creates a new {@code Line}.
	 * The created line fits in the unit cube.
	 * 
	 * @param dim  the line's dimension
	 */
	public NLine(int dim)
	{
		this(Vectors.create(-0.5f, dim), Vectors.create(0.5f, dim));
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
		if(o instanceof NLine)
		{
			NLine oLine = (NLine) o;
			return (p1.equals(oLine.p1)
				 && p2.equals(oLine.p2))
				 ||(p1.equals(oLine.p2)
				 && p2.equals(oLine.p1));
		}
		
		return false;
	}
	
	
	@Override
	public Vector P1()
	{
		return p1;
	}

	@Override
	public Vector P2()
	{
		return p2;
	}
}