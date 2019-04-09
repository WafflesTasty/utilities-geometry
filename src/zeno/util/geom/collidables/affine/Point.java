package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;

/**
 * The {@code Point} class defines a zero-dimensional affine space.
 *
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public class Point extends ASpace
{	
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param coords  a coördinate count
	 */
	public Point(int coords)
	{
		this(Vectors.create(coords));
	}
	
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param vals  a set of vector values
	 */
	public Point(float... vals)
	{
		this(Vectors.create(vals));
	}
	
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param v  a vector point
	 * 
	 * 
	 * @see Vector
	 */
	public Point(Vector v)
	{
		super(v, VSpaces.trivial(v.Size()));
	}
	
	
	@Override
	public Vector AMatrix()
	{
		return (Vector) super.AMatrix();
	}
	
	@Override
	public Vector VMatrix()
	{
		return (Vector) super.VMatrix();
	}
	
	

	@Override
	public Matrix Span()
	{
		return AMatrix();
	}
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.trivial(Origin().Size());
	}
	
	@Override
	public ASpace intersect(ASpace s)
	{
		if(!s.contains(this))
		{
			return ASpaces.trivial(Origin().Size());
		}
		
		return this;
	}
		
	@Override
	public int Dimension()
	{
		return 0;
	}
}