package zeno.util.geom;

import zeno.util.algebra.linear.matrix.Matrix;

/**
 * The {@code Affine} interface defines a target for affine transformations.
 * It defines an affine set representing the object, together with a factory used to
 * recreate a similar object from a transformed affine set.
 *
 * @author Zeno
 * @since Jul 14, 2019
 * @version 1.0
 * 
 * 
 * @see ICollidable
 */
public interface Affine extends ICollidable
{	
	/**
	 * The {@code Factory} interface defines a generator for {@code Affine} objects.
	 *
	 * @author Zeno
	 * @since Sep 10, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see Affine
	 */
	@FunctionalInterface
	public static interface Factory
	{
		/**
		 * Creates a new {@code Affine} object from a set.
		 * 
		 * @param m  a homogeneous matrix to use
		 * @return  a new affine object
		 * 
		 * 
		 * @see Affine
		 * @see Matrix
		 */
		public abstract Affine create(Matrix m);
	}
		
	
	/**
	 * Returns an affine factory for the {@code Affine}.
	 * 
	 * @return  an affine factory
	 * 
	 * 
	 * @see Factory
	 */
	public default Factory Factory()
	{
		return (m) ->
		{
			return null;
		};
	}

	/**
	 * Returns an affine span for the {@code Affine}.
	 * 
	 * @return  an affine span
	 */
	public abstract Matrix Span();
}