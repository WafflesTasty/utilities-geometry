package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.Measurable;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.collisions.affine.CLSPoint;
import zeno.util.geom.collidables.geometry.generic.IHull;
import zeno.util.tools.Floats;

/**
 * The {@code Point} class defines a euclidian point in mass-point co�rdinates.
 * Its {@link #Mass()} defines the homogeneous coördinate of a point in the
 * corresponding affine space, if it is non-zero. If it is zero, the
 * object defines a vector in the corresponding vector space.
 * 
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see Measurable
 * @see IHull
 */
public class Point implements Measurable<Point>, IHull
{		
	/**
	 * The {@code Type} enum defines the two {@code Point} subsets.
	 *
	 * @author Zeno
	 * @since Aug 24, 2019
	 * @version 1.0
	 */
	public static enum Type
	{
		/**
		 * An affine point has a non-zero mass.
		 */
		AFFINE,
		/**
		 * A vector point has a zero mass.
		 */
		VECTOR;
	}
	
	
	private Vector v, w;
	
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param v  a point vector
	 */
	public Point(Vector v)
	{
		this.v = v;
	}
	
	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param vals  a set of vector values
	 */
	public Point(float... vals)
	{
		this(Vectors.create(vals));
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
	 * Changes a value in the {@code Point}.
	 * 
	 * @param val  a coördinate value
	 * @param i  a coördinate index
	 */
	public void set(float val, int i)
	{
		float m = Mass();
		if(i >= v.Size() - 1)
		{
			v.set(0f, v.Size() - 1);
			v = Vectors.resize(v, i + 2);
			v.set( m, v.Size() - 1);
		}
		
		
		if(!Floats.isZero(m, 1))
			v.set(m * val, i);
		else
			v.set(val,  i);
	}
	
	/**
	 * Changes the mass of the {@code Point}.
	 * 
	 * @param m  a new point mass
	 */
	public void setMass(float m)
	{
		v.set(m, v.Size()-1);
	}

	/**
	 * Returns a {@code Point} coördinate.
	 * 
	 * @param i  a coördinate index
	 * @return  a point value
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
	 * Returns the {@code Point} mass.
	 * This value is zero for vectors, and acts
	 * as a homogeneous co�rdinate if non-zero.
	 * 
	 * @return  a point mass
	 */
	public float Mass()
	{
		return v.get(v.Size() - 1);
	}
	
	
	/**
	 * Translates the {@code Point} with a vector.
	 * 
	 * @param w  a vector to translate to
	 * @return  a translated point
	 * 
	 * 
	 * @see Vector
	 */
	public Point plus(Vector w)
	{
		Vector hw = Vectors.resize(w.times(Mass()), v.Size());
		return new Point(v.plus(hw));
	}
	
	/**
	 * Returns the {@code Point} as a vector.
	 * 
	 * @return  a point vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector asVector()
	{
		if(w == null)
		{
			w = Vectors.resize(v, v.Size() - 1);
			if(!Floats.isZero(Mass(), 1))
			{
				w = w.times(1f / Mass());
			}
		}
		
		return w;
	}
	
	/**
	 * Returns the {@code Point} type.
	 * A point with non-zero mass is affine, while
	 * a point with zero mass is a vector.
	 * 
	 * @return  a point type
	 * 
	 * 
	 * @see Type
	 */
	public Type Type()
	{
		if(Floats.isZero(Mass(), 1))
			return Type.VECTOR;
		return Type.AFFINE;
	}

	
	@Override
	public Point instance()
	{
		return new Point(v.copy());
	}
	
	@Override
	public Point plus(Point p)
	{
		return new Point(v.plus(p.v));
	}
	
	@Override
	public Point times(float val)
	{
		return new Point(v.times(val));
	}
		
	@Override
	public float dot(Point p)
	{
		return v.dot(p.v);
	}
	
	
//	@Override
//	public Extremum Extremum()
//	{
//		return v -> asVector();
//	}

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
	public Vector Vertices()
	{
		return asVector();
	}
	
	@Override
	public Vector Span()
	{
		return v;
	}

	
	// Obligatory bounds overrides.
	
	@Override
	public float Radius()
	{
		return 0f;
	}
	
	@Override
	public Vector Center()
	{
		return asVector();
	}
	
	@Override
	public Vector Size()
	{
		return Vectors.create(0f, Dimension());
	}

	
	// Optional bounds overrides.
	
	@Override
	public Vector Minimum()
	{
		return asVector();
	}
	
	@Override
	public Vector Maximum()
	{
		return asVector();
	}
}