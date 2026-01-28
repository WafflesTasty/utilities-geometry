package waffles.utils.geom.utilities;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.Collidable;

/**
 * A {@code Transformator} defines a {@code Collidable} that can be transformed with a {@code LinearMap}.
 *
 * @author Waffles
 * @since 28 Jan 2026
 * @version 1.1
 * 
 * 
 * @see Collidable
 * @see Affine
 */
public interface Transformator extends Affine, Collidable
{
	/**
	 * A {@code Transformator.Factory} generates {@code Transformator} geometry.
	 *
	 * @author Waffles
	 * @since 28 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Affine
	 */
	public static interface Factory extends Affine.Factory
	{
		/**
		 * Constructs a {@code Transformator} from a matrix span.
		 * 
		 * @param m  a matrix span
		 * @return   a transformator
		 * 
		 * 
		 * @see Transformator
		 */
		public abstract Transformator create(Matrix m);
		
		@Override
		public default Transformator create(Matrix... set)
		{
			if(set.length == 0)
				return null;
			if(set.length == 1)
			{
				return create(set[0]);
			}
			
			return create(Matrices.concat(set));
		}
	}
	
	@Override
	public abstract Factory Factory();
}