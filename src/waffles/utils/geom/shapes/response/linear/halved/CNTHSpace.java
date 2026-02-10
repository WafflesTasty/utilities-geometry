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

/**
 * A {@code CNTHSpace} computes a containment {@code Response} between halfspaces.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class CNTHSpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTHSpace}.
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
		 * Returns the source space of the {@code Hints}.
		 * 
		 * @return  a source space
		 * 
		 * 
		 * @see HSpace
		 */
		public abstract HSpace S();
		
		/**
		 * Returns the target space of the {@code Hints}.
		 * 
		 * @return  a target space
		 * 
		 * 
		 * @see HSpace
		 */
		public abstract HSpace T();
						
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Vector dst;
	private Hints hints;
	private Response rsp;
	
	/**
	 * Creates a new {@code CNTHSpace}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public CNTHSpace(Hints h)
	{
		hints = h;
		
		HSpace s = Hints().S();
		HSpace t = Hints().T();
		
		Point q = t.Origin();
		rsp = s.contain(q);
	}

	/**
	 * Creates a new {@code CNTHSpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see HSpace
	 */
	public CNTHSpace(HSpace s, HSpace t)
	{
		this(new Hints()
		{
			@Override
			public HSpace S()
			{
				return s;
			}
			
			@Override
			public HSpace T()
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
			return Hints().T();
		}

		int n = Dimension();
		return new Void(n);
	}

	@Override
	public boolean hasImpact()
	{
		if(dst == null)
		{
			Vector v = Hints().S().Normal();
			Vector w = Hints().T().Normal();
			
			float l = v.dot(w) / v.dot(v);
			dst = w.minus(v.times(l));
		}

		double e = Hints().Error();
		return dst.normSqr() < e;
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			Point p = Hints().S().Origin();
			Point q = Hints().T().Origin();

			return p.minus(q).Vector();
		}

		int dim = Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Point Contact()
	{
		return Hints().T().Origin();
	}

	@Override
	public int cost()
	{
		return rsp.cost() + 8 * Dimension() - 2;
	}
}