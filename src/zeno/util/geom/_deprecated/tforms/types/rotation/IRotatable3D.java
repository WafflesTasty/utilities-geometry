package zeno.util.geom.tforms.types.rotation;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom._deprecated.ITransformable;
import zeno.util.geom.tforms.types.ITransformation3D;

/**
 * The {@code IRotatable3D} interface defines an object
 * capable of rotating in 3D space.
 *
 * @since Apr 21, 2016
 * @author Zeno
 * 
 * @see ITransformable
 */
public interface IRotatable3D extends ITransformable
{
	@Override
	public abstract ITransformation3D Transform();
	
		
	/**
	 * Rotates the {@code IRotatable3D} to a new angle.
	 * 
	 * @param rwd  a new right vector
	 * @param uwd  a new up vector
	 * @see Vector3
	 */
	public default void rotateTo(Vector3 rwd, Vector3 uwd)
	{
		Transform().rotateTo(rwd, uwd);
	}

	/**
	 * Rotates the {@code IRotatable3D} around an arbitrary vector.
	 * 
	 * @param v  a vector to rotate around
	 * @param rad  an angle to rotate for
	 * @see Vector3
	 */
	public default void rotateFor(Vector3 v, float rad)
	{
		if(rad != 0)
		{
			Transform().rotateFor(v, rad);
		}
	}
	
	
	/**
	 * Pitches the {@code IRotatable3D} around its right vector.
	 * 
	 * @param rad  an angle to pitch with
	 */
	public default void pitchFor(float rad)
	{
		if(rad != 0)
		{
			Transform().pitchFor(rad);
		}
	}
	
	/**
	 * Rolls the {@code IRotatable3D} around its forward vector.
	 * 
	 * @param rad  an angle to roll with
	 */
	public default void rollFor(float rad)
	{
		if(rad != 0)
		{
			Transform().rollFor(rad);
		}
	}
	
	/**
	 * Yaws the {@code IRotatable3D} around its up vector.
	 * 
	 * @param rad  an angle to yaw with
	 */
	public default void yawFor(float rad)
	{
		if(rad != 0)
		{
			Transform().yawFor(rad);
		}
	}

	
	/**
	 * Returns the up vector of the {@code IRotatable3D}.
	 * 
	 * @return  the transformable's up vector
	 * @see Vector3
	 */
	public default Vector3 getForward()
	{
		return Transform().getForward();
	}
	
	/**
	 * Returns the right vector of the {@code IRotatable3D}.
	 * 
	 * @return  the transformable's right vector
	 * @see Vector3
	 */
	public default Vector3 getRight()
	{
		return Transform().getRight();
	}
	
	/**
	 * Returns the up vector of the {@code IRotatable3D}.
	 * 
	 * @return  the transformable's up vector
	 * @see Vector3
	 */
	public default Vector3 getUp()
	{
		return Transform().getUp();
	}
}