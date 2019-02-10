package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.matrix.Matrix;

/**
 * The {@code IRotatable} interface defines an object
 * capable of being rotated in an affine space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface IRotatable
{
	/**
	 * Returns the basis of the {@code IRotatable}.
	 * 
	 * @return  a basis matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix Basis();
	
	/**
	 * Rotates the {@code IRotatable} to a new basis.
	 * 
	 * @param m  a basis matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract void rotateTo(Matrix m);
	
	/**
	 * Rotates the {@code IRotatable} from its basis.
	 * 
	 * @param m  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public default void rotateFor(Matrix m)
	{
		rotateTo(m.times(Basis()));
	}
}