package waffles.utils.geom.shapes.response.linear.halved;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code CNTPoint} computes a containment {@code Response} between a halfspace and a point.
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
		 * @see HSpace
		 */
		public abstract HSpace S();
				
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Float dot;
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
	 * @see HSpace
	 * @see Point
	 */
	public CNTPoint(HSpace s, Point p)
	{
		this(new Hints()
		{
			@Override
			public HSpace S()
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
	public HSpace Source()
	{
		return Hints().S();
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
	public boolean hasImpact()
	{
		if(dot == null)
		{
			double e = Hints().Error();
			Vector v = Hints().S().Normal();
			Point p = Hints().S().Origin();
			
			Point x = Hints().P();
			Point xp = x.minus(p);
			
			float dv = v.dot(v);
			float dp = xp.dot(v);
			
			if(e < Floats.abs(dv))
				dot = dp / dv;
			else
				dot = 0.5f;
		}

		return dot >= 0;
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			Vector v = Hints().S().Normal();
			return v.times(-dot);
		}

		int dim = Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector v = Hints().S().Normal();
			return v.times(-dot);
		}

		int dim = Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Point Contact()
	{
		return Hints().P();
	}

	@Override
	public int cost()
	{
		return 3 * Dimension() - 1;
	}
}