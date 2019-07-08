package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ITransformation;
import zeno.util.geom.transforms.types.Translator;

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
 */
public class Translation implements ITransformation
{
	private static Vector DefaultOrigin(int dim)
	{
		return Vectors.create(dim);
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
	 * @param o  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Translation(Vector o)
	{
		origin = o;
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
		m.setOperator(Translator.Type());
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
		m.setOperator(Translator.Type());
		for(int d = 0; d < dim; d++)
		{
			if(d < origin.Size())
			{
				m.set(origin.get(d), d, dim);
			}
		}
		
		return m;
	}
}