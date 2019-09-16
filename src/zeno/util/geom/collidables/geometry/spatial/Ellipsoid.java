package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.Geometry3D;
import zeno.util.geom.collidables.geometry.bounds.BNDEllipsoid3D;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.transforms.AffineMap;
import zeno.util.geom.utilities.bounds.Bounds3D;

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
	 * @param x  a ellipsoid center x-co�rdinate
	 * @param y  a ellipsoid center y-co�rdinate
	 * @param z  a ellipsoid center z-co�rdinate
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
	 * @param c  an ellipsoid center
	 * @param s  an ellipsoid size
	 * 
	 * 
	 * @see Vector3
	 */
	public Ellipsoid(Vector3 c, Vector3 s)
	{
		super(c, s);
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 * 
	 * @param s  an ellipsoid size
	 * 
	 * 
	 * @see Vector3
	 */
	public Ellipsoid(Vector3 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Ellipsoid}.
	 */
	public Ellipsoid()
	{
		super();
	}

	
	@Override
	public Bounds3D Bounds(AffineMap map)
	{
		return new BNDEllipsoid3D(this, map);
	}
}