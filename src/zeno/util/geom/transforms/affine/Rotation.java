package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.algorithms.lsquares.LSQSVD;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.types.orthogonal.Orthogonal;
import zeno.util.geom.ITransformation;
import zeno.util.tools.Integers;
import zeno.util.tools.patterns.properties.Copyable;

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
 * @see Copyable
 */
public class Rotation implements Copyable<Rotation>, ITransformation
{
	private static Matrix DefaultRotation(int dim)
	{
		return Matrices.identity(dim);
	}
	
	/**
	 * Returns a {@code Rotation} to a basis matrix.
	 * 
	 * @param b  a basis matrix
	 * @return  a rotation
	 * 
	 * 
	 * @see Matrix
	 */
	public static Rotation of(Matrix b)
	{
		return new Rotation(b);
	}
	
	
	private Matrix basis;
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param dim  a space dimension
	 */
	public Rotation(int dim)
	{
		basis = DefaultRotation(dim);
	}
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param b  a basis matrix
	 * @param dim  a space dimension
	 * 
	 * 
	 * @see Matrix
	 */
	public Rotation(Matrix b, int dim)
	{
		basis = DefaultRotation(dim);
		for(int r = 0; r < Integers.min(b.Rows(), dim); r++)
		{
			for(int c = 0; c < Integers.min(b.Columns(), dim); c++)
			{
				basis.set(b.get(r, c), r, c);
			}
		}
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
		basis = b;
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
		Matrix m = Matrices.resize(basis, dim + 1, dim + 1);
		m = new LSQSVD(m).NearestOrthogonal().transpose();
		m.setOperator(Orthogonal.Type());
		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		Matrix m = Matrices.resize(basis, dim + 1, dim + 1);
		m = new LSQSVD(m).NearestOrthogonal();
		m.setOperator(Orthogonal.Type());
		return m;
	}
	
	
	@Override
	public Rotation instance()
	{
		return new Rotation(basis);
	}
	
		
	@Override
	public int DimOut()
	{
		return basis.Rows();
	}
	
	@Override
	public int DimIn()
	{
		return basis.Columns();
	}
}