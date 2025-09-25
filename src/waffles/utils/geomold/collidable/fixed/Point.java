package waffles.utils.geom.collidable.fixed;

import waffles.utils.alg.Abelian;
import waffles.utils.alg.lin.Angular;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collision.convex.hulls.CLSPoint;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Point} defines an n-dimensional euclidian point in homogeneous coordinates.
 * Its {@link #Mass()} defines the homogeneous coördinate of a point in the
 * corresponding affine space, if it is non-zero. If it is zero, the
 * object defines a vector in the corresponding vector space.
 * 
 * @author Waffles
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see Angular
 * @see Hull
 */
public class Point implements Angular, Hull
{			
	private Vector v;
	
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param v  a homogeneous vector
	 * 
	 * 
	 * @see Vector
	 */
	public Point(Vector v)
	{
		this.v = v;
	}
		
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param p  a point vector
	 * @param m  a point mass
	 * 
	 * 
	 * @see Vector
	 */
	public Point(Vector p, float m)
	{
		v = Vectors.create(p.Size() + 1);
		
		v.set(m, p.Size());
		for(int i = 0; i < p.Size(); i++)
		{
			v.set(p.get(i), i);
		}
	}

	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param vals  point values
	 */
	public Point(float... vals)
	{
		this(Vectors.create(vals));
	}
	
	
	/**
	 * Returns a {@code Point} mass.
	 * This value is zero for vectors,
	 * and non-zero for affine points.
	 * 
	 * @return  a point mass
	 */
	public float Mass()
	{
		return v.get(v.Size()-1);
	}

	/**
	 * Returns a {@code Point} coordinate.
	 * 
	 * @param i  a coordinate index
	 * @return   a coordinate value
	 */
	public float get(int i)
	{
		if(i < v.Size() - 1)
		{
			return v.get(i);
		}
		
		return 0f;
	}

	
	@Override
	public int[] Dimensions()
	{
		return new int[]{v.Size()};
	}

	@Override
	public Point plus(Abelian a)
	{
		Vector w = null;
		if(a instanceof Vector)
			w = (Vector) a;
		if(a instanceof Point)
		{
			Point p = (Point) a;
			
			if(Floats.isZero(p.Mass(), 1))
			{
				w = p.Generator();
			}
		}
		
		if(w != null)
		{
			w = w.resize(v.Size());
			w = v.plus(w.times(Mass()));
			return new Point(w);
		}
		
		return null;
	}
	
	@Override
	public Point minus(Abelian a)
	{
		if(a instanceof Point)
		{			
			Point p = (Point) a;
			float m1 =   Mass();
			float m2 = p.Mass();
			
			
			int dim = v.Size() - 1;
			Vector d = Vectors.create(dim);
			for(int i = 0; i < dim; i++)
			{
				float v1 =   get(i) / m1;
				float v2 = p.get(i) / m2;
				d.set(v1 - v2, i);
			}
			
			return new Point(d, 0f);
		}
		
		return null;
	}
	
	@Override
	public Point times(Float val)
	{
		float m = Mass();
		if(Floats.isZero(m, 1))
		{
			Vector g = Generator();
			Vector w = g.times(val);
			return new Point(w, 0f);
		}
		
		int dim = v.Size();
		Vector w = v.copy();
		
		w.set(m / val, dim-1);
		return new Point(w);
	}
		
	@Override
	public float dot(Angular a)
	{
		if(a instanceof Point)
		{
			Point p = (Point) a;
			int dim = v.Size()-1;
			
			float m1 =   Mass();
			float m2 = p.Mass();
			
			
			double dot = 0d;
			for(int i = 0; i < dim; i++)
			{
				dot += p.get(i) * get(i);
			}
			
			if(!Floats.isZero(m1 * m2, 3))
			{
				dot = dot / (m1 * m2);
			}
			
			return (float) dot;
		}
		
		return Floats.NaN;
	}

		
	@Override
	public <M extends Matrix> M Generator()
	{
		Vector w = v.resize(v.Size() - 1);
		if(!Floats.isZero(Mass(), 1))
		{
			w = w.times(1f / Mass());
		}
		
		return (M) w;
	}
		
	@Override
	public CLSPoint Collisions()
	{
		return new CLSPoint(this);
	}
	
	@Override
	public Factory Factory()
	{
		return (m) ->
		{
			return new Point((Vector) m);
		};
	}
	
	@Override
	public Vector Span()
	{
		return v;
	}
}