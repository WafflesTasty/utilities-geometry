package zeno.util.geom.utilities.spin;

import zeno.util.algebra.algorithms.lsquares.LSQSVD;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.types.orthogonal.Orthogonal;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code SpinND} defines spin in n-dimensional space.
 * It is completely determined by a basis matrix.
 *
 * @author Zeno
 * @since Jan 22, 2020
 * @version 1.0
 * 
 *
 * @see Spin
 */
public class SpinND implements Spin
{
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
		this(Matrices.identity(0));
	}
	
	
	/**
	 * Returns the {@code Spin} basis.
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
		try
		{
			return new SpinND(basis.times(((SpinND) s).Basis()));
		}
		catch(Exception e)
		{
			throw new Geometries.SpinError(this, s);
		}
	}
	
	@Override
	public Matrix generate(int dim)
	{
		if(!basis.is(Orthogonal.Type()))
		{
			basis = new LSQSVD(basis).NearestOrthogonal();
			basis.setOperator(Orthogonal.Type());
		}
		
		Matrix mat = Matrices.resize(basis, dim+1, dim+1);
		for(int i = basis.Rows(); i <= dim; i++)
		{
			mat.set(1f, i, i);
		}

		return mat;
	}

	@Override
	public Vector vector(int i)
	{
		if(i < basis.Columns())
			return basis.Column(i);
		
		return null;
	}
}