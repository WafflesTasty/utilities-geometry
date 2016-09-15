package zeno.util.geom.shapes;

import zeno.util.algebra.Floats;
import zeno.util.algebra.vectors.fixed.Vector2;

/**
 * The {@code Geometry2D} class defines a two-dimensional convex shape
 * using a center, width and height.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see IGeometry2D
 */
public abstract class Geometry2D implements IGeometry2D
{		
	private Vector2 center, size;
	
	/**
	 * Creates a new {@code Geometry2D}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param w  a geometry width
	 * @param h  a geometry height
	 */
	public Geometry2D(float x, float y, float w, float h)
	{
		center = new Vector2(x, y);
		size = new Vector2(w, h);
	}
	
	/**
	 * Creates a new {@code Geometry2D}.
	 */
	public Geometry2D()
	{
		this(0, 0, 1, 1);
	}
	
		
	/**
	 * Changes the center of the {@code Geometry2D}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 */
	public void setCenter(float x, float y)
	{
		center = new Vector2(x, y);
	}

	/**
	 * Changes the center of the {@code Geometry2D}.
	 * 
	 * @param center  a new center
	 * @see Vector2
	 */
	public void setCenter(Vector2 center)
	{
		setCenter(center.X(), center.Y());
	}

	/**
	 * Changes the center x of the {@code Geometry2D}.
	 * 
	 * @param x  a new center x
	 */
	public void setX(float x)
	{
		center.setX(x);
	}

	/**
	 * Changes the center y of the {@code Geometry2D}.
	 * 
	 * @param y  a new center y
	 */
	public void setY(float y)
	{
		center.setY(y);
	}
	
	
	/**
	 * Changes the size of the {@code Geometry2D}.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 */
	protected void setSize(float w, float h)
	{
		size = new Vector2(Math.abs(w), Math.abs(h));
	}

	/**
	 * Changes the size of the {@code Geometry2D}.
	 * 
	 * @param size  a new size
	 * @see Vector2
	 */
	protected void setSize(Vector2 size)
	{
		setSize(size.X(), size.Y());
	}

	
	/**
	 * Changes the height of the {@code Geometry2D}.
	 * 
	 * @param h  a new height
	 */
	protected void setHeight(float h)
	{
		setSize(Width(), h);
	}

	/**
	 * Changes the width of the {@code Geometry2D}.
	 * 
	 * @param w  a new width
	 */
	protected void setWidth(float w)
	{
		setSize(w, Height());
	}


	/**
	 * Returns the center of the {@code Geometry2D}.
	 * 
	 * @return  the rectangle's center
	 * @see Vector2
	 */
	public Vector2 Center()
	{
		return center;
	}
	
	/**
	 * Returns the center x of the {@code Geometry2D}.
	 * 
	 * @return  the rectangle's center x
	 */
	public float X()
	{
		return center.X();
	}
	
	/**
	 * Returns the center y of the {@code Geometry2D}.
	 * 
	 * @return  the rectangle's center y
	 */
	public float Y()
	{
		return center.Y();
	}
	
	
	/**
	 * Returns the size of the {@code Geometry2D}.
	 * 
	 * @return  the rectangle's size
	 * @see Vector2
	 */
	protected Vector2 Size()
	{
		return size;
	}
	
	/**
	 * Returns the height of the {@code Geometry2D}.
	 * 
	 * @return  the rectangle's height
	 */
	protected float Height()
	{
		return size.Y();
	}
	
	/**
	 * Returns the width of the {@code Geometry2D}.
	 * 
	 * @return  the rectangle's width
	 */
	protected float Width()
	{
		return size.X();
	}
			
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Geometry2D)
		{
			Geometry2D oGeom = (Geometry2D) o;
			return Floats.isEqual(Height(), oGeom.Height())
				&& Floats.isEqual(Width(), oGeom.Width())
				&& Floats.isEqual(X(), oGeom.X())
				&& Floats.isEqual(Y(), oGeom.Y());
		}
		
		return false;
	}

	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 17 + Floats.hashCode(center.X());
		code = code * 37 + Floats.hashCode(center.Y());
		code = code * 43 + Floats.hashCode(size.X());
		code = code * 23 + Floats.hashCode(size.Y());
		
		return code;
	}

	
	@Override
	public float XMin()
	{
		return X() - Width() / 2;
	}

	@Override
	public float XMax()
	{
		return X() + Width() / 2;
	}

	@Override
	public float YMin()
	{
		return Y() - Height() / 2;
	}

	@Override
	public float YMax()
	{
		return Y() + Height() / 2;
	}
}