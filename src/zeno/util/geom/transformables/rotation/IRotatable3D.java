package zeno.util.geom.transformables.rotation;

import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformations.ITransformation3D;
import zeno.util.tools.Floats;

/**
 * The {@code IRotatable3D} interface defines an object
 * capable of rotating in 3D space.
 * 
 * @author Zeno
 * @since Apr 21, 2016
 * @version 1.0
 * 
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
	 * 
	 * 
	 * @see Vector3
	 */
	public default void rotateTo(Vector3 rwd, Vector3 uwd)
	{
		Transform().setBasis(Matrix3x3.rotate3D(rwd, uwd));
	}

	/**
	 * Rotates the {@code IRotatable3D} around an arbitrary vector.
	 * 
	 * @param v  a vector to rotate around
	 * @param rad  an angle to rotate for
	 * 
	 * 
	 * @see Vector3
	 */
	public default void rotateFor(Vector3 v, float rad)
	{
		if(rad != 0f)
		{
			Matrix3x3 b = Transform().Basis();
			b = Matrix3x3.rotate3D(v, rad).times(b);
			Transform().setBasis(b);
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
	 * @param rad  an angle to pitch with
	 */
	public default void pitchFor(float rad)
	{
		if(rad == 0f) return;
		
		float cos = Floats.cos(rad);
		float sin = Floats.sin(rad);
				
		Vector3 fwd0 = Transform().Forward();
		Vector3 uwd0 = Transform().Up();
				
		Vector3 fwd1 = fwd0.times(cos).plus(uwd0.times(-sin));
		Vector3 uwd1 = uwd0.times(cos).plus(fwd0.times(sin));
		
		fwd1 = fwd1.normalize();
		uwd1 = uwd1.normalize();
		
		
		Matrix3x3 m = Transform().Basis();
		for(int r = 0; r < 3; r++)
		{
			m.set(uwd1.get(r), r, 1);
			m.set(fwd1.get(r), r, 2);
		}
	}
	
	/**
	 * Rolls the {@code IRotatable3D} around its forward vector.
	 * 
	 * @param rad  an angle to roll with
	 */
	public default void rollFor(float rad)
	{
		if(rad == 0f) return;
		
		float cos = Floats.cos(rad);
		float sin = Floats.sin(rad);
				
		Vector3 rwd0 = Transform().Right();
		Vector3 uwd0 = Transform().Up();

		Vector3 rwd1 = rwd0.times(cos).plus(uwd0.times(-sin));
		Vector3 uwd1 = uwd0.times(cos).plus(rwd0.times(sin));
		
		rwd1 = rwd1.normalize();
		uwd1 = uwd1.normalize();
		
		
		Matrix3x3 m = Transform().Basis();
		for(int r = 0; r < 3; r++)
		{
			m.set(rwd1.get(r), r, 0);
			m.set(uwd1.get(r), r, 1);
		}
	}
	
	/**
	 * Yaws the {@code IRotatable3D} around its up vector.
	 * 
	 * @param rad  an angle to yaw with
	 */
	public default void yawFor(float rad)
	{
		if(rad == 0) return;
		
		float cos = Floats.cos(rad);
		float sin = Floats.sin(rad);
				
		Vector3 rwd0 = Transform().Right();
		Vector3 fwd0 = Transform().Forward();
		
		Vector3 rwd1 = rwd0.times(cos).plus(fwd0.times(-sin));
		Vector3 fwd1 = fwd0.times(cos).plus(rwd0.times(sin));
		
		rwd1 = rwd1.normalize();
		fwd1 = fwd1.normalize();
		
		
		Matrix3x3 m = Transform().Basis();
		for(int r = 0; r < 3; r++)
		{
			m.set(rwd1.get(r), r, 0);
			m.set(fwd1.get(r), r, 2);
		}
	}
}