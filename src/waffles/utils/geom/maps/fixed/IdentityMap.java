package waffles.utils.geom.maps.fixed;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.GlobalMap;

/**
 * The {@code IdentityMap} defines a global identity map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class IdentityMap implements GlobalMap
{
	@Override
	public LazyMatrix UTW()
	{
		return new LazyMatrix(dim ->
		{
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());
			return m;
		});
	}

	@Override
	public LazyMatrix WTU()
	{
		return new LazyMatrix(dim ->
		{
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());
			return m;
		});
	}
}