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
 * A {@code CNTSphere} computes a containment {@code Response} between spheres.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTSphere implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTSphere}.
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
		 * Returns the target sphere of the {@code Hints}.
		 * 
		 * @return  a target sphere
		 * 
		 * 
		 * @see HyperSphere
		 */
		public abstract HyperSphere T();

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
	 * Creates a new {@code CNTSphere}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public CNTSphere(Hints h)
	{
		hints = h;
	}
	
	/**
	 * Creates a new {@code CNTSphere}.
	 *
	 * @param s  a source sphere
	 * @param t  a target sphere
	 *
	 *
	 * @see HyperSphere
	 */
	public CNTSphere(HyperSphere s, HyperSphere t)
	{
		this(new Hints()
		{
			@Override
			public HyperSphere S()
			{
				return s;
			}
			
			@Override
			public HyperSphere T()
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

	
	private Vector compute()
	{
		float r = Hints().S().Radius();
		float s = Hints().T().Radius();
		Point p = Hints().S().Origin();
		Point q = Hints().T().Origin();
		Point v = q.minus(p);
		
		float d = q.norm();
		if(Hints().Error() < d)
			q = q.times((r - s - d) / d);
		else
			q = q.times(r - s);
		
		return q.Vector();
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
		float r = Hints().S().Radius();
		float s = Hints().T().Radius();
		Point p = Hints().S().Origin();
		Point q = Hints().T().Origin();
		Point v = q.minus(p);
		
		float n = v.normSqr();
		return n <= (r - s) * (r - s)
			&& s <= r;
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
		return Hints().T().Origin();
	}

	@Override
	public int cost()
	{
		return 3 * Dimension();
	}
}