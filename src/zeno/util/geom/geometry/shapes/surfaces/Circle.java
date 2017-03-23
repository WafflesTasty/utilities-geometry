package zeno.util.geom.geometry.shapes.surfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.calc.variables.functions.Polynomial;
import zeno.util.geom.geometry.shapes.NSphere;
import zeno.util.geom.geometry.types.IShape2D;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Circle} class defines a two-dimensional circular shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see IShape2D
 * @see NSphere
 */
public class Circle extends NSphere implements IShape2D
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
		super(center, r);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 * 
	 * @param r  the circle's radius
	 */
	public Circle(float r)
	{
		super(2, r);
	}
	
	/**
	 * Creates a new {@code Circle}.
	 */
	public Circle()
	{
		this(1);
	}

	
	/**
	 * Calculates the tangents to the {@code Ellipse}.
	 * 
	 * @param v  a tangent source vector
	 * @return  an array of two tangents
	 * @see Vector2
	 */
	public Vector2[] tangentsTo(Vector2 v)
	{
		float p = v.X() - X();
		float q = v.Y() - Y();
		float s = Height() / 2;
		float r = Width() / 2;
		
		
		float rr = Floats.pow(r, 2);
		float ss = Floats.pow(s, 2);
		
		float ps = Floats.pow(p * s, 2);
		float qr = Floats.pow(q * r, 2);
		float rs = Floats.pow(r * s, 2);
				
		
		float a = ps + qr;
		float b = -2 * p * rs;
		float c = rr * (rs - qr);
		
		
		Polynomial poly = new Polynomial(c, b, a);
		float[] roots = poly.RealRoots();
		if(roots.length == 0)
		{
			return new Vector2[0];
		}
		
		
		float[] tangentx = new float[2];
		float[] tangenty = new float[2];

		tangentx[0] = roots.length == 1 ? roots[0] : Math.min(roots[0], roots[1]);
		tangentx[1] = roots.length == 1 ? roots[0] : Math.max(roots[0], roots[1]);
		
		tangenty[0] = Floats.sqrt(ss * (1 - tangentx[0] * tangentx[0] / rr));
		tangenty[1] = Floats.sqrt(ss * (1 - tangentx[1] * tangentx[1] / rr));
		
		tangentx[0] += X();
		tangentx[1] += X();
		
		
		boolean isSigned = false;
		if(-r < p && 0 < q)
		{
			isSigned = true;
			if(r <= p || 0 >= q)
			{
				tangenty[1] *= -1;
			}
		}
		
		if(!isSigned)
		{
			if(r > p && 0 > q)
			{
				isSigned = true;
				tangenty[1] *= -1;
				if(-r <= p || 0 <= q)
				{
					tangenty[0] *= -1;
				}
			}
		}
		
		if(!isSigned)
		{
			tangenty[0] *= -1;
		}

		tangenty[0] += Y();
		tangenty[1] += Y();
				
		return new Vector2[]
		{
			new Vector2(tangentx[0], tangenty[0]),
			new Vector2(tangentx[1], tangenty[1]),
		};
	}
	
	
	@Override
	public Rectangle Bounds()
	{
		return IShape2D.super.Bounds();
	}
	
	@Override
	public Vector2 Center()
	{
		return (Vector2) super.Center();
	}
	
	@Override
	public Vector2 Size()
	{
		return (Vector2) super.Size();
	}
}