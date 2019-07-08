package zeno.util.geom.transforms.functions;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.tools.Integers;
import zeno.util.tools.patterns.properties.operator.Operation;

/**
 * The {@code TranslatorLProduct} class defines the translation matrix multiplication on the left.
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
public class TranslatorLProduct implements Operation<Matrix>
{
	private Matrix t, m;
	
	/**
	 * Creates a new {@code TranslatorLProduct}.
	 * 
	 * @param t  a translation matrix to multiply
	 * @param m  a matrix to multiply
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorLProduct(Matrix t, Matrix m)
	{
		this.t = t;
		this.m = m;
	}
	
	
	@Override
	public Matrix result()
	{
		int row1 = t.Rows();
		int row2 = m.Rows();
		
		int col1 = t.Columns();
		int col2 = m.Columns();
			
		if(col1 != row2)
		{
			return null;
		}
		
		
		Matrix result = Matrices.create(row1, col2);
		for(int r = 0; r < row1; r++)
		{
			for(int c = 0; c < col2; c++)
			{
				double val = m.get(r, c);
				if(r < row2 - 1)
				{
					val += t.get(r, col1 - 1) * m.get(row2 - 1, c);
				}
				
				result.set((float) val, r, c);
			}
		}
		
		return result;
	}
	
	@Override
	public int cost()
	{
		int r1 = t.Rows();
		int r2 = m.Rows();
		
		int c1 = t.Columns();
		int c2 = m.Columns();
			
		if(c1 != r2)
		{
			return Integers.MAX_VALUE;
		}
		

		// Total cost of multiplication.
		return 2 * r1 * c2;
	}
}