package waffles.utils.geom.spatial.maps.data.spin;

import waffles.utils.alg.lin.DotProduct;
import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.patterns.basic.errors.NotImplementedError;

/**
 * A {@code SpinND} object defines an n-dimensional rotation.
 * In general, a spin is defined by an orthonormal basis.
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
	 * @param d  a matrix dimension
	 * @return  a rotation matrix
	 *
	 *
	 * @see Matrix
	 */
	public static Matrix Matrix(SpinND s, int d)
	{
		Matrix b = s.Basis().resize(d, d);
		for(int k = b.Rows(); k < d; k++)
		{
			b.set(1f, k, k);
		}

		return b;
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
	public SpinND inverse()
	{
		int dim = Basis().Rows();
		Matrix m = Matrix(this, dim);
		return new SpinND(m.transpose());
	}

	@Override
	public SpinND over(Float s)
	{
		return times(1f / s);
	}
	
	@Override
	public SpinND times(Float s)
	{
		throw new NotImplementedError();
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
	public SpinND hadamard(Point p)
	{
		throw new NotImplementedError();
	}
	
	@Override
	public float dot(DotProduct a)
	{
		throw new NotImplementedError();
	}
	
	@Override
	public Vector Basis(int k)
	{
		if(k < Basis().Columns())
		{
			return Basis().Column(k);
		}

		return null;
	}

	@Override
	public int Dimension()
	{
		return basis.Columns() - 1;
	}

	@Override
	public float norm()
	{
		throw new NotImplementedError();
	}
}