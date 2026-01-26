package waffles.utils.geom.shapes.points;

import waffles.utils.alg.Abelian;
import waffles.utils.alg.lin.Angular;
import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Inaccurate;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.linear.CLSPoint;
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
 * @see Collidable
 * @see Inaccurate
 * @see Angular
 * @see Affine
 */
public class Point implements Angular, Affine, Inaccurate<Point>, Collidable
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
	 * @see Affine
	 */
	public static class Factory implements Affine.Factory
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
		public Affine create(Matrix... set)
		{
			if(set.length > 0)
			{
				Matrix m = set[0];
				Vector s = m.Column(0);
				return Point.create(s);
			}
			
			return null;
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
	 * @param i  a coordinate index
	 * @return   a coordinate value
	 */
	public float aff(int i)
	{
		float m = Floats.abs(Mass());
		if(Collision().Error() < m)
		{
			return hom(i) / Mass();
		}
		
		return hom(i);
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
	
	/**
	 * Computes a Hadamard {@code Point}.
	 * 
	 * @param p  a point
	 * @return  a hadamard product
	 */
	public Point hadamard(Point p)
	{
		float m =   Mass();
		float n = p.Mass();
		
		Vector v =   Energy();
		Vector w = p.Energy();
		
		
		float o = m * n;
		Vector x = v.hadamard(w);
		return new Point(x, o);
	}

	@Override
	public Boolean equals(Point p, double e)
	{
		return Vector().equals(p.Vector(), e);
	}
	
	
	@Override
	public Factory Factory()
	{
		return new Factory(this);
	}
	
	@Override
	public CLSPoint Collision()
	{
		return new CLSPoint(this);
	}
	
	@Override
	public Point times(Float s)
	{
		Vector e = Energy();
		float m = Mass() / s;
		return new Point(e, m);
	}
	
	@Override
	public Point plus(Abelian a)
	{		
		Vector f = null;
		Vector e = Energy();
		float m = Mass();
		float n = 1f;

		if(a instanceof Vector)
		{
			f = (Vector) a;
		}
		
		if(a instanceof Point)
		{
			Point p = (Point) a;
			
			f = p.Energy();
			n = p.Mass();
		}

		
		int d = Dimension();
		Vector s = Vectors.create(d);
		for(int k = 0; k < d; k++)
		{
			float v1 = n * e.get(k);
			float v2 = m * f.get(k);
			
			s.set(v1 + v2, k);
		}
		
		return new Point(s, m * n);
	}
	
	@Override
	public Point minus(Abelian a)
	{
		return plus(((Angular) a).times(-1f));
	}
		
	@Override
	public float dot(Angular a)
	{
		Vector f = null;
		Vector e = Energy();
		float m = Mass();
		float n = 1f;

		if(a instanceof Vector)
		{
			f = (Vector) a;
		}
		
		if(a instanceof Point)
		{
			Point p = (Point) a;
			
			f = p.Energy();
			n = p.Mass();
		}
		
		
		double dot = 0d;
		int d = Dimension();
		for(int k = 0; k < d; k++)
		{
			dot += e.get(k) * f.get(k);
		}
		
		return (float) (dot / (m * n));
	}

	@Override
	public int[] Dimensions()
	{
		int n = Dimension();
		return new int[]{n + 1};
	}
	
	@Override
	public int Dimension()
	{
		return Energy().Size();
	}
}