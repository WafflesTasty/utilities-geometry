package waffles.utils.geom.maps.linear.ops;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.tensor.Tensor;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code TranslatorAddition} computes the sum of a matrix with a translator matrix.
 * The operation is optimized to skip zeroes inside the translator matrix.
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
	private Matrix t, m;
	
	/**
	 * Creates a new {@code TranslatorAddition}.
	 * 
	 * @param t  a translation matrix
	 * @param m  a matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorAddition(Matrix t, Matrix m)
	{
		this.t = t;
		this.m = m;
	}
	

	@Override
	public Matrix result()
	{
		int row1 = m.Rows();
		int row2 = t.Rows();
		
		int col1 = m.Columns();
		int col2 = t.Columns();
	
		if(row1 != row2 || col1 != col2)
		{
			return null;
		}
		
		
		Matrix result = Matrices.create(row1, col1);
		for(int r = 0; r < row1; r++)
		{
			for(int c = 0; c < col1; c++)
			{
				float val = m.get(r, c);
				if(c == col1 - 1)
					val += t.get(r, c);
				else if(c == r)
					val += t.get(r, c);
				
				result.set(val, r, c);
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
		int c2 = t.Columns();
	
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