package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.points.Point;

/**
 * The {@code TrivialASpace} class defines a trivial affine space.
 * Its dimension equals -1, containing no points.
 *
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class TrivialASpace implements Affine.Space, Affine.Set
{
	private VSpace direction;
	
	/**
	 * Creates a new {@code TrivialASpace}.
	 * 
	 * @param size  a coördinate size
	 */
	public TrivialASpace(int size)
	{
		direction = VSpaces.trivial(size);
	}

	
	@Override
	public boolean contains(Point p)
	{
		return false;
	}
	
	@Override
	public boolean contains(Affine s)
	{
		return false;
	}
	
	@Override
	public boolean equals(Affine a, int ulps)
	{
		return a.isEmpty();
	}
	
	@Override
	public boolean intersects(Affine s)
	{
		return false;
	}
	
	@Override
	public Affine intersect(Affine s)
	{
		return this;
	}
	
	
	@Override
	public VSpace Direction()
	{
		return direction;
	}
	
	@Override
	public Affine.Set Span()
	{
		return ASpaces.vset(Matrices.identity(0));
	}
	
	@Override
	public Point Origin()
	{
		return null;
	}


	@Override
	public Matrix VMatrix()
	{
		return direction.Span();
	}
	
	@Override
	public Matrix HMatrix()
	{
		return ASpaces.homogenize(VMatrix());
	}
	
	@Override
	public boolean isFinite()
	{
		return true;
	}
	
	@Override
	public boolean isEmpty()
	{
		return true;
	}

	@Override
	public int Size()
	{
		return 0;
	}
}