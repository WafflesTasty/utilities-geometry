package zeno.util.geom.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.shapes.IEllipsoid;

/**
 * The {@code Ellipsoid} class defines a three-dimensional ellipsoid shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IEllipsoid
 * @see Geometry3D
 */
public class Ellipsoid extends Geometry3D implements IEllipsoid
{
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param x  the ellipsoid's center x-coördinate
	 * @param y  the ellipsoid's center y-coördinate
	 * @param z  the ellipsoid's center z-coördinate
	 * @param w  the ellipsoid's width
	 * @param h  the ellipsoid's height
	 * @param d  the ellipsoid's depth
	 */
	public Ellipsoid(float x, float y, float z, float w, float h, float d)
	{
		this(new Vector3(x, y, z), new Vector3(w, h, d));
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param center  the ellipsoid's center
	 * @param size  the ellipsoid's size
	 * @see Vector3
	 */
	public Ellipsoid(Vector3 center, Vector3 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param w  the ellipsoid's width
	 * @param h  the ellipsoid's height
	 * @param d  the ellipsoid's depth
	 */
	public Ellipsoid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param size  the ellipsoid's size
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