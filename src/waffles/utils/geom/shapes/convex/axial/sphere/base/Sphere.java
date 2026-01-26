package waffles.utils.geom.shapes.convex.axial.sphere.base;

import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Sphere} implements three-dimensional {@code HyperSphere} geometry.
 * 
 * @author Waffles
 * @since Mar 21, 2017
 * @version 1.0
 * 
 * 
 * @see HyperSphere3D
 * @see Spheroid
 */
public class Sphere extends Spheroid implements HyperSphere3D
{
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param z  an origin z
	 * @param r  a sphere radius
	 */
	public Sphere(float x, float y, float z, float r)
	{
		this(new Point(x, y, z, 1f), r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param o  an origin point
	 * @param r  a sphere radius
	 * 
	 * 
	 * @see Point
	 */
	public Sphere(Point o, float r)
	{
		super(o, Arrow.create(r, 3));
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 * 
	 * @param r  a sphere radius
	 */
	public Sphere(float r)
	{
		this(new Point(3), r);
	}
	
	/**
	 * Creates a new {@code Sphere}.
	 */
	public Sphere()
	{
		this(1f);
	}
}