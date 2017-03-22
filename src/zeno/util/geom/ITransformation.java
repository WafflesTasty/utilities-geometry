package zeno.util.geom;

import zeno.util.algebra.tensors.matrices.fixed.Matrix4x4;

/**
 * The {@code ITransformation} interface defines an object
 * capable of transforming a {@code ITransformable} object.
 *
 * @since Apr 22, 2016
 * @author Zeno
 */
public interface ITransformation
{		
	/**
	 * Returns the inverse of the {@code Transformation}.
	 * 
	 * @return  the transformation inverse
	 * @see Matrix4x4
	 */
	public abstract Matrix4x4 getInverse();

	/**
	 * Returns the matrix of the {@code Transformation}.
	 * 
	 * @return  the transformation matrix
	 * @see Matrix4x4
	 */
	public abstract Matrix4x4 getMatrix();
}