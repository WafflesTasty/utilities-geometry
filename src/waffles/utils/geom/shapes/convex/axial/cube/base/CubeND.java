package waffles.utils.geom.shapes.convex.axial.cube.base;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCube;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code CuboidND} implements n-dimensional {@code HyperCube} geometry.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 *
 *
 * @see HyperCube
 * @see CuboidND
 */
public class CubeND extends CuboidND implements HyperCube
{
	/**
	 * Creates a new {@code CubeND}.
	 *
	 * @param o  a cube origin
	 * @param l  a cube length
	 *
	 *
	 * @see Point
	 */
	public CubeND(Point o, float l)
	{
		super(o, Arrow.create(2 * l, o.Dimension()));
	}

	/**
	 * Creates a new {@code CubeND}.
	 *
	 * @param d  a cube dimension
	 * @param l  a cube length
	 */
	public CubeND(float l, int d)
	{
		super(Arrow.create(2 * l, d));
	}

	/**
	 * Creates a new {@code CubeND}.
	 *
	 * @param d  a cube dimension
	 */
	public CubeND(int d)
	{
		super(d);
	}
}