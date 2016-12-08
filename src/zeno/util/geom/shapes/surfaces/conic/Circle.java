package zeno.util.geom.shapes.surfaces.conic;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.shapes.surfaces.Conic;

/**
 * The {@code Circle} class defines a two-dimensional circular shape.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Conic
 */
public class Circle extends Conic
{
	/**
	 * Creates a circle through three {@code Vector2} points.
	 * <br> This method returns null if the points are colinear.
	 * 
	 * @param a  the first vector
	 * @param b  the second vector
	 * @param c  the third vector
	 * @return  a circle
	 */
	public static Circle through(Vector2 a, Vector2 b, Vector2 c)
	{
		if(Vector2.isColinear(a, b, c))
		{
			return null;
		}

		float x1 = a.X();
		float x2 = b.X();
		float x3 = c.X();
		float y1 = a.Y();
		float y2 = b.Y();
		float y3 = c.Y();
		
		float x31 = x3 - x1;
		float x23 = x2 - x3;
		float x12 = x1 - x2;
		float y21 = y2 - y1;
		float y32 = y3 - y2;
		float y13 = y1 - y3;
		
		float as = a.normsqr();
		float bs = b.normsqr();
		float cs = c.normsqr();
		
		float d = (x1 * y32 + x2 * y13 + x3 * y21) * 2;
		float x = (as * y32 + bs * y13 + cs * y21) / d;
		float y = (as * x23 + bs * x31 + cs * x12) / d;
		
		Vector2 center = new Vector2(x, y);
		float rad = center.distance(a);
		return new Circle(center, rad);
	}
	
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param vcount  a vertex count
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param rad  a circle radius
	 */
	public Circle(int vcount, float x, float y, float rad)
	{
		super(vcount, x, y, rad, rad);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param vcount  a vertex count
	 * @param center  a circle center
	 * @param rad  a circle radius
	 * @see Vector2
	 */
	public Circle(int vcount, Vector2 center, float rad)
	{
		this(vcount, center.X(), center.Y(), rad);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param rad  a circle radius
	 */
	public Circle(float x, float y, float rad)
	{
		super(x, y, rad, rad);
	}
		
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param center  a circle center
	 * @param rad  a circle radius
	 * @see Vector2
	 */
	public Circle(Vector2 center, float rad)
	{
		this(center.X(), center.Y(), rad);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param vcount  a vertex count
	 * @param rad  a circle radius
	 */
	public Circle(int vcount, float rad)
	{
		this(vcount, 0, 0, rad);
	}

	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param rad  a circle radius
	 */
	public Circle(float rad)
	{
		this(0, 0, rad);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 */
	public Circle()
	{
		this(1);
	}

	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Circle)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	/**
	 * Indicates if the {@code Circle} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return  {@code true} if the point is contained
	 */
	@Override
	public boolean contains(float x, float y)
	{
		float r = Radius();
		
		// Normalized point.
		float nx = x - X();
		float ny = y - Y();
		
		// Distance from center.
		return nx * nx + ny * ny < r * r;
	}
	
	/**
	 * Changes the radius of the {@code Circle}.
	 * 
	 * @param rad  a new circle radius
	 */
	public void setRadius(float rad)
	{
		setSize(Math.abs(rad), Math.abs(rad));
	}
	
	/**
	 * Returns the radius of the {@code Circle}.
	 * 
	 * @return  the circle's radius
	 */
	public float Radius()
	{
		return Width();
	}
}