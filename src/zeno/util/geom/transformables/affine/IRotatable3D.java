package zeno.util.geom.transformables.affine;

import zeno.util.algebra.imaginary.Quaternion;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.utilities.spin.Spin3D;
import zeno.util.tools.Floats;

/**
 * The {@code IRotatable3D} interface defines an object
 * capable of rotating in a 3D affine space.
 * 
 * @author Zeno
 * @since Apr 21, 2016
 * @version 1.0
 * 
 * 
 * @see IRotatable
 */
public interface IRotatable3D extends IRotatable
{	
	/**
	 * Rotates the {@code IRotatable3D} around an arbitrary versor.
	 * 
	 * @param q  a unit quaternion
	 * 
	 * 
	 * @see Quaternion
	 */
	public default void rotateFor(Quaternion q)
	{
		if(!Floats.isEqual(q.W(), 1f, 1))
		{
			rotateFor(new Spin3D(q));
		}
	}
	
	/**
	 * Rotates the {@code IRotatable3D} around an arbitrary vector.
	 * 
	 * @param v  a vector to rotate around
	 * @param a  an angle to rotate for
	 * 
	 * 
	 * @see Vector3
	 */
	public default void rotateFor(Vector3 v, float a)
	{
		if(!Floats.isZero(a, 1))
		{
			rotateFor(new Spin3D(v, a));
		}
	}
	
	/**
	 * Rotates the {@code IRotatable3D} around an arbitrary vector.
	 * <br> The vector's length serves as the rotation angle.
	 * 
	 * @param v  a vector to rotate around
	 * 
	 * 
	 * @see Vector3
	 */
	public default void rotateFor(Vector3 v)
	{
		float norm = v.norm();	
		if(!Floats.isZero(norm, 3))
		{
			rotateFor(v.times(1f / norm), norm);	
		}
	}
	
	
	/**
	 * Pitches the {@code IRotatable3D} around its right vector.
	 * 
	 * @param a  an angle to pitch with
	 */
	public default void pitchFor(float a)
	{
		rotateFor(Right(), a);
	}
	
	/**
	 * Rolls the {@code IRotatable3D} around its forward vector.
	 * 
	 * @param a  an angle to roll with
	 */
	public default void rollFor(float a)
	{
		rotateFor(Forward(), a);
	}
	
	/**
	 * Yaws the {@code IRotatable3D} around its up vector.
	 * 
	 * @param a  an angle to yaw with
	 */
	public default void yawFor(float a)
	{
		rotateFor(Up(), a);
	}

	
	@Override
	public abstract Spin3D Spin();

	/**
	 * Returns the forward vector of the {@code IRotatable3D}.
	 * 
	 * @return  a forward vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Forward()
	{
		return Spin().Forward();
	}
	
	/**
	 * Returns the right vector of the {@code IRotatable3D}.
	 * 
	 * @return  a right vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Right()
	{
		return Spin().Right();
	}
	
	/**
	 * Returns the up vector of the {@code IRotatable3D}.
	 * 
	 * @return  an up vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Up()
	{
		return Spin().Up();
	}
}