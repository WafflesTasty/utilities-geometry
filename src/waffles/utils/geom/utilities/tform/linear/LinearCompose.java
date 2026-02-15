package waffles.utils.geom.utilities.tform.linear;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.affine.LinearMap;

/**
 * A {@code GlobalCompose} defines a {@code LinearMap} composition.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LinearMap
 */
public class LinearCompose implements LinearMap
{
	private LinearMap[] maps;
	
	/**
	 * Creates a new {@code LinearCompose}.
	 * 
	 * @param set  a map set
	 * 
	 * 
	 * @see LinearMap
	 */
	public LinearCompose(LinearMap... set)
	{
		maps = set;
	}
	
	
	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Matrices.identity(dim);
		for(LinearMap map : maps)
		{
			Matrix n = map.Inverse(dim);
			m = n.times(m);
		}

		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		Matrix m = Matrices.identity(dim);
		for(LinearMap map : maps)
		{
			Matrix n = map.Matrix(dim);
			m = m.times(n);
		}

		return m;
	}
}