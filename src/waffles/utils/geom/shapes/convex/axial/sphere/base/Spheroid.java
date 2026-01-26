package waffles.utils.geom.shapes.convex.axial.sphere.base;

import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Spheroid} implements three-dimensional {@code HyperSpheroid} geometry.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 *
 *
 * @see HyperSpheroid3D
 * @see SpheroidND
 */
public class Spheroid extends SpheroidND implements HyperSpheroid3D
{
	/**
	 * Creates a new {@code Spheroid}.
	 *
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param z  an origin z
	 * @param w  a scale width
	 * @param h  a scale height
	 * @param d  a scale depth
	 */
	public Spheroid(float x, float y, float z, float w, float h, float d)
	{
		this(new Point(x, y, z, 1f), new Arrow(w, h, d));
	}

	/**
	 * Creates a new {@code Spheroid}.
	 *
	 * @param w  a scale width
	 * @param h  a scale height
	 * @param d  a scale depth
	 */
	public Spheroid(float w, float h, float d)
	{
		this(new Arrow(w, h, d));
	}

	/**
	 * Creates a new {@code Spheroid}.
	 *
	 * @param o  an origin point
	 * @param s  a scale arrow
	 *
	 *
	 * @see Arrow
	 * @see Point
	 */
	public Spheroid(Point o, Arrow s)
	{
		super(o, s);
	}

	/**
	 * Creates a new {@code Spheroid}.
	 *
	 * @param s  a scale arrow
	 *
	 *
	 * @see Arrow
	 */
	public Spheroid(Arrow s)
	{
		this(new Point(s.Dimension()), s);
	}

	/**
	 * Creates a new {@code Spheroid}.
	 */
	public Spheroid()
	{
		super(3);
	}
}