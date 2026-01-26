package waffles.utils.geom.shapes.convex.hulls;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code HullND} implements n-dimensional {@code Hull} geometry.
 *
 * @author Waffles
 * @since 23 Apr 2021
 * @version 1.0
 *
 *
 * @see Hull
 */
public class HullND implements Hull
{
	private Matrix span;

	/**
	 * Creates a new {@code HullND}.
	 *
	 * @param set  a point set
	 *
	 *
	 * @see Point
	 */
	public HullND(Point... set)
	{
		this(Point.concat(set));
	}

	/**
	 * Creates a new {@code HullND}.
	 *
	 * @param s  a matrix span
	 *
	 *
	 * @see Matrix
	 */
	public HullND(Matrix s)
	{
		span = s;
	}

	
	@Override
	public Factory Factory()
	{
		return () -> span;
	}
}