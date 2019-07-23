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
	private Vector vmat;

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
		vmat = v;
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
		return new Point(vmat.plus(v));
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
		
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.trivial(vmat.Size());
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
		return vmat;
	}
	
	@Override
	public Matrix HMatrix()
	{
		return ASpaces.homogenize(VMatrix());
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