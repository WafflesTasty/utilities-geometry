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
	
	
	private Matrix mat, basis;
	
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
		basis = Matrices.identity(0);
		mat = b;
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
		return mat;
	}

	
	@Override
	public Matrix Inverse(int dim)
	{
		if(basis.Rows() != dim + 1)
		{
			// Create rotation from nearest orthogonal matrix.
			basis = Matrices.resize(mat, dim + 1, dim + 1);
			basis = new LSQSVD(basis).NearestOrthogonal();
			basis.setOperator(Orthogonal.Type());
		}

		return basis.transpose();
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		if(basis.Rows() != dim + 1)
		{
			// Create rotation from nearest orthogonal matrix.
			basis = Matrices.resize(mat, dim + 1, dim + 1);
			basis = new LSQSVD(basis).NearestOrthogonal();
			basis.setOperator(Orthogonal.Type());
		}

		return basis;
	}
}