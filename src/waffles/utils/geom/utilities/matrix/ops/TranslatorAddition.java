package waffles.utils.geom.utilities.matrix.ops;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.tensor.Tensor;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorAddition} defines a matrix addition operation.
 * The operation is optimized to skip zero values in a {@code Translator}.
 *
 * @author Waffles
 * @since Jul 13, 2018
 * @version 1.0
 *
 *
 * @see Operation
 * @see Tensor
 */
public class TranslatorAddition implements Operation<Tensor>
{
	private Matrix t1, m1;

	/**
	 * Creates a new {@code TranslatorAddition}.
	 *
	 * @param t1  a translation matrix
	 * @param m1  a matrix
	 *
	 *
	 * @see Matrix
	 */
	public TranslatorAddition(Matrix t1, Matrix m1)
	{
		this.t1 = t1;
		this.m1 = m1;
	}


	@Override
	public Matrix result()
	{
		int r1 = m1.Rows();
		int r2 = t1.Rows();

		int c1 = m1.Columns();
		int c2 = t1.Columns();

		if(r1 != r2 || c1 != c2)
		{
			return null;
		}


		Matrix m2 = Matrices.create(r1, c1);
		for(int r = 0; r < r1; r++)
		{
			for(int c = 0; c < c1; c++)
			{
				float v1 = m1.get(r, c);
				if(c == c1 - 1)
				{
					v1 += t1.get(r, c);
				} else if(c == r)
				{
					v1 += t1.get(r, c);
				}

				m2.set(v1, r, c);
			}
		}

		return m2;
	}

	@Override
	public int cost()
	{
		int r1 = m1.Rows();
		int r2 = t1.Rows();

		int c1 = m1.Columns();
		int c2 = t1.Columns();

		if(r1 != r2 || c1 != c2)
		{
			return Integers.MAX_VALUE;
		}


		// Cost of translation.
		return r2 + 1
		// Cost of diagonal.
			 + r2;
	}
}