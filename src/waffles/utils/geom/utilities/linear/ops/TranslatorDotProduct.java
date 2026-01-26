package waffles.utils.geom.utilities.linear.ops;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorDotProduct} defines a matrix dot product operation.
 * The operation is optimized to skip zero values in a {@code Translator}.
 *
 * @author Waffles
 * @since Jul 13, 2018
 * @version 1.0
 * 
 * 
 * @see Operation
 */
public class TranslatorDotProduct implements Operation<Float>
{
	private Matrix m1, t1;
	
	/**
	 * Creates a new {@code TranslatorDotProduct}.
	 * 
	 * @param t1  a translation matrix
	 * @param m1  a matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorDotProduct(Matrix t1, Matrix m1)
	{
		this.t1 = t1;
		this.m1 = m1;
	}
	
	
	@Override
	public Float result()
	{
		int r1 = m1.Rows();
		int r2 = t1.Rows();
		
		int c1 = m1.Columns();
		int c2 = t1.Columns();
			
		if(r1 != r2 || c1 != c2)
		{
			return null;
		}
		
		
		double d1 = 0d;
		for(int r = 0; r < r1; r++)
		{
			float v1 = t1.get(r, r);
			float v2 = m1.get(r, r);
			
			d1 += v1 * v2;
			if(r < r1 - 1)
			{
				v1 = t1.get(r, c1 - 1);
				v2 = m1.get(r, c1 - 1);
				
				d1 += v1 * v2;
			}
		}
		
		return (float) d1;
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
		return 2 * r2
		// Cost of diagonal.
			 + 2 * r2;
	}
}