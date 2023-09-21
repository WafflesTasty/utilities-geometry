package waffles.utils.geom.collidable.axial;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.bounds.axial.BNDAxial3D;
import waffles.utils.geom.collidable.Geometry3D;

/**
 * An {@code AxialSet3D} defines 3-dimensional geometry through a center and size vector.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometry3D
 * @see AxialSet
 */
public abstract class AxialSet3D extends AxialSet implements Geometry3D
{	
	/**
	 * Creates a new {@code AxialSet3D}.
	 * 
	 * @param o  an origin vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector3
	 */
	public AxialSet3D(Vector3 o, Vector3 s)
	{
		super(o, s);
	}
	
	/**
	 * Creates a new {@code AxialSet3D}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector3
	 */
	public AxialSet3D(Vector3 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code AxialSet3D}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public AxialSet3D(float w, float h, float d)
	{
		this(new Vector3(w, h, d));
	}
	
	/**
	 * Creates a new {@code AxialSet3D}.
	 * 
	 * @param dim  a space dimension
	 */
	public AxialSet3D(int dim)
	{
		super(dim);
	}
	
			
	/**
	 * Returns the width of the {@code AxialSet3D}.
	 * 
	 * @return  a set width
	 */
	public float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code AxialSet3D}.
	 * 
	 * @return  a set height
	 */
	public float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the depth of the {@code AxialSet3D}.
	 * 
	 * @return  a set depth
	 */
	public float Depth()
	{
		return Size().Z();
	}
	
	/**
	 * Returns the x-coordinate of the {@code AxialSet3D}.
	 * 
	 * @return  a center x-coordinate
	 */
	public float X()
	{
		return Origin().X();
	}
	
	/**
	 * Returns the y-coordinate of the {@code AxialSet3D}.
	 * 
	 * @return  a center y-coordinate
	 */
	public float Y()
	{
		return Origin().Y();
	}
	
	/**
	 * Returns the z-coordinate of the {@code AxialSet3D}.
	 * 
	 * @return  a center z-coordinate
	 */
	public float Z()
	{
		return Origin().Z();
	}
	
	
	@Override
	public Bounds3D Bounds()
	{
		return new BNDAxial3D(this);
	}
	
	@Override
	public Vector3 Origin()
	{
		return (Vector3) super.Origin();
	}
	
	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
	}
}