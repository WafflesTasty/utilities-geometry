package zeno.util.geom.shapes.solids.lateral;

import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.geom.shapes.solids.Lateral3D;

/**
 * The {@code Cuboid} class defines a three-dimensional rectangular shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @see Lateral3D
 */
public class Cuboid extends Lateral3D
{
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Cuboid(float x, float y, float z, float w, float h, float d)
	{
		super(x, y, z, w, h, d);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param center  a center
	 * @param size  a size
	 * @see Vector3
	 */
	public Cuboid(Vector3 center, Vector3 size)
	{
		this(center.X(), center.Y(), center.Z(), size.X(), size.Y(), size.Z());
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Cuboid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param size  a size
	 * @see Vector3
	 */
	public Cuboid(Vector3 size)
	{
		this(size.X(), size.Y(), size.Z());
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 */
	public Cuboid()
	{
		this(1, 1, 1);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Cuboid)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	/**
	 * Changes the size of the {@code Cuboid}.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 * @param d  a new depth
	 */
	@Override
	public void setSize(float w, float h, float d)
	{
		super.setSize(Math.abs(w), Math.abs(h), Math.abs(d));
	}
		
	/**
	 * Changes the size of the {@code Cuboid}.
	 * 
	 * @param size  a new size
	 * @see Vector3
	 */
	@Override
	public void setSize(Vector3 size)
	{
		setSize(size.X(), size.Y(), size.Z());
	}
	
	
	/**
	 * Changes the height of the {@code Cuboid}.
	 * 
	 * @param h  a new height
	 */
	@Override
	public void setHeight(float h)
	{
		setSize(Width(), h, Depth());
	}
	
	/**
	 * Changes the depth of the {@code Cuboid}.
	 * 
	 * @param d  a new depth
	 */
	@Override
	public void setDepth(float d)
	{
		setSize(Width(), Height(), d);
	}
	
	/**
	 * Changes the width of the {@code Cuboid}.
	 * 
	 * @param w  a new width
	 */
	@Override
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
		return super.Size();
	}
	
	/**
	 * Returns the height of the {@code Cuboid}.
	 * 
	 * @return  the cuboid's height
	 */
	@Override
	public float Height()
	{
		return super.Height();
	}
	
	/**
	 * Returns the depth of the {@code Cuboid}.
	 * 
	 * @return  the cuboid's depth
	 */
	@Override
	public float Depth()
	{
		return super.Depth();
	}
	
	/**
	 * Returns the width of the {@code Cuboid}.
	 * 
	 * @return  the cuboid's width
	 */
	@Override
	public float Width()
	{
		return super.Width();
	}
}