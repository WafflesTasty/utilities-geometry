package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.Affine;

/**
 * The {@code ASpace} class defines a standard euclidian affine space.
 * Affine spaces are immutable objects designed to produce various bases,
 * and allow for intersection and containment checks.
 *
 * @author Zeno
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class ASpace implements Affine.Space
{
	private VSpace space;
	private Point origin;
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an origin point
	 * @param s  a direction space
	 * 
	 * 
	 * @see Point
	 * @see VSpace
	 */
	public ASpace(Point o, VSpace s)
	{
		origin = o;
		space = s;
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an origin point
	 * @param v  a direction vector
	 * 
	 * 
	 * @see Point
	 * @see Vector
	 */
	public ASpace(Point o, Vector v)
	{
		space = new VSpace(v);
		origin = o;
	}
	
	
	@Override
	public Point Origin()
	{
		return origin;
	}
	
	@Override
	public VSpace Direction()
	{
		return space;
	}

	@Override
	public Affine.Set Span()
	{
		Matrix span = Direction().Span();
		Matrix o = Origin().VMatrix();
		
		int cols = span.Columns();
		int rows = span.Rows();
		
		span = Matrices.resize(span, rows, cols + 1);
		for(int c = 0; c <= cols; c++)
		{
			for(int r = 0; r < rows; r++)
			{
				float val = span.get(r, c) + o.get(r);
				span.set(val, r, c);
			}
		}
		
		return ASpaces.vset(span);
	}
}