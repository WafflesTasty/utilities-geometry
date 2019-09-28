package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.algorithms.lsquares.LSQSVD;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.types.orthogonal.Orthogonal;
import zeno.util.geom.ITransformation;

/**
 * The {@code Rotation} class defines an affine rotation.
 * It is defined as a homogeneous linear transformation
 * through the {@code ITransformation} interface.
 *
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public class Rotation implements ITransformation
{
	private static Matrix DefaultRotation(int dim)
	{
		return Matrices.identity(dim);
	}
	
	
	private Matrix basis, mat;
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param dim  a space dimension
	 */
	public Rotation(int dim)
	{
		this(DefaultRotation(dim));
	}
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param b  a basis matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Rotation(Matrix b)
	{
		basis = new LSQSVD(b).NearestOrthogonal();
		basis.setOperator(Orthogonal.Type());
		mat = Matrices.identity(0);
	}
		
	/**
	 * Returns the rotation basis.
	 * 
	 * @return  a basis matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix Basis()
	{
		return basis;
	}

	
	@Override
	public Matrix Inverse(int dim)
	{
		if(mat.Rows() != dim + 1)
		{
			mat = Matrices.resize(basis, dim + 1, dim + 1);
			mat = new LSQSVD(mat).NearestOrthogonal();
			mat.setOperator(Orthogonal.Type());
		}
		
		return mat.transpose();
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		if(mat.Rows() != dim + 1)
		{
			mat = Matrices.resize(basis, dim + 1, dim + 1);
			mat = new LSQSVD(mat).NearestOrthogonal();
			mat.setOperator(Orthogonal.Type());
		}
		
		return mat;
	}
}