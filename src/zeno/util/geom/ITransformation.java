package zeno.util.geom;

import zeno.util.algebra.tensors.matrices.fixed.Matrix4x4;

/**
 * The {@code ITransformation} interface handles a geometric transformation.
 * <br> This transformation takes the form of an invertible 4x4 matrix.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 */
public interface ITransformation
{
	/**
	 * Returns the regular matrix of the {@code ITransformation}.
	 * 
	 * @return  the transformation matrix
	 * @see Matrix4x4
	 */
	public abstract Matrix4x4 Matrix();
	
	/**
	 * Returns the inverse matrix of the {@code ITransformation}.
	 * 
	 * @return  the transformation inverse
	 * @see Matrix4x4
	 */
	public abstract Matrix4x4 Inverse();
}