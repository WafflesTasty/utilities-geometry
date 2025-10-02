package waffles.utils.geom.utilities.matrix.ops;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorRProduct} defines a right matrix multiplication operation.
 * The operation is optimized to skip zero values in a {@code Translator}.
 *
 * @author Waffles
 * @since Jul 13, 2018
 * @version 1.0
 * 
 * 
 * @see Operation
 * @see Matrix
 */
public class TranslatorRProduct implements Operation<Matrix>
{
	private Matrix t1, m1;
	
	/**
	 * Creates a new {@code TranslatorRProduct}.
	 * 
	 * @param t1  a translation matrix
	 * @param m1  a matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorRProduct(Matrix t1, Matrix m1)
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
			
		if(c1 != r2)
		{
			return null;
		}
		
		
		Matrix m2 = Matrices.create(r1, c2);
		for(int r = 0; r < r1; r++)
		{
			for(int c = 0; c < c2 - 1; c++)
			{
				float v1 = m1.get(r, c);
				v1 = v1 * t1.get(c, c);
				m2.set(v1, r, c);
			}
			
			float v2 = 0f;
			for(int c = 0; c < c1; c++)
			{
				float v1 = t1.get(c, c2 - 1);
				v2 += m1.get(r, c) * v1;
			}
			
			m2.set(v2, r, c2-1);
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
			
		if(c1 != r2)
		{
			return Integers.MAX_VALUE;
		}
		

		// Cost of translation.
		return r1 * (c2 - 1)
			// Cost of diagonal.
			 + r1 * (2 * c1 - 1);
	}
}