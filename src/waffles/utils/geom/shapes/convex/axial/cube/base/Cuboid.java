package waffles.utils.geom.shapes.convex.axial.cube.base;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Cuboid} implements three-dimensional {@code HyperCuboid} geometry.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 *
 *
 * @see HyperCuboid3D
 * @see CuboidND
 */
public class Cuboid extends CuboidND implements HyperCuboid3D
{
	/**
	 * Creates a new {@code Cuboid}.
	 *
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param z  an origin z
	 * @param w  a scale width
	 * @param h  a scale height
	 * @param d  a scale depth
	 */
	public Cuboid(float x, float y, float z, float w, float h, float d)
	{
		this(new Point(x, y, z, 1f), new Arrow(w, h, d));
	}

	/**
	 * Creates a new {@code Cuboid}.
	 *
	 * @param w  a scale width
	 * @param h  a scale height
	 * @param d  a scale depth
	 */
	public Cuboid(float w, float h, float d)
	{
		this(new Arrow(w, h, d));
	}

	/**
	 * Creates a new {@code Cuboid}.
	 *
	 * @param o  an origin point
	 * @param s  a scale arrow
	 *
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public Cuboid(Point o, Arrow s)
	{
		super(o, s);
	}

	/**
	 * Creates a new {@code Cuboid}.
	 *
	 * @param s  a scale arrow
	 *
	 *
	 * @see Arrow
	 */
	public Cuboid(Arrow s)
	{
		this(new Point(s.Dimension()), s);
	}

	/**
	 * Creates a new {@code Cuboid}.
	 */
	public Cuboid()
	{
		super(3);
	}
}