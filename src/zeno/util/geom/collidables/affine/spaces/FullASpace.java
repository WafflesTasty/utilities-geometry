package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.affine.ASpace;

/**
 * The {@code FullASpace} class defines an entire affine space.
 * Its dimension equals the amount of coördinates being used.
 *
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public class FullASpace extends ASpace
{
	private int dim;
	
	/**
	 * Creates a new {@code FullASpace}.
	 * 
	 * @param coords  a coördinate count
	 */
	public FullASpace(int coords)
	{
		dim = coords;
	}

	
	@Override
	public Vector Origin()
	{
		return Vectors.create(dim);
	}
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.full(dim);
	}
	
	@Override
	public Matrix VMatrix()
	{
		return Direction().Span();
	}
	
	
	@Override
	public Matrix Span()
	{
		return Direction().Span();
	}
	
	@Override
	public Matrix Complement()
	{
		return Direction().Complement();
	}
	
	@Override
	public ASpace intersect(ASpace s)
	{
		return s;
	}
	
	@Override
	public ASpace add(ASpace s)
	{
		return this;
	}
	
	@Override
	public int Dimension()
	{
		return dim;
	}
	
	
	@Override
	public boolean contains(Vector v)
	{
		return true;
	}
			
	@Override
	public boolean intersects(ASpace s)
	{
		return true;
	}
	
	@Override
	public boolean contains(ASpace s)
	{
		return true;
	}
	
	
	@Override
	public boolean equals(ASpace s, int ulps)
	{
		return s.Dimension() == -1;
	}
	
	@Override
	public ASpace instance()
	{
		return this;
	}
}