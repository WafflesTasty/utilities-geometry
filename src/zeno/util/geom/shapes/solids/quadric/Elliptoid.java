package zeno.util.geom.shapes.solids.quadric;

import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.geom.shapes.solids.Quadric;

/**
 * The {@code Elliptoid} class defines a three-dimensional elliptical shape.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Quadric
 */
public class Elliptoid extends Quadric
{
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param vcount  a vertex count
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Elliptoid(int vcount, float x, float y, float z, float w, float h, float d)
	{
		super(vcount, x, y, z, w, h, d);
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param vcount  a vertex count
	 * @param center  a center
	 * @param size  a size
	 * @see Vector3
	 */
	public Elliptoid(int vcount, Vector3 center, Vector3 size)
	{
		this(vcount, center.X(), center.Y(), center.Z(), size.X(), size.Y(), size.Z());
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Elliptoid(float x, float y, float z, float w, float h, float d)
	{
		super(x, y, z, w, h, d);
	}
		
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param center  a center
	 * @param size  a size
	 * @see Vector3
	 */
	public Elliptoid(Vector3 center, Vector3 size)
	{
		this(center.X(), center.Y(), center.Z(), size.X(), size.Y(), size.Z());
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param vcount  a vertex count
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Elliptoid(int vcount, float w, float h, float d)
	{
		this(vcount, 0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param vcount  a vertex count
	 * @param size  a size
	 * @see Vector3
	 */
	public Elliptoid(int vcount, Vector3 size)
	{
		this(vcount, 0, 0, 0, size.X(), size.Y(), size.Z());
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Elliptoid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param size  a size
	 * @see Vector3
	 */
	public Elliptoid(Vector3 size)
	{
		this(0, 0, 0, size.X(), size.Y(), size.Z());
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 * 
	 * @param vcount  a vertex count
	 */
	public Elliptoid(int vcount)
	{
		this(vcount, 1, 1, 1);
	}
	
	/**
	 * Creates a new {@code Elliptoid}.
	 */
	public Elliptoid()
	{
		this(1, 1, 1);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Elliptoid)
		{
			return super.equals(o);
		}
		
		return false;
	}
		
	/**
	 * Changes the dimension of the {@code Elliptoid}.
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
	 * Changes the dimension of the {@code Elliptoid}.
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
	 * Changes the height of the {@code Elliptoid}.
	 * 
	 * @param h  a new height
	 */
	@Override
	public void setHeight(float h)
	{
		setSize(Width(), h, Depth());
	}
	
	/**
	 * Changes the width of the {@code Elliptoid}.
	 * 
	 * @param w  a new width
	 */
	@Override
	public void setWidth(float w)
	{
		setSize(w, Height(), Depth());
	}
	
	/**
	 * Changes the depth of the {@code Elliptoid}.
	 * 
	 * @param d  a new depth
	 */
	@Override
	public void setDepth(float d)
	{
		setSize(Width(), Height(), d);
	}
	
	
	/**
	 * Returns the size of the {@code Elliptoid}.
	 * 
	 * @return  the elliptoid's size
	 * @see Vector3
	 */
	@Override
	public Vector3 Size()
	{
		return super.Size();
	}
	
	/**
	 * Returns the {@code Elliptoid}'s height.
	 * 
	 * @return  the elliptoid's height
	 */
	@Override
	public float Height()
	{
		return super.Height();
	}
	
	/**
	 * Returns the {@code Elliptoid}'s width.
	 * 
	 * @return  the elliptoid's width
	 */
	@Override
	public float Width()
	{
		return super.Width();
	}
	
	/**
	 * Returns the {@code Elliptoid}'s depth.
	 * 
	 * @return  the elliptoid's depth
	 */
	@Override
	public float Depth()
	{
		return super.Depth();
	}
}