package waffles.utils.geom.collide.response.linear;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.lin.solvers.matrix.ranks.types.RRSVD;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.linear.VSpace;
import waffles.utils.geom.shapes.linear.VSpace.Direct;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * An {@code ISCLSpace} computes an intersection {@code Response} between linear spaces.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class ISCLSpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code ISCLSpace}.
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
		 * @see VSpace
		 */
		public abstract VSpace.Direct T();

		/**
		 * Returns the source space of the {@code Hints}.
		 * 
		 * @return  a source space
		 * 
		 * 
		 * @see VSpace
		 */
		public abstract VSpace.Direct S();
		
		
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
	private Matrix dir;
	private Vector l, m;
	private Point x, y;

	/**
	 * Creates a new {@code ISCLSpace}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public ISCLSpace(Hints h)
	{
		svd = new RRSVD(h);
	}
	
	/**
	 * Creates a new {@code ISCLSpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see VSpace
	 */
	public ISCLSpace(Direct s, Direct t)
	{
		this(new Hints()
		{
			@Override
			public VSpace.Direct S()
			{
				return s;
			}
			
			@Override
			public VSpace.Direct T()
			{
				return t;
			}
		});
	}

	/**
	 * Returns {@code Response} direction.
	 * 
	 * @return  a direction matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix Direction()
	{
		if(dir == null)
		{
			dir = svd.Kernel();
			if(dir.Columns() > 0)
			{
				Matrix src = Hints().S().Direction();
				
				int r = src.Columns();
				int c = dir.Columns();
				
				dir = dir.resize(r, c);
				dir = src.times(dir);
			}
		}
		
		return dir;
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
	
	
	/**
	 * Returns a source {@code Vector} L.
	 * This represents the first half of a
	 * least squares solution between
	 * two linear spaces.
	 * 
	 * @return  a source vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector L()
	{
		if(l == null)
		{
			compute();
		}
		
		return l;
	}
	
	/**
	 * Returns a target {@code Vector} M.
	 * This represents the second half of a
	 * least squares solution between
	 * two linear spaces.
	 * 
	 * @return  a target vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector M()
	{
		if(m == null)
		{
			compute();
		}
		
		return m;
	}
	
	/**
	 * Returns a source {@code Point} x.
	 * This represents a point on the source space
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
			Matrix src = Hints().S().Direction();
			Point p = Hints().S().Origin();
			x = p.plus(src.times(L()));
		}
		
		return x;
	}
	
	/**
	 * Returns a target {@code Point} y.
	 * This represents a point on the target space
	 * that is the closest to the source space.
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
			Matrix tgt = Hints().T().Direction();
			Point q = Hints().T().Origin();
			y = q.minus(tgt.times(M()));
		}
		
		return y;
	}

	
	void compute()
	{
		Matrix sum = Hints().Matrix();
		Matrix src = Hints().S().Direction();
		Matrix tgt = Hints().T().Direction();

		Point p = Hints().S().Origin();
		Point q = Hints().T().Origin();
		Point o = q.minus(p);

		
		Vector u = o.Vector();
		Vector v = svd.approx(u);
		Vector w = sum.times(v);

		int r1 = src.Columns();
		int r2 = tgt.Columns();


		l = v.resize(r1);
		m = Vectors.create(r2);
		for(int k = 0; k < r2; k++)
		{
			float val = v.get(r1 + k);
			m.set(val, k);
		}
	}

	@Override
	public VSpace Source()
	{
		return Hints().S();
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