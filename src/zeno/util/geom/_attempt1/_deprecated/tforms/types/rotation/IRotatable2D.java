package zeno.util.geom._attempt1._deprecated.tforms.types.rotation;

import zeno.util.geom.ITransformable;
import zeno.util.geom._attempt1._deprecated.Ortho2x2;
import zeno.util.geom._attempt1._deprecated.tforms.types.ITransformation2D;

/**
 * The {@code IRotatable2D} interface defines an object
 * capable of being rotated in 2D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
 * 
 * @see ITransformable
 */
public interface IRotatable2D extends ITransformable
{
	@Override
	public abstract ITransformation2D Transform();
	
	public default Ortho2x2 Basis()
	{
		return Transform().Basis();
	}
	
	
	/**
	 * Rotates the {@code IRotatable2D} for a specified angle.
	 * 
	 * @param rad  an angle to rotate for
	 */
	public default void rotateFor(float rad)
	{
		if(rad != 0)
		{
			Transform().Basis().rotateFor(rad);
		}
	}
	
	/**
	 * Rotates the {@code IRotatable2D} to a new angle.
	 * 
	 * @param rad  an angle to rotate to
	 */
	public default void rotateTo(float rad)
	{
		Transform().Basis().rotateTo(rad);
	}
}