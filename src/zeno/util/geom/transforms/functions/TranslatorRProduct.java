package zeno.util.geom.transforms.functions;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.tools.Integers;
import zeno.util.tools.patterns.properties.operator.Operation;

/**
 * The {@code TranslatorRProduct} class defines the translation matrix multiplication on the right.
 * The multiplication is optimized to skip zero values in the translation matrix.
 *
 * @author Zeno
 * @since Jul 13, 2018
 * @version 1.0
 * 
 * 
 * @see Operation
 * @see Matrix
 */
public class TranslatorRProduct implements Operation<Matrix>
{
	private Matrix t, m;
	
	/**
	 * Creates a new {@code TranslatorRProduct}.
	 * 
	 * @param m  a matrix to multiply
	 * @param t  a translation matrix to multiply
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorRProduct(Matrix m, Matrix t)
	{
		this.m = m;
		this.t = t;
	}
	
	
	@Override
	public Matrix result()
	{
		int row1 = m.Rows();
		int row2 = t.Rows();
		
		int col1 = m.Columns();
		int col2 = t.Columns();
			
		if(col1 != row2)
		{
			return null;
		}
		
		
		Matrix result = Matrices.create(row1, col2);
		for(int r = 0; r < row1; r++)
		{
			double val = 0d;
			for(int c = 0; c < col1; c++)
			{
				val += m.get(r, c) * t.get(c, col2 - 1);
			}
			
			result.set((float) val, r, col2 - 1);
			for(int c = 0; c < col2 - 1; c++)
			{
				result.set(m.get(r, c), r, c);
			}
		}
		
		return result;
	}
	
	@Override
	public int cost()
	{
		int r1 = m.Rows();
		int r2 = t.Rows();
		
		int c1 = m.Columns();
			
		if(c1 != r2)
		{
			return Integers.MAX_VALUE;
		}
		

		// Total cost of multiplication.
		return 2 * r1 * c1;
	}
}