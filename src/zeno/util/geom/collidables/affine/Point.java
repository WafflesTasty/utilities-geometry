package zeno.util.geom.collidables.affine;

import java.util.Iterator;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.collisions.affine.CLSPoint;
import zeno.util.tools.Floats;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code Point} class defines a zero-dimensional affine space.
 * It implements both the {@code Affine.Space} and {@code Affine.Set}
 * interface to allow it to be used in both applications.
 * 
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class Point implements Affine.Set, Affine.Space
{		
	private Vector hmat;

	/**
	 * Creates a new {@code Point}.
	 * 
	 * @param v  a vector point
	 * 
	 * 
	 * @see Vector
	 */
	public Point(Vector v)
	{
		this(v, 1f);
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
	 * @param v  a vector point
	 * @param h  a homogeneous coördinate
	 * 
	 * 
	 * @see Vector
	 */
	public Point(Vector v, float h)
	{
		hmat = Vectors.resize(v, v.Size() + 1);
		hmat.set(h, v.Size());
	}

	/**
	 * Returns a {@code Point} value.
	 * 
	 * @param i  a coördinate index
	 * @return  a vector value
	 */
	public float get(int i)
	{
		return get(i, 0);
	}
	
	
	/**
	 * Adds a {@code Point}.
	 * 
	 * @param p  a point to add
	 * @return  a result vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector plus(Point p)
	{
		Vector v1 = VMatrix();
		Vector v2 = p.VMatrix();
		return v1.plus(v2);
	}
	
	/**
	 * Subtracts a {@code Point}.
	 * 
	 * @param p  a point to subtract
	 * @return  a result vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector minus(Point p)
	{
		Vector v1 = VMatrix();
		Vector v2 = p.VMatrix();
		return v1.minus(v2);
	}
	
	/**
	 * Subtracts a {@code Vector}.
	 * 
	 * @param v  a vector to subtract
	 * @return  a result point
	 * 
	 * 
	 * @see Vector
	 */
	public Point minus(Vector v)
	{
		return new Point(VMatrix().minus(v));
	}
	
	/**
	 * Adds a {@code Vector}.
	 * 
	 * @param v  a vector to add
	 * @return  a result point
	 * 
	 * 
	 * @see Vector
	 */
	public Point plus(Vector v)
	{
		return new Point(VMatrix().plus(v));
	}
			
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.trivial(hmat.Size() - 1);
	}
	
	@Override
	public Affine.Set Span()
	{
		return this;
	}
	
	@Override
	public Point Origin()
	{
		return this;
	}
	
	
	@Override
	public Vector VMatrix()
	{
		return (Vector) ASpaces.vectorize(hmat);
	}
	
	@Override
	public Matrix HMatrix()
	{
		return hmat;
	}
	
	
	@Override
	public float get(int r, int c)
	{
		int rows = hmat.Rows() - 1;
		if(r < rows)
		{
			float val = hmat.get(r, c);
			if(!Floats.isZero(hmat.get(rows, c), 1))
			{
				val /= hmat.get(rows, c);
			}
			
			return val;
		}
		
		return 0f;
	}
	
	@Override
	public Iterator<Point> iterator()
	{
		return Iterables.singleton(this).iterator();
	}
	
	@Override
	public ICollision Collisions()
	{
		return new CLSPoint(this);
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}
	
	@Override
	public int Dimension()
	{
		return 0;
	}
	
	@Override
	public int Size()
	{
		return 1;
	}
}