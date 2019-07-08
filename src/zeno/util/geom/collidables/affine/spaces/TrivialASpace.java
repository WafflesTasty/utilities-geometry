package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.affine.ASpace;

/**
 * The {@code TrivialASpace} class defines a trivial affine space.
 * Its dimension equals -1, containing no points.
 *
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public class TrivialASpace extends ASpace
{
	private int coords;
	
	/**
	 * Creates a new {@code TrivialASpace}.
	 * 
	 * @param coords  a coördinate count
	 */
	public TrivialASpace(int coords)
	{
		this.coords = coords;
	}

	
	@Override
	public Vector Origin()
	{
		return Vectors.create(coords);
	}
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.trivial(coords);
	}
	
	@Override
	public Matrix Complement()
	{
		return Direction().Complement();
	}
	
	@Override
	public Matrix Span()
	{
		return Direction().Span();
	}
		
	
	@Override
	public Matrix AMatrix()
	{
		return Matrices.create(coords + 1, 0);
	}
	
	@Override
	public Matrix VMatrix()
	{
		return Matrices.create(coords, 0);
	}
	
	@Override
	public int Dimension()
	{
		return -1;
	}
	
		
	@Override
	public ASpace intersect(ASpace s)
	{
		return this;
	}
	
	@Override
	public ASpace add(ASpace s)
	{
		return s;
	}
	
	
	
	
	@Override
	public boolean contains(Vector v)
	{
		return false;
	}

	@Override
	public boolean equals(ASpace s, int ulps)
	{
		return s.Dimension() == -1;
	}
	
	@Override
	public boolean intersects(ASpace s)
	{
		return false;
	}
	
	@Override
	public boolean contains(ASpace s)
	{
		return false;
	}
}