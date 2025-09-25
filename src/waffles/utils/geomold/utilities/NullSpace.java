package waffles.utils.geom.utilities;

import waffles.utils.alg.lin.measure.matrix.Matrix;

/**
 * The {@code NullSpace} interface defines a solver for matrix subspaces.
 * Each matrix defines a column space and null space for which a basis can be found.
 * 
 * @author Waffles
 * @since Apr 7, 2019
 * @version 1.1
 */
@Deprecated
public interface NullSpace
{	
	/**
	 * Returns the column space of the {@code LinearSpace}.
	 * 
	 * @return  a column space
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix ColSpace();
	
	
	/**
	 * Returns the col complement of the {@code LinearSpace}.
	 * 
	 * @return  a column space complement
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix ColComplement();
}