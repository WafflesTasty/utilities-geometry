package waffles.utils.geom.shapes.convex.axial.cube.base;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCube3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Cube} implements three-dimensional {@code HyperCube} geometry.
 *
 * @author Waffles
 * @since Mar 21, 2017
 * @version 1.0
 *
 *
 * @see HyperCube3D
 * @see Cuboid
 */
public class Cube extends Cuboid implements HyperCube3D
{
	/**
	 * Creates a new {@code Cube}.
	 *
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param z  an origin z
	 * @param l  a cube length
	 */
	public Cube(float x, float y, float z, float l)
	{
		this(new Point(x, y, z, 1f), l);
	}

	/**
	 * Creates a new {@code Cube}.
	 *
	 * @param o  an origin point
	 * @param l  a cube length
	 *
	 *
	 * @see Point
	 */
	public Cube(Point o, float l)
	{
		super(o, Arrow.create(2 * l, 3));
	}

	/**
	 * Creates a new {@code Cube}.
	 *
	 * @param l  a cube length
	 */
	public Cube(float l)
	{
		this(new Point(3), l);
	}

	/**
	 * Creates a new {@code Cube}.
	 */
	public Cube()
	{
		this(1f);
	}
}