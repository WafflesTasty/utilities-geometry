package waffles.utils.geom.collide.response.linear.affine;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.solvers.matrix.ranks.types.RRSVD;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CNTASpace} computes a containment {@code Response} between affine spaces.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTASpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTASpace}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see RRSVD
	 */
	public static interface Hints extends RRSVD.Hints
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
		 * @see ASpace
		 */
		public abstract ASpace S();
		
		
		@Override
		public default Matrix Matrix()
		{
			Matrix m1 = S().Direction();
			Matrix m2 = T().Direction();
			
			return Matrices.concat(m1, m2);
		}
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private RRSVD svd;
	private Boolean hasImpact;
	
	/**
	 * Creates a new {@code CNTASpace}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public CNTASpace(Hints h)
	{
		svd = new RRSVD(h);
	}
	
	/**
	 * Creates a new {@code CNTASpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see ASpace
	 */
	public CNTASpace(ASpace s, ASpace t)
	{
		this(new Hints()
		{
			@Override
			public ASpace S()
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
		return (Hints) svd.Hints();
	}
	
	
	@Override
	public ASpace Source()
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
		if(hasImpact == null)
		{
			float r = Hints().S().rank();
			if(r == svd.rank())
			{
				Matrix m = Hints().Matrix();
				Point p = Hints().S().Origin();
				Point q = Hints().T().Origin();
				
				Vector v = q.minus(p).Vector();
				Vector w = m.times(svd.approx(v));
	
				double n = w.minus(v).normSqr();
				double e = Hints().Error();
				hasImpact = n < e;
			}
			
			hasImpact = false;
		}
		
		return hasImpact;
	}
	
	@Override
	public Point Contact()
	{
		return Hints().T().Origin();
	}
	
	@Override
	public int cost()
	{
		int n = Dimension();
		int i = Hints().MaxLoops();
		
		int k = Hints().S().Factory().Span().Columns();
		int l = Hints().T().Factory().Span().Columns();
		
		return i * l * (2 * n - 1)
		* ((l + n)^2 + (k + l + n)^2);
	}
}