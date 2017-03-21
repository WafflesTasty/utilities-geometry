package zeno.util.geom.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.shapes.NCuboid;

/**
 * The {@code Cuboid} class defines a three-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NCuboid
 */
public class Cuboid extends NCuboid
{
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param x  the cuboid's center x-coördinate
	 * @param y  the cuboid's center y-coördinate
	 * @param z  the cuboid's center z-coördinate
	 * @param w  the cuboid's width
	 * @param h  the cuboid's height
	 * @param d  the cuboid's depth
	 */
	public Cuboid(float x, float y, float z, float w, float h, float d)
	{
		this(new Vector3(x, y, z), new Vector3(w, h, d));
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param center  the cuboid's center
	 * @param size  the cuboid's size
	 * @see Vector3
	 */
	public Cuboid(Vector3 center, Vector3 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param w  the cuboid's width
	 * @param h  the cuboid's height
	 * @param d  the cuboid's depth
	 */
	public Cuboid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param size  the cuboid's size
	 * @see Vector3
	 */
	public Cuboid(Vector3 size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 */
	public Cuboid()
	{
		this(1, 1, 1);
	}
	
	
	/**
	 * Changes the size of the {@code Cuboid}.
	 * 
	 * @param w  a new cuboid width
	 * @param h  a new cuboid height
	 * @param d  a new cuboid depth
	 */
	public void setSize(float w, float h, float d)
	{
		setSize(new Vector3(w, h, d));
	}
		
	/**
	 * Changes the size of the {@code Cuboid}.
	 * 
	 * @param size  a new cuboid size
	 * @see Vector3
	 */
	public void setSize(Vector3 size)
	{
		super.setSize(size);
	}
	
	
	/**
	 * Changes the height of the {@code Cuboid}.
	 * 
	 * @param h  a new cuboid height
	 */
	public void setHeight(float h)
	{
		setSize(Width(), h, Depth());
	}
	
	/**
	 * Changes the depth of the {@code Cuboid}.
	 * 
	 * @param d  a new cuboid depth
	 */
	public void setDepth(float d)
	{
		setSize(Width(), Height(), d);
	}
	
	/**
	 * Changes the width of the {@code Cuboid}.
	 * 
	 * @param w  a new cuboid width
	 */
	public void setWidth(float w)
	{
		setSize(w, Height(), Depth());
	}
	
	
	/**
	 * Returns the size of the {@code Cuboid}.
	 * 
	 * @return  the cuboid's size
	 * @see Vector3
	 */
	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
	}
	
	/**
	 * Returns the height of the {@code Cuboid}.
	 * 
	 * @return  the cuboid's height
	 */
	public float Height()
	{
		return Size().get(1);
	}
	
	/**
	 * Returns the depth of the {@code Cuboid}.
	 * 
	 * @return  the cuboid's depth
	 */
	public float Depth()
	{
		return Size().get(2);
	}
	
	/**
	 * Returns the width of the {@code Cuboid}.
	 * 
	 * @return  the cuboid's width
	 */
	public float Width()
	{
		return Size().get(0);
	}
}