package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.types.banded.Diagonal;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ITransformation;
import zeno.util.tools.Integers;
import zeno.util.tools.patterns.properties.Copyable;

/**
 * The {@code Dilation} interface defines an affine dilation.
 * It is defined as a homogeneous linear transformation
 * through the {@code ITransformation} interface.
 *
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 * @see Copyable
 */
public class Dilation implements Copyable<Dilation>, ITransformation
{
	private static Vector DefaultSize(int dim)
	{
		Vector v = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			v.set(2f, i);
		}
		
		return v;
	}
	
	/**
	 * Returns a {@code Dilation} to a size vector.
	 * 
	 * @param v  a size vector
	 * @return  a dilation
	 * 
	 * 
	 * @see Vector
	 */
	public static Dilation of(Vector v)
	{
		return new Dilation(v);
	}
	
	
	private Vector size;
	
	/**
	 * Creates a new {@code Dilation}.
	 * 
	 * @param dim  a space dimension
	 */
	public Dilation(int dim)
	{
		size = DefaultSize(dim);
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 * 
	 * @param v  a size vector
	 * @param dim  a space dimension
	 * 
	 * 
	 * @see Vector
	 */
	public Dilation(Vector v, int dim)
	{		
		size = DefaultSize(dim);
		for(int i = 0; i < Integers.min(v.Size(), dim); i++)
		{
			size.set(v.get(i), i);
		}
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 * 
	 * @param v  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public Dilation(Vector v)
	{
		size = v;
	}
		
	/**
	 * Returns the size vector.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Size()
	{
		return size;
	}
	
	
	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Matrices.identity(dim + 1);
		m.setOperator(Diagonal.Type());
		for(int d = 0; d < dim; d++)
		{
			if(d < size.Size())
			{
				m.set(1f / size.get(d), d, d);
			}
		}
		
		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		Matrix m = Matrices.identity(dim + 1);
		m.setOperator(Diagonal.Type());
		for(int d = 0; d < dim; d++)
		{
			if(d < size.Size())
			{
				m.set(size.get(d), d, d);
			}
		}
		
		return m;
	}

	
	@Override
	public Dilation instance()
	{
		return new Dilation(size);
	}
	
	
	@Override
	public int DimOut()
	{
		return size.Size();
	}
	
	@Override
	public int DimIn()
	{
		return size.Size();
	}
}