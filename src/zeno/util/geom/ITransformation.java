package zeno.util.geom;

import zeno.util.algebra.Function;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.ASpaces;

/**
 * The {@code ITransformation} interface defines a transformation in geometric space.
 * Transformations are defined as mappings between homogeneous co�rdinate systems.
 * 
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see Function
 * @see Affine
 */
public interface ITransformation extends Function<Affine, Affine>
{	
	/**
	 * Returns a matrix for the {@code ITransformation}.
	 * 
	 * @param dim  a square matrix dimension
	 * @return  a transformation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix Matrix(int dim);
	
	/**
	 * Returns an inverse for the {@code ITransformation}.
	 * 
	 * @param dim  a square matrix dimension
	 * @return  a transformation inverse
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix Inverse(int dim);

	
	/**
	 * Maps a source to its target {@code Matrix}.
	 * 
	 * @param mat  a source matrix
	 * @return  a target matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public default Matrix map(Matrix mat)
	{
		return Matrix(mat.Rows() - 1).times(mat);
	}
	
	/**
	 * Maps a target to its source {@code Matrix}.
	 * 
	 * @param mat  a target matrix
	 * @return  a source matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public default Matrix unmap(Matrix mat)
	{
		return Inverse(mat.Rows() - 1).times(mat);
	}

	
	@Override
	public default Affine unmap(Affine val)
	{
		Matrix hmat = val.Span().HMatrix();
		if(val instanceof Affine.Set)
		{
			return ASpaces.hset(unmap(hmat));
		}
		
		if(val instanceof ASpace)
		{
			return ASpaces.span(unmap(hmat));
		}
		
		return null;
	}
	
	@Override
	public default Affine map(Affine val)
	{
		Matrix hmat = val.Span().HMatrix();
		if(val instanceof Affine.Set)
		{
			return ASpaces.hset(map(hmat));
		}
		
		if(val instanceof ASpace)
		{
			return ASpaces.span(map(hmat));
		}
		
		return null;
	}
}