package waffles.utils.geom.spatial.maps.fixed.linear.matrix.ops;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.tensor.Tensor;
import waffles.utils.tools.patterns.operator.Operation;

/**
 * A {@code TranslatorScalar} is a scalar operation optimized for translator matrices.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Operation
 * @see Tensor
 */
public class TranslatorScalar implements Operation<Tensor>
{
	private Matrix m;
	private float mul;
	
	/**
	 * Creates a new {@code TranslatorScalar}.
	 * 
	 * @param m  a matrix to multiply
	 * @param mul  a scalar multiple
	 * 
	 * 
	 * @see Matrix
	 */
	public TranslatorScalar(Matrix m, float mul)
	{
		this.mul = mul;
		this.m = m;
	}
	

	@Override
	public Matrix result()
	{
		int rows = m.Rows();
		int cols = m.Columns();
	
		Matrix result = Matrices.create(rows, cols);
		for(int r = 0; r < rows; r++)
		{
			result.set(mul * m.get(r, cols-1), r, cols-1);
			result.set(mul, r, r);
		}
		
		return result;
	}
	
	@Override
	public int cost()
	{
		return 3 * m.Rows();
	}
}