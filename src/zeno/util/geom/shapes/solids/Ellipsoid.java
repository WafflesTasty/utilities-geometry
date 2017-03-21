package zeno.util.geom.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.shapes.NEllipsoid;

/**
 * The {@code Ellipsoid} class defines a three-dimensional ellipsoid shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NEllipsoid
 */
public class Ellipsoid extends NEllipsoid
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
	
	
	/**
	 * Changes the size of the {@code Ellipsoid}.
	 * 
	 * @param w  a new ellipsoid width
	 * @param h  a new ellipsoid height
	 * @param d  a new ellipsoid depth
	 */
	public void setSize(float w, float h, float d)
	{
		setSize(new Vector3(w, h, d));
	}
		
	/**
	 * Changes the size of the {@code Ellipsoid}.
	 * 
	 * @param size  a new ellipsoid size
	 * @see Vector3
	 */
	public void setSize(Vector3 size)
	{
		super.setSize(size);
	}
	
	
	/**
	 * Changes the height of the {@code Ellipsoid}.
	 * 
	 * @param h  a new ellipsoid height
	 */
	public void setHeight(float h)
	{
		setSize(Width(), h, Depth());
	}
	
	/**
	 * Changes the depth of the {@code Ellipsoid}.
	 * 
	 * @param d  a new ellipsoid depth
	 */
	public void setDepth(float d)
	{
		setSize(Width(), Height(), d);
	}
	
	/**
	 * Changes the width of the {@code Ellipsoid}.
	 * 
	 * @param w  a new ellipsoid width
	 */
	public void setWidth(float w)
	{
		setSize(w, Height(), Depth());
	}
	
	
	/**
	 * Returns the size of the {@code Ellipsoid}.
	 * 
	 * @return  the ellipsoid's size
	 * @see Vector3
	 */
	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
	}
	
	/**
	 * Returns the height of the {@code Ellipsoid}.
	 * 
	 * @return  the ellipsoid's height
	 */
	public float Height()
	{
		return Size().get(1);
	}
	
	/**
	 * Returns the depth of the {@code Ellipsoid}.
	 * 
	 * @return  the ellipsoid's depth
	 */
	public float Depth()
	{
		return Size().get(2);
	}
	
	/**
	 * Returns the width of the {@code Ellipsoid}.
	 * 
	 * @return  the ellipsoid's width
	 */
	public float Width()
	{
		return Size().get(0);
	}
}