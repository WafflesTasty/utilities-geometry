package waffles.utils.geom.shapes.response.convex.spheres;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CNTPoint} computes a containment {@code Response} between a sphere and a point.
 *
 * @author Waffles
 * @since 12 May 2021
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
		public abstract Point X();

		/**
		 * Returns the source sphere of the {@code Hints}.
		 * 
		 * @return  a source sphere
		 * 
		 * 
		 * @see HyperSphere
		 */
		public abstract HyperSphere S();
		
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
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
	 * @param s  a source sphere
	 * @param x  a target point
	 *
	 *
	 * @see HyperSphere
	 * @see Point
	 */
	public CNTPoint(HyperSphere s, Point x)
	{
		this(new Hints()
		{
			@Override
			public HyperSphere S()
			{
				return s;
			}
			
			@Override
			public Point X()
			{
				return x;
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

	
	private Vector compute()
	{
		Point p = Hints().S().Origin();
		Point v = Hints().X().minus(p);
		float r = Hints().S().Radius();

		float d = v.norm();
		if(Hints().Error() < d)
			v = v.times((r - d) / d);
		else
			v = v.times(r);

		return v.Vector();
	}

	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return Hints().X();
		}

		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public boolean hasImpact()
	{
		Point p = Hints().S().Origin();
		Point v = Hints().X().minus(p);
		float r = Hints().S().Radius();
		
		float n = v.normSqr();
		return n <= r * r;
	}
	
	@Override
	public HyperSphere Source()
	{
		return Hints().S();
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			return compute();
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return compute();
		}
		
		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Point Contact()
	{
		return Hints().X();
	}

	@Override
	public int cost()
	{
		return 3 * Dimension();
	}
}