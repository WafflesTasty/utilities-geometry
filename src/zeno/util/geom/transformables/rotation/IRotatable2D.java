package zeno.util.geom.transformables.rotation;

import zeno.util.algebra.linear.matrix.fixed.Matrix2x2;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformations.ITransformation2D;

/**
 * The {@code IRotatable2D} interface defines an object
 * capable of being rotated in 2D space.
 *
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see ITransformable
 */
public interface IRotatable2D extends ITransformable
{
	@Override
	public abstract ITransformation2D Transform();

	
	/**
	 * Rotates the {@code IRotatable2D} for a specified angle.
	 * 
	 * @param rad  an angle to rotate for
	 */
	public default void rotateFor(float rad)
	{
		if(rad != 0)
		{
			Matrix2x2 b = Transform().Basis();
			b = Matrix2x2.rotate2D(rad).times(b);
			Transform().setBasis(b);
		}
	}
	
	/**
	 * Rotates the {@code IRotatable2D} to a new angle.
	 * 
	 * @param rad  an angle to rotate to
	 */
	public default void rotateTo(float rad)
	{
		Transform().setBasis(Matrix2x2.rotate2D(rad));
	}
}