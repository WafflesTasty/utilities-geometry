package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.complex.Quaternion;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.spatial.data.spin.Spin3D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Rotatable3D} object can be rotated in a three-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 21, 2016
 * @version 1.0
 * 
 * 
 * @see Rotatable
 */
public interface Rotatable3D extends Rotatable
{	
	@Override
	public default Spin3D Spin()
	{
		return (Spin3D) Rotatable.super.Spin();
	}
	
	
	/**
	 * Rotates the {@code Rotatable3D} around a given versor.
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
	 * Rotates the {@code Rotatable3D} around a given vector.
	 * 
	 * @param v  a rotation vector
	 * @param a  a rotation angle
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
	 * Rotates the {@code Rotatable3D} around a given vector.
	 * The vector norm serves as the rotation angle.
	 * 
	 * @param v  a rotation vector
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
	 * Pitches around the right {@code Vector}.
	 * 
	 * @param a  a rotation angle
	 */
	public default void pitchFor(float a)
	{
		rotateFor(Right(), a);
	}
	
	/**
	 * Rolls around the forward {@code Vector}.
	 * 
	 * @param a  a rotation angle
	 */
	public default void rollFor(float a)
	{
		rotateFor(Forward(), a);
	}
	
	/**
	 * Yaws around the up {@code Vector}.
	 * 
	 * @param a  a rotation angle
	 */
	public default void yawFor(float a)
	{
		rotateFor(Up(), a);
	}

	
	/**
	 * Returns a forward {@code Vector3}.
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
	 * Returns a right {@code Vector3}.
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
	 * Returns a up {@code Vector3}.
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