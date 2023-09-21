package waffles.utils.geom.collidable.fixed;

import waffles.utils.algebra.Additive;
import waffles.utils.algebra.elements.linear.Angular;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.hulls.IHull;
import waffles.utils.geom.collision.hulls.CLSPoint;
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
 * @see IHull
 */
public class Point implements Angular, IHull
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
			if(!Floats.isZero(m, 1))
				v.set(m * p.get(i), i);
			else
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
	
	/**
	 * Subtracts a {@code Point} to create a vector.
	 * 
	 * @param p  an affine point
	 * @return   a vector difference
	 * 
	 * 
	 * @see Vector
	 */
	public Vector minus(Point p)
	{
		int dim = v.Size()-1;
		Vector d = Vectors.create(dim);
		

		float m1 =   Mass();
		float m2 = p.Mass();
		
		for(int i = 0; i < dim; i++)
		{
			float v1 =   get(i);
			float v2 = p.get(i);
			
			float val = m2 * v1 - m1 * v2;
			d.set(val / (m1 * m2), i);
		}
		
		return d;
	}
	

	@Override
	public Point plus(Additive a)
	{
		if(a instanceof Vector)
		{
			Vector w = (Vector) a;
			w = Vectors.resize(w, v.Size());
			w = v.plus(w.times(Mass()));
			return new Point(w);
		}
		
		return null;
	}
	
	@Override
	public Point times(Float val)
	{
		float m = Mass();
		int dim = v.Size();
		Vector u = v.copy();
		
		u.set(m * val, dim-1);
		return new Point(u);
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
			
			return (float) (dot / (m1 * m2));
		}
		
		return Floats.NaN;
	}

	
	@Override
	public <M extends Matrix> M Generator()
	{
		Vector w = Vectors.resize(v, v.Size() - 1);
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