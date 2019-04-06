package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.Geometry3D;

/**
 * The {@code Ellipsoid} class defines a three-dimensional ellipsoid shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see IEllipsoid
 * @see Geometry3D
 */
public class Ellipsoid extends Geometry3D implements IEllipsoid
{
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param x  a ellipsoid center x-coördinate
	 * @param y  a ellipsoid center y-coördinate
	 * @param z  a ellipsoid center z-coördinate
	 * @param w  a ellipsoid width
	 * @param h  a ellipsoid height
	 * @param d  a ellipsoid depth
	 */
	public Ellipsoid(float x, float y, float z, float w, float h, float d)
	{
		this(new Vector3(x, y, z), new Vector3(w, h, d));
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param center  an ellipsoid center
	 * @param size    an ellipsoid size
	 * 
	 * 
	 * @see Vector3
	 */
	public Ellipsoid(Vector3 center, Vector3 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param w  an ellipsoid width
	 * @param h  an ellipsoid height
	 * @param d  an ellipsoid depth
	 */
	public Ellipsoid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param size  an ellipsoid size
	 * 
	 * 
	 * @see Vector3
	 */
	public Ellipsoid(Vector3 size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 */
	public Ellipsoid()
	{
		this(1, 1, 1);
	}
}