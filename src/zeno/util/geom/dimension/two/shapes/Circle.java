package zeno.util.geom.dimension.two.shapes;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.shapes.ISphere;

/**
 * The {@code Circle} class defines a two-dimensional circular shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see Ellipse
 * @see ISphere
 */
public class Circle extends Ellipse implements ISphere
{
	/**
	 * Creates a circle through three {@code Vector2} points.
	 * <br> This method returns null if the points are colinear.
	 * 
	 * @param a  the first contained vector
	 * @param b  the second contained vector
	 * @param c  the third contained vector
	 * @return  the resulting circle
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
	 * @param x  the circle's center x-coördinate
	 * @param y  the circle's center y-coördinate
	 * @param r  the circle's radius
	 */
	public Circle(float x, float y, float r)
	{
		this(new Vector2(x, y), r);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param center  the circle's center
	 * @param r  the circle's radius
	 * @see Vector2
	 */
	public Circle(Vector2 center, float r)
	{
		super(center, new Vector2(r, r));
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param r  the circle's radius
	 */
	public Circle(float r)
	{
		super(r, r);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 */
	public Circle()
	{
		this(1);
	}
}