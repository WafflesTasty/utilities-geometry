package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ITransformation;
import zeno.util.tools.patterns.properties.Copyable;

/**
 * The {@code Translation} class defines an affine translation.
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
public class Translation implements Copyable<Translation>, ITransformation
{
	private static Vector DefaultOrigin(int dim)
	{
		return Vectors.create(dim);
	}
	
	/**
	 * Returns a {@code Translation} to an origin vector.
	 * 
	 * @param v  an origin vector
	 * @return  a translation
	 * 
	 * 
	 * @see Vector
	 */
	public static Translation of(Vector v)
	{
		return new Translation(v);
	}
		
	
	private Vector origin;
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param dim  a space dimension
	 */
	public Translation(int dim)
	{
		origin = DefaultOrigin(dim);
	}
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param v  an origin vector
	 * @param dim  a space dimension
	 * 
	 * 
	 * @see Vector
	 */
	public Translation(Vector v, int dim)
	{
		this(Vectors.resize(v, dim));
	}

	/**
	 * Creates a new {@ode Translation}.
	 * 
	 * @param v  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Translation(Vector v)
	{
		origin = v;
	}
		
	/**
	 * Returns the origin vector.
	 * 
	 * @return  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Origin()
	{
		return origin;
	}
		
	
	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Matrices.identity(dim + 1);
		for(int d = 0; d < dim; d++)
		{
			if(d < origin.Size())
			{
				m.set(-origin.get(d), d, dim);
			}
		}
		
		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		Matrix m = Matrices.identity(dim + 1);
		for(int d = 0; d < dim; d++)
		{
			if(d < origin.Size())
			{
				m.set(origin.get(d), d, dim);
			}
		}
		
		return m;
	}
	
	
	@Override
	public Translation instance()
	{
		return new Translation(origin);
	}
	
	
	@Override
	public int DimOut()
	{
		return origin.Size();
	}
	
	@Override
	public int DimIn()
	{
		return origin.Size();
	}
}