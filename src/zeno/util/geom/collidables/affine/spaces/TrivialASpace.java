package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.APoint;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.ASpaces;

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
	 * @param size  a coördinate count
	 */
	public TrivialASpace(int size)
	{
		super(null, VSpaces.trivial(size));
		coords = size;
	}

	
	@Override
	public APoint Origin()
	{
		return null;
	}
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.trivial(coords);
	}
	
	@Override
	public Affine.Set Span()
	{
		return ASpaces.vset(Matrices.identity(0));
	}
	
	@Override
	public int Dimension()
	{
		return -1;
	}
		
		
	@Override
	public Affine intersect(Affine s)
	{
		return this;
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
	public boolean contains(Affine s)
	{
		return false;
	}
}