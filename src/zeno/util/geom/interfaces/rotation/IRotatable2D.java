package zeno.util.geom.interfaces.rotation;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.ITransformable;
import zeno.util.geom.interfaces.ITransformation2D;

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
	
	
	/**
	 * Rotates the {@code IRotatable2D} for a specified angle.
	 * 
	 * @param rad  an angle to rotate for
	 */
	public default void rotateFor(float rad)
	{
		if(rad != 0)
		{
			Transform().rotateFor(rad);
		}
	}
	
	/**
	 * Rotates the {@code IRotatable2D} to a new angle.
	 * 
	 * @param rad  an angle to rotate to
	 */
	public default void rotateTo(float rad)
	{
		Transform().rotateTo(rad);
	}
		
	
	/**
	 * Returns the right vector of the {@code IRotatable2D}.
	 * 
	 * @return  the rotatable's right vector
	 * @see Vector2
	 */
	public default Vector2 getRight()
	{
		return Transform().getRight();
	}
	
	/**
	 * Returns the up vector of the {@code IRotatable2D}.
	 * 
	 * @return  the rotatable's up vector
	 * @see Vector2
	 */
	public default Vector2 getUp()
	{
		return Transform().getUp();
	}
}