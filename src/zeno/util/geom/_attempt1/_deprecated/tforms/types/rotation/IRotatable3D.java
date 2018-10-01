package zeno.util.geom._attempt1._deprecated.tforms.types.rotation;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformable;
import zeno.util.geom._attempt1._deprecated.Ortho3x3;
import zeno.util.geom._attempt1._deprecated.tforms.types.ITransformation3D;
import zeno.util.tools.Floats;

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
	
	public default Ortho3x3 Basis()
	{
		return Transform().Basis();
	}
	
		
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
	 * Rotates the {@code IRotatable3D} around an arbitrary vector.
	 * <br> The vector's length serves as the rotation angle.
	 * 
	 * @param v  a vector to rotate around
	 * @see Vector3
	 */
	public default void rotateFor(Vector3 v)
	{
		float norm = v.norm();	
		if(!Floats.isZero(norm, 3))
		{
			rotateFor(v, norm);	
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
}