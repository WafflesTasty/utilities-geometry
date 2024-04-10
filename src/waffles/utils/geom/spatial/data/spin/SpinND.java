package waffles.utils.geom.spatial.data.spin;

import waffles.utils.algebra.algorithms.orthogonal.ORTNewton;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Orthogonal;
import waffles.utils.algebra.elements.linear.vector.Vector;

/**
 * A {@code SpinND} defines an n-dimensional rotation spin.
 * It is completely determined by a basis matrix.
 *
 * @author Waffles
 * @since Jan 22, 2020
 * @version 1.1
 * 
 *
 * @see Spin
 */
public class SpinND implements Spin
{
	/**
	 * Creates a {@code Matrix} from a {@code SpinND}.
	 * 
	 * @param s    a spin object
	 * @param dim  a matrix dimension
	 * @return  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix Matrix(SpinND s, int dim)
	{
		Matrix b = s.Basis();
		if(!b.is(Orthogonal.Type()))
		{
			b = new ORTNewton(b).NearestOrthogonal();
			b.setOperator(Orthogonal.Type());
		}
		
		
		Matrix mat = Matrices.resize(b, dim, dim);
		for(int i = b.Rows(); i < dim; i++)
		{
			mat.set(1f, i, i);
		}

		return mat;
	}
	
	
	private Matrix basis;
	
	/**
	 * Creates a new {@code SpinND}.
	 * 
	 * @param b  a spin basis
	 * 
	 * 
	 * @see Matrix
	 */
	public SpinND(Matrix b)
	{
		basis = b;
	}
		
	/**
	 * Creates a new {@code SpinND}.
	 */
	public SpinND()
	{
		this(Matrices.identity(1));
	}
		
	/**
	 * Returns a {@code Spin} basis.
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
	public SpinND compose(Spin s)
	{
		if(s instanceof SpinND)
		{
			Matrix b = ((SpinND) s).Basis();
			return new SpinND(Basis().times(b));
		}
		
		return null;
	}

	@Override
	public Vector Basis(int i)
	{
		if(i < Basis().Columns())
			return Basis().Column(i);
		
		return null;
	}
	
	@Override
	public SpinND invert()
	{
		int dim = Basis().Rows();
		Matrix m = Matrix(this, dim);
		return new SpinND(m.transpose());
	}
}