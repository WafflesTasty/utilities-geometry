package waffles.utils.geom.collide.response;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CNTPoint} computes a containment {@code Response} between two points.
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
		 * Returns the source point of the {@code Hints}.
		 * 
		 * @return  a source point
		 * 
		 * 
		 * @see Point
		 */
		public abstract Point P();
		
		/**
		 * Returns the target point of the {@code Hints}.
		 * 
		 * @return  a target point
		 * 
		 * 
		 * @see Point
		 */
		public abstract Point Q();
		
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Hints hints;
	private Boolean hasImpact;
		
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param p  a source point
	 * @param q  a target point
	 * 
	 * 
	 * @see Point
	 */
	public CNTPoint(Point p, Point q)
	{
		this(new Hints()
		{
			@Override
			public Point P()
			{
				return p;
			}

			@Override
			public Point Q()
			{
				return q;
			}	
		});
	}
	
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
	public Point Source()
	{
		return Hints().P();
	}
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return Hints().Q();
		}
		
		int dim = Dimension();
		return new Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{			
		if(hasImpact == null)
		{
			Point p = Hints().P();
			Point q = Hints().Q();
			
			double e = Hints().Error();
			hasImpact = p.equals(q, e);
		}
		
		return hasImpact;
	}
	
	@Override
	public Vector Penetration()
	{
		int dim = Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Point p = Hints().P();
			Point q = Hints().Q();
			
			Point d = q.minus(p);
			return d.Vector();
		}
		
		int dim = Dimension();
		return Vectors.create(dim);
	}
	
	@Override
	public Point Contact()
	{
		return Hints().Q();
	}
	
	@Override
	public int cost()
	{
		return Hints().P().Dimension();
	}
}