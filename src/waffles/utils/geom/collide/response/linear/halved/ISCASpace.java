	package waffles.utils.geom.collide.response.linear.halved;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code ISCASpace} computes an intersection {@code Response} between a halfspace and an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class ISCASpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTASpace}.
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
		 * Returns the target space of the {@code Hints}.
		 * 
		 * @return  a target space
		 * 
		 * 
		 * @see ASpace
		 */
		public abstract ASpace T();
		
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
	
	
	private Vector n;
	private Hints hints;
	private Response rsp;

	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public ISCASpace(Hints h)
	{
		hints = h;
		
		HSpace s = Hints().S();
		ASpace t = Hints().T();
		
		Point o = t.Origin();
		rsp = s.contain(o);
	}

	/**
	 * Creates a new {@code ISCASpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see HSpace
	 * @see ASpace
	 */
	public ISCASpace(HSpace s, ASpace t)
	{
		this(new Hints()
		{
			@Override
			public HSpace S()
			{
				return s;
			}
			
			@Override
			public ASpace T()
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
	public Collidable Source()
	{
		return Hints().S();
	}

	@Override
	public boolean hasImpact()
	{	
		HSpace s = Hints().S();
		ASpace t = Hints().T();
		
		if(rsp.hasImpact())
		{
			return true;
		}

		if(n == null)
		{
			Matrix d = t.Direction();
			Vector v = s.Normal();
			n = d.times(v);			
		}
		
		double e = Hints().Error();
		return n.normSqr() < e;
	}

	@Override
	public Point Contact()
	{
		HSpace s = Hints().S();
		ASpace t = Hints().T();
		
		if(rsp.hasImpact())
		{
			return t.Origin();
		}
		
		
		Vector v = s.Normal();
		Matrix d = t.Direction();
		Vector w = d.times(v);
		
		int m = 0;
		float dot = 0f;
		for(int i = 0; i < d.Columns(); i++)
		{
			float x = w.get(i);
			if(dot < Floats.abs(x))
			{
				dot = x;
				m = i;
			}
		}
		
		
		Point p = s.Origin();
		Point q = t.Origin();
		Point r = p.minus(q);
		
		Vector u = d.Column(m);
		float l = r.dot(v) / dot;
		return q.plus(u.times(l));
	}

	@Override
	public int cost()
	{
		int n = Dimension();
		int m = 2 * n * (n + 1) - 1;
		return rsp.cost() + m;
	}
}