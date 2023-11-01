package waffles.utils.geom.spatial.maps.linear.matrix.ops;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorRProduct} multiplies a translation matrix on the right.
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
public class TranslatorRProduct implements Operation<Matrix>
{
	private Matrix t, m;
	
	/**
	 * Creates a new {@code TranslatorRProduct}.
	 * 
	 * @param t  a translation matrix
	 * @param m  a matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorRProduct(Matrix t, Matrix m)
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
			for(int c = 0; c < col2 - 1; c++)
			{
				float val = m.get(r, c);
				val = val * t.get(c, c);
				result.set(val, r, c);
			}
			
			float val = 0f;
			for(int c = 0; c < col1; c++)
			{
				val += m.get(r, c) * t.get(c, col2-1);
			}
			
			result.set(val, r, col2-1);
		}
		
		return result;
	}
	
	@Override
	public int cost()
	{
		int r1 = m.Rows();
		int r2 = t.Rows();
		
		int c1 = m.Columns();
		int c2 = t.Columns();
			
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