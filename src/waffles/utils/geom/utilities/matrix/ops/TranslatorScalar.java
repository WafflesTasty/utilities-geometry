package waffles.utils.geom.utilities.matrix.ops;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.tensor.Tensor;
import waffles.utils.tools.patterns.operator.Operation;

/**
 * A {@code TranslatorScalar} defines a scalar matrix multiplication operation.
 * The operation is optimized to skip zero values in a {@code Translator}.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 *
 *
 * @see Operation
 * @see Tensor
 */
public class TranslatorScalar implements Operation<Tensor>
{
	private float s1;
	private Matrix m1;

	/**
	 * Creates a new {@code TranslatorScalar}.
	 *
	 * @param m1  a base matrix
	 * @param s1  a scalar multiple
	 *
	 *
	 * @see Matrix
	 */
	public TranslatorScalar(Matrix m1, float s1)
	{
		this.m1 = m1;
		this.s1 = s1;
	}


	@Override
	public Matrix result()
	{
		int r1 = m1.Rows();
		int c1 = m1.Columns();

		Matrix m2 = Matrices.create(r1, c1);
		for(int r = 0; r < r1; r++)
		{
			float v1 = m1.get(r, c1 - 1);
			m2.set(s1 * v1, r, c1 - 1);
			m2.set(s1, r, r);
		}

		return m2;
	}

	@Override
	public int cost()
	{
		return 3 * m1.Rows();
	}
}