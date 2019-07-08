package zeno.util.geom.transforms.functions;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.tensor.Tensor;
import zeno.util.tools.Integers;
import zeno.util.tools.patterns.properties.operator.Operation;

/**
 * The {@code TranslatorAddition} class defines the translation matrix sum.
 * The addition is optimized to skip zeroes in the translation matrix.
 *
 * @author Zeno
 * @since Jul 13, 2018
 * @version 1.0
 * 
 * 
 * @see Operation
 * @see Tensor
 */
public class TranslatorAddition implements Operation<Tensor>
{
	private Matrix m, t;
	
	/**
	 * Creates a new {@code TranslatorAddition}.
	 * 
	 * @param m  a matrix to add
	 * @param t  a translation matrix to add
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorAddition(Matrix m, Matrix t)
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
				{
					val += 1f;
				}
				
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
		return r2
		// Cost of diagonal.
			 + r2;
	}
}