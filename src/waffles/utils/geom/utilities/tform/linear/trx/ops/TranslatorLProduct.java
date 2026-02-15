package waffles.utils.geom.utilities.tform.linear.trx.ops;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorLProduct} defines a left matrix multiplication operation.
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
public class TranslatorLProduct implements Operation<Matrix>
{
	private Matrix t1, m1;
	
	/**
	 * Creates a new {@code TranslatorLProduct}.
	 * 
	 * @param t1  a translation matrix
	 * @param m1  a matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorLProduct(Matrix t1, Matrix m1)
	{
		this.t1 = t1;
		this.m1 = m1;
	}
	
	
	@Override
	public Matrix result()
	{
		int r1 = t1.Rows();
		int r2 = m1.Rows();
		
		int c1 = t1.Columns();
		int c2 = m1.Columns();
			
		if(c1 != r2)
		{
			return null;
		}
		
		
		Matrix m2 = Matrices.create(r1, c2);
		for(int r = 0; r < r1; r++)
		{
			for(int c = 0; c < c2; c++)
			{
				float v1 = t1.get(r, r);
				v1 *= m1.get(r, c);
				if(r < r2 - 1)
				{
					float v2 = t1.get(r, c1 - 1);
					v1 += v2 * m1.get(r2 - 1, c);
				}
				
				m2.set(v1, r, c);
			}
		}
		
		return m2;
	}
	
	@Override
	public int cost()
	{
		int r1 = t1.Rows();
		int r2 = m1.Rows();
		
		int c1 = t1.Columns();
		int c2 = m1.Columns();
			
		if(c1 != r2)
		{
			return Integers.MAX_VALUE;
		}
		

		// Total cost of multiplication.
		return 3 * r1 * c2 - 2;
	}
}