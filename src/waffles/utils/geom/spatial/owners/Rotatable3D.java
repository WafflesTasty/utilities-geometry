package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.complex.Quaternion;
import waffles.utils.alg.lin.measure.vector.fixed.Vector3;
import waffles.utils.geom.spatial.data.spin.Spin3D;
import waffles.utils.geom.spatial.data.unary.Rotated3D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Rotatable3D} object can be rotated in a three-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 21, 2016
 * @version 1.1
 * 
 * 
 * @see Rotatable
 * @see Rotated3D
 */
public interface Rotatable3D extends Rotatable, Rotated3D
{		
	/**
	 * Rotates the {@code Rotatable3D} around a versor.
	 * 
	 * @param q  a unit quaternion
	 * 
	 * 
	 * @see Quaternion
	 */
	public default void rotateFor(Quaternion q)
	{
		if(ERROR < Floats.abs(q.W() - 1f))
		{
			rotateFor(new Spin3D(q));
		}
	}
	
	/**
	 * Rotates the {@code Rotatable3D} around a vector.
	 * 
	 * @param v  a rotation vector
	 * @param a  a rotation angle
	 * 
	 * 
	 * @see Vector3
	 */
	public default void rotateFor(Vector3 v, float a)
	{
		if(ERROR < a)
		{
			rotateFor(new Spin3D(v, a));
		}
	}
	
	/**
	 * Rotates the {@code Rotatable3D} around a vector.
	 * The vector norm serves as the rotation angle.
	 * 
	 * @param v  a rotation vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default void rotateFor(Vector3 v)
	{
		float n = v.norm();	
		if(ERROR < n)
		{
			rotateFor(v.times(1f / n), n);	
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

	
	@Override
	public default Spin3D Spin()
	{
		return (Spin3D) Rotatable.super.Spin();
	}
}