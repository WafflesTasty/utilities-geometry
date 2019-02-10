package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector3;
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
	 * Rotates the {@code IRotatable3D} to a new angle.
	 * 
	 * @param rwd  a new right vector
	 * @param uwd  a new up vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default void rotateTo(Vector3 rwd, Vector3 uwd)
	{
		rotateTo(Matrix3x3.rotate3D(rwd, uwd));
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
			rotateFor(Matrix3x3.rotate3D(v, a));
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
		if(Floats.isZero(a, 1)) return;
		
		float cos = Floats.cos(a);
		float sin = Floats.sin(a);
				
		Vector3 fwd0 = Forward();
		Vector3 uwd0 = Up();
		
		
		Vector3 fwd1 = fwd0.times(cos).plus(uwd0.times(-sin));
		Vector3 uwd1 = uwd0.times(cos).plus(fwd0.times(sin));
		
		fwd1 = fwd1.normalize();
		uwd1 = uwd1.normalize();
		
		
		Matrix3x3 m = Basis().copy();
		for(int r = 0; r < 3; r++)
		{
			m.set(uwd1.get(r), r, 1);
			m.set(fwd1.get(r), r, 2);
		}
		
		rotateTo(m);
	}
	
	/**
	 * Rolls the {@code IRotatable3D} around its forward vector.
	 * 
	 * @param a  an angle to roll with
	 */
	public default void rollFor(float a)
	{
		if(Floats.isZero(a, 1)) return;
		
		float cos = Floats.cos(a);
		float sin = Floats.sin(a);
				
		Vector3 rwd0 = Right();
		Vector3 uwd0 = Up();

		
		Vector3 rwd1 = rwd0.times(cos).plus(uwd0.times(-sin));
		Vector3 uwd1 = uwd0.times(cos).plus(rwd0.times(sin));
		
		rwd1 = rwd1.normalize();
		uwd1 = uwd1.normalize();
		
		
		Matrix3x3 m = Basis().copy();
		for(int r = 0; r < 3; r++)
		{
			m.set(rwd1.get(r), r, 0);
			m.set(uwd1.get(r), r, 1);
		}
		
		rotateTo(m);
	}
	
	/**
	 * Yaws the {@code IRotatable3D} around its up vector.
	 * 
	 * @param a  an angle to yaw with
	 */
	public default void yawFor(float a)
	{
		if(Floats.isZero(a, 1)) return;
		
		float cos = Floats.cos(a);
		float sin = Floats.sin(a);
				
		Vector3 fwd0 = Forward();
		Vector3 rwd0 = Right();
		
		
		Vector3 rwd1 = rwd0.times(cos).plus(fwd0.times(-sin));
		Vector3 fwd1 = fwd0.times(cos).plus(rwd0.times(sin));
		
		rwd1 = rwd1.normalize();
		fwd1 = fwd1.normalize();
		
		
		Matrix3x3 m = Basis().copy();
		for(int r = 0; r < 3; r++)
		{
			m.set(rwd1.get(r), r, 0);
			m.set(fwd1.get(r), r, 2);
		}
		
		rotateTo(m);
	}

	
	@Override
	public abstract Matrix3x3 Basis();

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
		return Basis().Column(1);
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
		return Basis().Column(0);
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
		return Basis().Column(2);
	}
}