package waffles.utils.geom.shapes.response.convex.hulls;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.linear.ISCLSpace;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code ISCLSpace} computes an intersection {@code Response} between a convex hull and an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 *
 *
 * @see Response
 */
public class ISCASpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code ISCASpace}.
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
		 * Returns the source hull of the {@code Hints}.
		 * 
		 * @return  a source hull
		 * 
		 * 
		 * @see Hull
		 */
		public abstract Hull S();
		

		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Point x, y;
	private Hints hints;
	private Matrix span;
	
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
		span = h.S().Factory().Span();
		hints = h;
	}
	
	/**
	 * Creates a new {@code ISCASpace}.
	 *
	 * @param s  a source hull
	 * @param t  a target space
	 *
	 *
	 * @see ASpace
	 * @see Hull
	 */
	public ISCASpace(Hull s, ASpace t)
	{
		this(new Hints()
		{
			@Override
			public Hull S()
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
	
	/**
	 * Returns a source {@code Point} x.
	 * This represents a point on the source hull
	 * that is the closest to the target space.
	 * 
	 * @return  a source point
	 * 
	 * 
	 * @see Point
	 */
	public Point X()
	{
		if(x == null)
		{
			compute();
		}
		
		return x;
	}
	
	/**
	 * Returns a target {@code Point} y.
	 * This represents a point on the target space
	 * that is the closest to the source hull.
	 * 
	 * @return  a target point
	 * 
	 * 
	 * @see Point
	 */
	public Point Y()
	{
		if(y == null)
		{
			compute();
		}
		
		return y;
	}

	
	void compute()
	{
		ISCLSpace rsp = null;
		while(1 < span.Columns())
		{
			rsp = response();
			int idx = index(rsp);
			if(0 <= idx)
			{
				span = Matrices.omitColumn(span, idx);
				continue;
			}
			
			break;
		}
		
		if(1 < span.Columns())
		{
			x = rsp.X();
			y = rsp.Y();			
		}
		else
		{
			Vector s = (Vector) span;
			x = new Point(s, 1f);
			if(rsp != null)
				y = rsp.Y();
			else
			{
				ASpace tgt = Hints().T();
				Response rsp2 = tgt.contain(x);
				if(rsp2.hasImpact())
					y = x;
				else
				{
					Vector dst = rsp2.Distance();
					y = x.plus(dst);
				}
			}
		}
	}
	
	ISCLSpace response()
	{
		ASpace tgt = Hints().T();
		ASpace src = new ASpace(span);
		Response rsp = src.intersect(tgt);
		return (ISCLSpace) rsp;
	}
	
	int index(ISCLSpace rsp)
	{
		int idx = -1;
		Hull src = Hints().S();
		float sum = 0f, val = Floats.MAX_VALUE;
		for(int k = 1; k <= src.Factory().Count(); k++)
		{
			float v = rsp.L().get(k - 1);
			
			sum += v;
			if(val > v)
			{
				val = v;
				idx = k;
			}
		}
		
		if(val > 1f - sum)
		{
			val = 1f - sum;
			idx = 0;
		}
		
		if(val < 0f)
			return idx;
		return -1;
	}
	
	@Override
	public boolean hasImpact()
	{
		double err = Hints().Error();
		return X().equals(Y(), err);
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Point dst = X().minus(Y());
			return dst.Vector();
		}

		int dim = Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Point Contact()
	{
		return Y();
	}
	
	@Override
	public Hull Source()
	{
		return Hints().S();
	}

	@Override
	public int cost()
	{
		int n = Dimension();
		int i = Hints().S().Factory().Count();
		
		int k = Hints().S().Factory().Span().Columns();
		int l = Hints().T().Factory().Span().Columns();
		
		return i * l * (2 * n - 1)
		* ((l + n)^2 + (k + l + n)^2);
	}
}