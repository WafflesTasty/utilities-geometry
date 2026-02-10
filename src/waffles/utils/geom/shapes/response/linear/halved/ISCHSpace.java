package waffles.utils.geom.shapes.response.linear.halved;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.lin.solvers.matrix.square.types.LSHouseholder;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * An {@code ISCHSpace} computes an intersection {@code Response} between halfspaces.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class ISCHSpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code ISCHSpace}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see LSHouseholder
	 */
	public static interface Hints extends LSHouseholder.Hints
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
		public default Matrix Matrix()
		{
			Vector v = S().Normal();
			Vector w = T().Normal();
			int d = v.Size();
			
			Matrix m = Matrices.create(2, d);
			for(int c = 0; c < d; c++)
			{
				m.set(v.get(c), 0, c);
				m.set(w.get(c), 1, c);
			}
			
			return m;
		}
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Vector d;
	private Response rsp;
	private LSHouseholder lsh;
	
	/**
	 * Creates a new {@code ISCHSpace}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public ISCHSpace(Hints h)
	{
		lsh = new LSHouseholder(h);
		
		HSpace s = Hints().S();
		HSpace t = Hints().T();
		
		Point q = t.Origin();
		rsp = s.contain(q);
	}

	/**
	 * Creates a new {@code ISCHSpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see HSpace
	 */
	public ISCHSpace(HSpace s, HSpace t)
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
		return (Hints) lsh.Hints();
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
		if(d == null)
		{
			Vector v = Hints().S().Normal();
			Vector w = Hints().T().Normal();
			
			float l = v.dot(w) / v.dot(v);
			d = w.minus(v.times(l));
		}

		double e = Hints().Error();
		return d.normSqr() < e;
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
		HSpace s = Hints().S();
		HSpace t = Hints().T();
		
		Vector v = s.Normal();
		Vector w = t.Normal();
		
		Point p = s.Origin();
		Point q = t.Origin();

		
		if(!rsp.hasImpact())
		{
			float cv = p.dot(v);
			float cw = q.dot(w);
			
			Vector c = lsh.approx(cv, cw);
			return new Point(c, 1f);
		}

		return q;
	}

	@Override
	public int cost()
	{
		return rsp.cost() + 8 * Dimension() - 2;
	}
}