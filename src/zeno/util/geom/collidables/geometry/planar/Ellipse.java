package zeno.util.geom.collidables.geometry.planar;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.calc.variables.functions.Polynomial;
import zeno.util.geom.collidables.geometry.Geometry2D;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.tools.Floats;

/**
 * The {@code Ellipse} class defines a two-dimensional ellipsoid shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see Geometry2D
 * @see IEllipsoid
 */
public class Ellipse extends Geometry2D implements IEllipsoid
{
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param x  an ellipse center x-coördinate
	 * @param y  an ellipse center y-coördinate
	 * @param w  an ellipse width
	 * @param h  an ellipse height
	 */
	public Ellipse(float x, float y, float w, float h)
	{
		this(new Vector2(x, y), new Vector2(w, h));
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param c  an ellipse center
	 * @param s  an ellipse size
	 * 
	 * 
	 * @see Vector2
	 */
	public Ellipse(Vector2 c, Vector2 s)
	{
		super(c, s);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param w  an ellipse width
	 * @param h  an ellipse height
	 */
	public Ellipse(float w, float h)
	{
		this(0, 0, w, h);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param s  an ellipse size
	 * 
	 * 
	 * @see Vector2
	 */
	public Ellipse(Vector2 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 */
	public Ellipse()
	{
		super();
	}
	
	
	/**
	 * Calculates the tangents to the {@code Ellipse}.
	 * 
	 * @param v  a tangent source vector
	 * @return  an array of two tangents
	 * 
	 * 
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
}