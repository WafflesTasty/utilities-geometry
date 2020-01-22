package zeno.util.geom.utilities.spin;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;

/**
 * The {@code Spin} interface defines a data object used to construct rotations.
 * 
 * 
 * @author Zeno
 * @since Dec 26, 2019
 * @version 1.0
 */
public interface Spin
{
	/**
	 * Generates an affine matrix from the {@code Spin}.
	 * 
	 * @param dim  an affine dimension
	 * @return  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix generate(int dim);

	/**
	 * Composes the spin with another {@code Spin}.
	 * 
	 * @param spin  a spin to compose with
	 * @return  a composite spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract Spin compose(Spin spin);
	
	/**
	 * Returns a basis vector in the {@code Spin}.
	 * 
	 * @param i  a vector index
	 * @return  a basis vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector vector(int i);
}