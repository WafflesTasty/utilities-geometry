package waffles.utils.geom.collidable.axial;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.bounds.axial.BNDAxial2D;
import waffles.utils.geom.collidable.Geometry2D;

/**
 * An {@code AxialSet2D} defines 2-dimensional geometry through a center and size vector.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometry2D
 * @see AxialSet
 */
public abstract class AxialSet2D extends AxialSet implements Geometry2D
{	
	/**
	 * Creates a new {@code AxialSet2D}.
	 * 
	 * @param o  an origin vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector2
	 */
	public AxialSet2D(Vector2 o, Vector2 s)
	{
		super(o, s);
	}
	
	/**
	 * Creates a new {@code AxialSet2D}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 */
	public AxialSet2D(float w, float h)
	{
		this(new Vector2(w, h));
	}
	
	/**
	 * Creates a new {@code AxialSet2D}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector2
	 */
	public AxialSet2D(Vector2 s)
	{
		super(s);
	}
		
	/**
	 * Creates a new {@code AxialSet2D}.
	 */
	public AxialSet2D()
	{
		this(2f, 2f);
	}
	
		
	/**
	 * Returns the width of the {@code AxialSet2D}.
	 * 
	 * @return  a set width
	 */
	public float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code AxialSet2D}.
	 * 
	 * @return  a set height
	 */
	public float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the x-coordinate of the {@code AxialSet2D}.
	 * 
	 * @return  a center x-coordinate
	 */
	public float X()
	{
		return Origin().X();
	}
	
	/**
	 * Returns the y-coordinate of the {@code AxialSet2D}.
	 * 
	 * @return  a center y-coordinate
	 */
	public float Y()
	{
		return Origin().Y();
	}

	
	@Override
	public Bounds2D Bounds()
	{
		return new BNDAxial2D(this);
	}
	
	@Override
	public Vector2 Origin()
	{
		return (Vector2) super.Origin();
	}
	
	@Override
	public Vector2 Size()
	{
		return (Vector2) super.Size();
	}
}