package waffles.utils.geom.collide.response.linear;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.LSpace;
import waffles.utils.geom.shapes.linear.VSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code APXPoint} computes an approximation {@code Response} for a one-dimensional linear space.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class APXPoint implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code APXPoint}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see Algorithmic
	 */
	public static interface Hints extends Algorithmic
	{		
		/**
		 * Returns the target point of the {@code Hints}.
		 * 
		 * @return  a target point
		 * 
		 * 
		 * @see Point
		 */
		public abstract Point T();

		/**
		 * Returns the source space of the {@code Hints}.
		 * 
		 * @return  a source space
		 * 
		 * 
		 * @see LSpace
		 */
		public abstract LSpace S();
		
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Float l;
	private Point y;
	private Hints hints;

	/**
	 * Creates a new {@code APXPoint}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public APXPoint(Hints h)
	{
		hints = h;
	}
	
	/**
	 * Creates a new {@code APXPoint}.
	 *
	 * @param s  a source space
	 * @param t  a target point
	 *
	 *
	 * @see VSpace
	 * @see Point
	 */
	public APXPoint(LSpace s, Point t)
	{
		this(new Hints()
		{
			@Override
			public LSpace S()
			{
				return s;
			}
			
			@Override
			public Point T()
			{
				return t;
			}
		});
	}
	
	/**
	 * Returns {@code Response} hints.
	 * 
	 * @return  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return hints;
	}
	
	/**
	 * Returns a source {@code Point} y.
	 * This represents a point on the source
	 * space that is the closest to x.
	 * 
	 * @return  a source point
	 * 
	 * 
	 * @see Point
	 */
	public Point Y()
	{
		if(y == null)
		{
			Vector v = Hints().S().Direction();
			Point p = Hints().S().Origin();
			
			y = p.plus(v.times(L()));
		}
		
		return y;
	}
	
	/**
	 * Returns a source {@code Lambda}.
	 * This is the coefficient for the closest
	 * point on the {@code LSpace}.
	 * 
	 * @return  a lambda value
	 */
	public float L()
	{
		if(l == null)
		{
			Point x = Hints().T();
			Point p = Hints().S().Origin();
			Vector v = Hints().S().Direction();
			double e = Hints().Error();
			
			float dv = v.dot(v);
			float dp = p.minus(x).dot(v);
			if(e < Floats.abs(dv))
				l = dp / dv;
			else
				l = 0.5f;
		}
		
		return l;
	}
	
	
	@Override
	public VSpace Source()
	{
		return Hints().S();
	}
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return Hints().T();
		}

		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public boolean hasImpact()
	{
		double e = Hints().Error();
		double dst = Distance().normSqr();
		return dst < e * Dimension();
	}
	
	@Override
	public Vector Distance()
	{
		Point dst = Y().minus(Hints().T());
		return dst.Vector();
	}

	@Override
	public int cost()
	{
		int d = Hints().S().Dimension();
		return 2 * d * (d + 1);
	}
}