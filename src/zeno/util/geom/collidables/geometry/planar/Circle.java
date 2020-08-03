package zeno.util.geom.collidables.geometry.planar;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.calc.Operators;
import zeno.util.geom.collidables.geometry.generic.ISphere;

/**
 * The {@code Circle} class defines a two-dimensional circular shape.
 * 
 * @author Zeno
 * @since Mar 21, 2017
 * @version 1.0
 * 
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
	 * 
	 * 
	 * @see Vector2
	 */
	public static Circle through(Vector2 a, Vector2 b, Vector2 c)
	{
		if(Operators.isColinear(a, b, c))
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
		
		float as = a.normSqr();
		float bs = b.normSqr();
		float cs = c.normSqr();
		
		float d = (x1 * y32 + x2 * y13 + x3 * y21) * 2;
		float x = (as * y32 + bs * y13 + cs * y21) / d;
		float y = (as * x23 + bs * x31 + cs * x12) / d;
		
		Vector2 v = new Vector2(x, y);
		return new Circle(v, v.dist(a));
	}
	
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param x  a circle center x-co�rdinate
	 * @param y  a circle center y-co�rdinate
	 * @param r  a circle radius
	 */
	public Circle(float x, float y, float r)
	{
		this(new Vector2(x, y), r);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param c  a circle center
	 * @param r  a circle radius
	 * 
	 * 
	 * @see Vector2
	 */
	public Circle(Vector2 c, float r)
	{
		super(c, new Vector2(2 * r, 2 * r));
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param r  a circle radius
	 */
	public Circle(float r)
	{
		super(2 * r, 2 * r);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 */
	public Circle()
	{
		super();
	}

	
	// Optional Bounds overrides.
	
	@Override
	public Circle Ball()
	{
		return this;
	}
}