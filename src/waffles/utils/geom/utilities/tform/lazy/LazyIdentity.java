package waffles.utils.geom.utilities.tform.lazy;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.matrix.LazyMatrix;

/**
 * A {@code LazyIdentity} defines a {@code LazyMatrix} that spawn an identity matrix.
 * This can be used as a placeholder for future implementations.
 *
 * @author Waffles
 * @since 01 Oct 2025
 * @version 1.1
 *
 *
 * @see LazyMatrix
 */
public class LazyIdentity extends LazyMatrix
{
	@Override
	public Matrix compute(Integer d)
	{
		return Matrices.identity(d);
	}
}