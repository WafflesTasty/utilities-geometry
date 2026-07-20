package waffles.utils.geom.shapes.points;

import waffles.utils.alg.Abelian;
import waffles.utils.alg.lin.DotProduct;
import waffles.utils.alg.lin.InProduct;
import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Hadamard;
import waffles.utils.alg.utilities.Inaccurate;
import waffles.utils.geom.shapes.collision.linear.CLSPoint;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.utilities.Transformator;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Point} defines an n-dimensional affine point in homogeneous coordinates.
 * Its {@link #Mass()} defines its scaling factor, which is assumed to be non-zero.
 * A {@code Point} is essentially defined as a {@code Collidable} wrapper around
 * a {@code Vector} point in space. Its homogeneous coordinate allows it to be
 * transformed by linear maps, and speeds up geometric algorithms.
 * 
 * @author Waffles
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see Transformator
 * @see Inaccurate
 * @see Hadamard
 * @see InProduct
 */
public class Point implements InProduct, Hadamard<Point>, Transformator, Inaccurate<Point>
{	
	/**
	 * Defines the error margin of a {@code Point}.
	 */
	public static final double ERROR = Doubles.pow(2, -8);
	
	/**
	 * A {@code Point.Factory} generates {@code Point} geometry.
	 *
	 * @author Waffles
	 * @since 26 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Transformator
	 */
	public static class Factory implements Transformator.Factory
	{
		private Point src;
		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param s  a source point
		 * 
		 * 
		 * @see Point
		 */
		public Factory(Point s)
		{
			src = s;
		}
		
		
		@Override
		public Transformator create(Matrix m)
		{
			if(m.Columns() == 0)
			{
				int n = m.Rows();
				return new Void(n);
			}
			
			Vector s = m.Column(0);
			return Point.create(s);
		}
		
		@Override
		public Vector Span()
		{
			int n = src.Dimension();
			float mass = src.Mass();
			
			Vector s = src.Energy();
			s = s.resize(n + 1);
			s.set(mass, n);
			return s;
		}
	}
	
	
	/**
	 * Creates a {@code Matrix} span from a {@code Point} set.
	 * 
	 * @param pts  a point set
	 * @return   a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix concat(Point... pts)
	{
		int cols = pts.length;
		int rows = pts[0].Dimension();
		
		Matrix s = Matrices.create(rows + 1, cols);
		for(int c = 0; c < cols; c++)
		{
			Point p = pts[c];
			
			s.set(p.Mass(), rows, c);
			for(int r = 0; r < rows; r++)
			{
				float v = pts[c].hom(r);
				s.set(v, r, c);
			}
		}

		return s;
	}
	
	/**
	 * Creates a {@code Point} from a {@code Vector} span.
	 * 
	 * @param s  a vector span
	 * @return  an affine point
	 * 
	 * 
	 * @see Vector
	 */
	public static Point create(Vector s)
	{
		int n = s.Size() - 1;
		Vector w = s.resize(n);
		float m = s.get(n);
		
		if(Floats.abs(m) < ERROR)
			return new Arrow(w);
		return new Point(w, m);
	}
	
	
	private float mass;
	private Vector vec;
		
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param v  a point vector
	 * @param m  a point mass
	 * 
	 * 
	 * @see Vector
	 */
	public Point(Vector v, float m)
	{
		mass = m;
		vec = v;
	}

	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param vals  point values
	 */
	public Point(float... vals)
	{
		vec = Vectors.create(vals);
		int n = vec.Size() - 1;
		vec = vec.resize(n);
		mass = vals[n];
	}
	
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param n  a point dimension
	 */
	public Point(int n)
	{
		this(Vectors.create(n), 1f);
	}

	
	/**
	 * Returns a similar{@code Arrow}.
	 * 
	 * @return  an arrow
	 * 
	 * 
	 * @see Arrow
	 */
	public Arrow arrow()
	{
		return new Arrow(Vector());
	}
		
	/**
	 * Returns a {@code Point} energy.
	 * 
	 * @return  an energy vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Energy()
	{
		return vec;
	}
	
	/**
	 * Returns a {@code Point} vector.
	 * 
	 * @return  a point vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Vector()
	{
		float m = Floats.abs(Mass());
		if(Collision().Error() < m)
		{
			return vec.times(1f / Mass());
		}
		
		return vec;
	}
	
	/**
	 * Returns a {@code Point} mass.
	 * 
	 * @return  a point mass
	 */
	public float Mass()
	{
		return mass;
	}
	
	
	/**
	 * Returns a {@code Point} x-coordinate.
	 * 
	 * @return  an x-coordinate
	 */
	public float X()
	{
		return aff(0);
	}

	/**
	 * Returns a {@code Point} y-coordinate.
	 * 
	 * @return  an y-coordinate
	 */
	public float Y()
	{
		return aff(1);
	}
	
	/**
	 * Returns a {@code Point} z-coordinate.
	 * 
	 * @return  an z-coordinate
	 */
	public float Z()
	{
		return aff(2);
	}
	
	/**
	 * Returns a {@code Point} w-coordinate.
	 * 
	 * @return  an w-coordinate
	 */
	public float W()
	{
		return aff(3);
	}
	
	
	/**
	 * Returns a homogeneous {@code Point} coordinate.
	 * 
	 * @param k  a coordinate index
	 * @return   a coordinate value
	 */
	public float hom(int k)
	{
		if(k < vec.Size())
		{
			return vec.get(k);
		}
		
		return 0f;
	}

	/**
	 * Returns an affine {@code Point} coordinate.
	 * 
	 * @param k  a coordinate index
	 * @return   a coordinate value
	 */
	public float aff(int k)
	{
		float m = Floats.abs(Mass());
		if(Collision().Error() < m)
		{
			return hom(k) / Mass();
		}
		
		return hom(k);
	}
		

	/**
	 * Computes an absolute {@code Point}.
	 * 
	 * @return  an absolute point
	 */
	public Point absolute()
	{
		Vector v = Energy().absolute();
		float m = Floats.abs(Mass());
		return new Point(v, m);
	}
		
	
	@Override
	public Boolean equals(Point p, double e)
	{
		return Vector().equals(p.Vector(), e);
	}

	@Override
	public CLSPoint Collision()
	{
		return new CLSPoint(this);
	}
	
	@Override
	public Factory Factory()
	{
		return new Factory(this);
	}
	
	
	@Override
	public Point normalize()
	{
		return (Point) InProduct.super.normalize();
	}
	
	@Override
	public Point over(Float s)
	{
		Vector e = Energy();
		float n = Floats.abs( Mass());
		float r = Floats.sign(Mass());
		
		if(n > Floats.MAX_VALUE / Floats.abs(s))
		{
			e = e.times(r / n / s);
			return new Point(e, 1f);
		}

		float m = r * n * s;
		return new Point(e, m);
	}
	
	@Override
	public Point times(Float s)
	{
		return over(1f / s);
	}
	
	@Override
	public Point plus(Abelian a)
	{	
		float n = 1f, s = 1f;
		float m = Floats.abs( Mass());
		float r = Floats.sign(Mass());
		
		Vector e = Energy();
		Vector f = null;

		if(a instanceof Vector)
		{
			f = (Vector) a;
		}
		
		if(a instanceof Point)
		{
			Point p = (Point) a;
			
			n = Floats.abs( p.Mass());
			s = Floats.sign(p.Mass());

			f = p.Energy();
		}

		
		int d = Dimension();
		float eMax = 0f, fMax = 0f;
		for(int k = 0; k < d; k++)
		{
			eMax = Floats.max(eMax, Floats.abs(e.get(k)));
			fMax = Floats.max(fMax, Floats.abs(f.get(k)));
		}

		if(!Floats.isFinite(n * eMax + m * fMax))
		{
			e = e.times(1f / m);
			f = f.times(1f / n);
			m = n = 1f;
		}

		
		Vector v = Vectors.create(d);
		for(int k = 0; k < d; k++)
		{
			float v1 = s * n * e.get(k);
			float v2 = r * m * f.get(k);

			v.set(v1 + v2, k);
		}

		return new Point(v, r * s * m * n);
	}
	
	@Override
	public Point minus(Abelian a)
	{
		return plus(((InProduct) a).times(-1f));
	}
			
	@Override
	public Point hadamard(Point p)
	{
		float r = Floats.sign(  Mass());
		float s = Floats.sign(p.Mass());
		
		float m = Floats.abs(  Mass());
		float n = Floats.abs(p.Mass());
				
		Vector v =   Energy();
		Vector w = p.Energy();
		
		
		Vector x = v.hadamard(w);		
		if(n > Floats.MAX_VALUE / m)
		{
			x = x.times(r * s / m / n);
			return new Point(x, 1f);
		}

		return new Point(x, r * s * m * n);
	}
	
	@Override
	public float dot(DotProduct a)
	{
		Vector f = null;
		Vector e = Vector();
		
		if(a instanceof Vector)
		{
			f = (Vector) a;
		}
		
		if(a instanceof Point)
		{
			Point p = (Point) a;
			f = p.Vector();
		}
		
		
		double dot = 0d;
		int d = Dimension();
		for(int k = 0; k < d; k++)
		{
			dot += e.get(k) * f.get(k);
		}

		return (float) dot;
	}

	@Override
	public int Dimension()
	{
		return Energy().Size();
	}
}