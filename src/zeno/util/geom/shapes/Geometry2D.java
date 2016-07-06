package zeno.util.geom.shapes;

import zeno.util.algebra.Floats;
import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.Geometry;
import zeno.util.geom.tools.bounds.IBound2D;

/**
 * The {@code Geometry2D} class is the base for any convex two-dimensional geometric shape.
 *
 * @author Zeno
 * @since Aug 22, 2015
 * @see IBound2D
 * @see Geometry
 */
public abstract class Geometry2D implements Geometry, IBound2D
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
	 * Indicates if the {@code Geometry2D} intersects a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the rectangle intersects
	 */
	public abstract boolean intersects(float x1, float y1, float x2, float y2);
	
	/**
	 * Indicates if the {@code Geometry2D} contains a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the rectangle contains
	 */
	public abstract boolean contains(float x1, float y1, float x2, float y2);
	
	/**
	 * Indicates if the {@code Geometry2D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return  {@code true} if the point is contained
	 */
	public abstract boolean contains(float x, float y);
	
	/**
	 * Returns the vertices of the {@code Geometry2D}.
	 * 
	 * @return  the geometry's vertices
	 * @see Vector2
	 */
	@Override
	public abstract Vector2[] Vertices();
	
	
	/**
	 * Indicates if the {@code Geometry2D} intersects a rectangle.
	 * 
	 * @param rect  a rectangle to check
	 * @return  {@code true} if the rectangle intersects
	 * @see IBound2D
	 */
	public final boolean intersects(IBound2D rect)
	{
		return intersects(rect.XMin(), rect.YMin(), rect.XMax(), rect.YMax());
	}
	
	/**
	 * Indicates if the {@code Geometry2D} contains a rectangle.
	 * 
	 * @param rect  a rectangle to check
	 * @return  {@code true} if the rectangle contains
	 * @see IBound2D
	 */
	public final boolean contains(IBound2D rect)
	{
		return contains(rect.XMin(), rect.YMin(), rect.XMax(), rect.YMax());
	}
	
	/**
	 * Indicates if the {@code Geometry2D} contains a point.
	 * 
	 * @param v  a point to check
	 * @return  {@code true} if the point is contained
	 * @see Vector2
	 */
	public final boolean contains(Vector2 v)
	{
		return contains(v.X(), v.Y());
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
		
		code = code * 17 + Float.hashCode(center.X());
		code = code * 37 + Float.hashCode(center.Y());
		code = code * 43 + Float.hashCode(size.X());
		code = code * 23 + Float.hashCode(size.Y());
		
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