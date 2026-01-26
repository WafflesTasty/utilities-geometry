package waffles.utils.geom.shapes.linear.affine.lines;

import waffles.utils.alg.lin.measure.vector.fixed.Vector3;
import waffles.utils.geom.Collideable3D;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Line3D} defines an affine line in three-dimensional space.
 *
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 *
 *
 * @see Collideable3D
 * @see Line
 */
public class Line3D extends Line implements Collideable3D
{
	/**
	 * Creates a new {@code Line3D}.
	 *
	 * @param x1  an x-coordinate
	 * @param y1  a  y-coordinate
	 * @param z1  a  z-coordinate
	 * @param x2  an x-coordinate
	 * @param y2  a  y-coordinate
	 * @param z2  a  z-coordinate
	 */
	public Line3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		this(new Point(x1, y1, z1, 1f), new Point(x2, y2, z2, 1f));
	}

	
	/**
	 * Creates a new {@code Line3D}.
	 *
	 * @param p1  a point vector
	 * @param p2  a point vector
	 *
	 *
	 * @see Vector3
	 */
	public Line3D(Vector3 p1, Vector3 p2)
	{
		this(new Point(p1, 1f), new Point(p2, 1f));
	}
	
	/**
	 * Creates a new {@code Line3D}.
	 *
	 * @param p1  a line point
	 * @param p2  a line point
	 *
	 *
	 * @see Point
	 */
	public Line3D(Point p1, Point p2)
	{
		super(p1, p2);
	}
}