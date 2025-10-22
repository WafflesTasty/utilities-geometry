package waffles.utils.geom.utilities.tform;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.affine.LinearMap;

/**
 * A {@code LinearInverse} defines an inverse {@code LinearMap}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 *
 *
 * @see LinearMap
 */
public class LinearInverse implements LinearMap
{
	private LinearMap map;

	/**
	 * Creates a new {@code LinearInverse}.
	 *
	 * @param m  a linear map
	 *
	 *
	 * @see LinearMap
	 */
	public LinearInverse(LinearMap m)
	{
		map = m;
	}


	@Override
	public Matrix Inverse(int dim)
	{
		return map.Matrix(dim);
	}

	@Override
	public Matrix Matrix(int dim)
	{
		return map.Inverse(dim);
	}
}