package waffles.utils.geom.maps.linear.ops;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorDotProduct} computes the dot product of a matrix with a translator matrix.
 * The operation is optimized to skip zeroes inside the translator matrix.
 *
 * @author Waffles
 * @since Jul 13, 2018
 * @version 1.0
 * 
 * 
 * @see Operation
 * @see Float
 */
public class TranslatorDotProduct implements Operation<Float>
{
	private Matrix m, t;
	
	/**
	 * Creates a new {@code TranslatorDotProduct}.
	 * 
	 * @param t  a translation matrix
	 * @param m  a matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorDotProduct(Matrix t, Matrix m)
	{
		this.t = t;
		this.m = m;
	}
	
	
	@Override
	public Float result()
	{
		int row1 = m.Rows();
		int row2 = t.Rows();
		
		int col1 = m.Columns();
		int col2 = t.Columns();
			
		if(row1 != row2 || col1 != col2)
		{
			return null;
		}
		
		
		double dot = 0d;
		for(int r = 0; r < row1; r++)
		{
			dot += m.get(r, r) * t.get(r, r);
			if(r < row1 - 1)
			{
				dot += m.get(r, col1 - 1) * t.get(r, col1 - 1);
			}
		}
		
		return (float) dot;
	}
	
	@Override
	public int cost()
	{
		int r1 = m.Rows();
		int r2 = t.Rows();
		
		int c1 = m.Columns();
		int c2 = t.Columns();
			
		if(r1 != r2 || c1 != c2)
		{
			return Integers.MAX_VALUE;
		}


		// Cost of translation.
		return 2 * r2
		// Cost of diagonal.
			 + 2 * r2;
	}
}