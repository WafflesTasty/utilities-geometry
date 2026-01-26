package waffles.utils.geom.collide.response.linear.affine;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CNTPoint} computes a containment {@code Response} between an affine space and a point.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class CNTPoint implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTPoint}.
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
		public abstract Point P();

		/**
		 * Returns the source space of the {@code Hints}.
		 * 
		 * @return  a source space
		 * 
		 * 
		 * @see ASpace
		 */
		public abstract ASpace S();
		
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Point dst;
	private Hints hints;

	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public CNTPoint(Hints h)
	{
		hints = h;
	}
	
	/**
	 * Creates a new {@code CNTPoint}.
	 *
	 * @param s  a source space
	 * @param p  a target point
	 *
	 *
	 * @see ASpace
	 * @see Point
	 */
	public CNTPoint(ASpace s, Point p)
	{
		this(new Hints()
		{
			@Override
			public ASpace S()
			{
				return s;
			}
			
			@Override
			public Point P()
			{
				return p;
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
	
	
	@Override
	public Vector Distance()
	{
		if(dst == null)
		{
			Point p = Hints().P();
			ASpace s = Hints().S();
			Point q = s.approx(p);
			dst = q.minus(p);
		}
		
		return dst.Vector();
	}

	@Override
	public boolean hasImpact()
	{
		double e = Hints().Error();
		double dst = Distance().normSqr();
		return dst < e * Dimension();
	}
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return Hints().P();
		}

		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public ASpace Source()
	{
		return Hints().S();
	}
	
	@Override
	public int cost()
	{
		int d = Hints().S().Dimension();
		return 2 * d * (d + 1);
	}
}