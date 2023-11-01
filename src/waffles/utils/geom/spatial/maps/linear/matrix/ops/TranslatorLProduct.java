package waffles.utils.geom.spatial.maps.linear.matrix.ops;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorLProduct} multiplies a translation matrix on the left.
 * The operation is optimized to skip zero values in the translation matrix.
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
	private Matrix t, m;
	
	/**
	 * Creates a new {@code TranslatorLProduct}.
	 * 
	 * @param t  a translation matrix
	 * @param m  a matrix
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
				float val = t.get(r, r) * m.get(r, c);
				if(r < row2 - 1)
				{
					val += t.get(r, col1-1) * m.get(row2-1, c);
				}
				
				result.set(val, r, c);
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
		return 3 * r1 * c2 - 2;
	}
}