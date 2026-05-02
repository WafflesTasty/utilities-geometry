package waffles.utils.geom.shapes.points;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.utilities.Transformator;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Arrow} defines an n-dimensional affine vector in homogeneous coordinates.
 * Its {@code Span()} vector is represented with a homogeneous coordinate equal to zero,
 * while internally still maintaining a non-zero mass to facilitate computations.
 *
 * @author Waffles
 * @since 26 Jan 2026
 * @version 1.1
 *
 * 
 * @see Point
 */
public class Arrow extends Point
{
	/**
	 * A {@code Arrow.Factory} generates {@code Arrow} geometry.
	 *
	 * @author Waffles
	 * @since 26 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Point
	 */
	public class Factory extends Point.Factory
	{
		/**
		 * Creates a new {@code Factory}.
		 */
		public Factory()
		{
			super(Arrow.this);
		}

		
		@Override
		public Transformator create(Matrix s)
		{
			if(s.Columns() == 0)
			{
				int n = s.Rows();
				return new Void(n);
			}
			
			Vector v = s.Column(0);
			int n = v.Size() - 1;
			Vector w = v.resize(n);
			return new Arrow(w);
		}
		
		@Override
		public Vector Span()
		{
			int n = Dimension();
			Vector s = Vectors.create(n + 1);
			for(int k = 0; k < n; k++)
			{
				s.set(aff(k), k);
			}

			return s;
		}
	}
	
	/**
	 * Creates an {@code Arrow} from a value and a dimension.
	 * 
	 * @param s  an arrow value
	 * @param d  an arrow dimension
	 * @return   an affine arrow
	 */
	public static Arrow create(float s, int d)
	{
		Vector v = Vectors.create(d);
		for(int k = 0; k < d; k++)
		{
			v.set(s, k);
		}

		return new Arrow(v);
	}
	
	/**
	 * Creates an {@code Arrow} from a {@code Vector} span.
	 * 
	 * @param s  a vector span
	 * @return   an affine arrow
	 * 
	 * 
	 * @see Vector
	 */
	public static Arrow create(Vector s)
	{
		int n = s.Size() - 1;
		Vector w = s.resize(n);
		float m = s.get(n);
		
		if(Floats.abs(m) < ERROR)
		{
			return new Arrow(w);
		}

		w = w.times(1f / m);
		return new Arrow(w);
	}
	
		
	/**
	 * Creates a new {@code Arrow}.
	 * 
	 * @param e  an arrow vector
	 * @param m  an arrow mass
	 * 
	 * 
	 * @see Vector
	 */
	public Arrow(Vector e, float m)
	{
		super(e, m);
	}
	
	/**
	 * Creates a new {@code Arrow}.
	 * 
	 * @param vals  arrow values
	 */
	public Arrow(float... vals)
	{
		this(Vectors.create(vals));
	}
	
	/**
	 * Creates a new {@code Arrow}.
	 * 
	 * @param e  an arrow vector
	 * 
	 * 
	 * @see Vector
	 */
	public Arrow(Vector e)
	{
		this(e, 1f);
	}

	/**
	 * Creates a new {@code Arrow}.
	 * 
	 * @param n  an arrow dimension
	 */
	public Arrow(int n)
	{
		this(Vectors.create(n));
	}
	

	@Override
	public Point.Factory Factory()
	{
		return new Factory();
	}
	
	@Override
	public Arrow times(Float s)
	{
		return over(1f / s);
	}
	
	@Override
	public Arrow over(Float s)
	{
		Vector e = Energy();
		float n = Floats.abs( Mass());
		float r = Floats.sign(Mass());
		
		if(n > Floats.MAX_VALUE / Floats.abs(s))
		{
			e = e.times(r / Mass() / s);
			return new Arrow(e, 1f);
		}
		
		float m = r * Mass() * s;
		return new Arrow(e, m);
	}
	
	@Override
	public Arrow absolute()
	{
		Vector v = Energy().absolute();
		float m = Floats.abs(Mass());
		return new Arrow(v, m);
	}

	@Override
	public Arrow arrow()
	{
		return this;
	}
}
