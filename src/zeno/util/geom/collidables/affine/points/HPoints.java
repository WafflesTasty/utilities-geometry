package zeno.util.geom.collidables.affine.points;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;

/**
 * The {@code HPoints} class defines an affine set from a homogeneous matrix.
 *
 * @author Zeno
 * @since Jul 14, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class HPoints implements Affine.Set
{
	private Matrix hmat;
	
	/**
	 * Creates a new {@code HPoints}.
	 * 
	 * @param m  an affine matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public HPoints(Matrix m)
	{
		hmat = m;
	}
	
	
	@Override
	public Matrix VMatrix()
	{
		return ASpaces.vectorize(hmat);
	}

	@Override
	public Matrix HMatrix()
	{
		return hmat;
	}

	@Override
	public int Size()
	{
		return hmat.Columns();
	}
}