package waffles.utils.geom.shapes.convex.axial.sphere.base;

import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code SphereND} implements n-dimensional {@code HyperSphere} geometry.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 *
 *
 * @see HyperSphere
 * @see SpheroidND
 */
public class SphereND extends SpheroidND implements HyperSphere
{
	/**
	 * Creates a new {@code SphereND}.
	 *
	 * @param o  a sphere origin
	 * @param r  a sphere radius
	 *
	 *
	 * @see Point
	 */
	public SphereND(Point o, float r)
	{
		super(o, Arrow.create(2 * r, o.Dimension()));
	}

	/**
	 * Creates a new {@code SphereND}.
	 *
	 * @param r  a sphere radius
	 * @param d  a sphere dimension
	 */
	public SphereND(float r, int d)
	{
		super(Arrow.create(2 * r, d));
	}

	/**
	 * Creates a new {@code SphereND}.
	 *
	 * @param d  a sphere dimension
	 */
	public SphereND(int d)
	{
		super(d);
	}
}