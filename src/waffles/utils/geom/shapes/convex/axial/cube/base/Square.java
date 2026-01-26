package waffles.utils.geom.shapes.convex.axial.cube.base;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCube2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Square} implements two-dimensional {@code HyperCube} geometry.
 *
 * @author Waffles
 * @since Mar 21, 2017
 * @version 1.0
 *
 *
 * @see HyperCube2D
 * @see Rectangle
 */
public class Square extends Rectangle implements HyperCube2D
{
	/**
	 * Creates a new {@code Square}.
	 *
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param l  a square length
	 */
	public Square(float x, float y, float l)
	{
		this(new Point(x, y, 1f), l);
	}

	/**
	 * Creates a new {@code Square}.
	 *
	 * @param o  an origin point
	 * @param l  a square length
	 *
	 *
	 * @see Point
	 */
	public Square(Point o, float l)
	{
		super(o, Arrow.create(2 * l, 2));
	}

	/**
	 * Creates a new {@code Square}.
	 *
	 * @param l  a square length
	 */
	public Square(float l)
	{
		this(new Point(2), l);
	}

	/**
	 * Creates a new {@code Square}.
	 */
	public Square()
	{
		this(1f);
	}
}