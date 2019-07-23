package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.affine.CLSATrivial;

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
	 */
	public TrivialASpace()
	{
		direction = VSpaces.trivial(0);
	}

	
	@Override
	public VSpace Direction()
	{
		return direction;
	}
	
	@Override
	public ICollision Collisions()
	{
		return new CLSATrivial();
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