package waffles.utils.geom.spatial.maps;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;

/**
 * A {@code GlobalMap} is a linear map which delegates matrix
 * construction to {@code LazyMatrix} objects. The direct map
 * generates a unit-to-world matrix, while the inverse map
 * generates a world-to-unit matrix.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see LinearMap
 */
public interface GlobalMap extends LinearMap
{	
	/**
	 * Returns the unit-to-world matrix of the {@code GlobalMap}.
	 * 
	 * @return  a lazy matrix
	 * 
	 * 
	 * @see LazyMatrix
	 */
	public abstract LazyMatrix UTW();
	
	/**
	 * Returns the world-to-unit matrix of the {@code GlobalMap}.
	 * 
	 * @return  a lazy matrix
	 * 
	 * 
	 * @see LazyMatrix
	 */
	public abstract LazyMatrix WTU();
	
	/**
	 * Forces the {@code GlobalMap} to recompute.
	 */
	public default void setChanged()
	{
		UTW().setChanged();
		WTU().setChanged();
	}

	
	@Override
	public default Matrix Inverse(int dim)
	{
		return WTU().Value(dim);
	}
	
	@Override
	public default Matrix Matrix(int dim)
	{
		return UTW().Value(dim);
	}
}