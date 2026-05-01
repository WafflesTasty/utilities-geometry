package waffles.utils.geom.spatial.maps.linear;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Reflection} defines a linear map which reflects vectors
 * along a hyperplane. Its corresponding matrices are idempotent.
 *
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 *
 *
 * @see LinearMap
 */
public class Reflection implements LinearMap
{
	private Vector normal;
	
	/**
	 * Creates a new {@code Reflection}.
	 *
	 * @param n  a normal arrow
	 *
	 *
	 * @see Point
	 */
	public Reflection(Point n)
	{
		this(n.Vector());
	}

	/**
	 * Creates a new {@code Reflection}.
	 *
	 * @param n  a normal vector
	 *
	 *
	 * @see Vector
	 */
	public Reflection(Vector n)
	{
		normal = n;
	}


	@Override
	public Matrix Inverse(int dim)
	{
		Vector n = normal.resize(dim);
		return Matrices.reflection(n);
	}

	@Override
	public Matrix Matrix(int dim)
	{
		Vector n = normal.resize(dim);
		return Matrices.reflection(n);
	}
}