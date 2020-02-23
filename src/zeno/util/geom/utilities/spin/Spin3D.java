package zeno.util.geom.utilities.spin;

import zeno.util.algebra.imaginary.Quaternion;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.types.orthogonal.Orthogonal;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code Spin3D} class defines spin in the 3D space.
 * </br> It is completely determined by its versor.
 *
 * @author Zeno
 * @since Jan 22, 2020
 * @version 1.0
 * 
 *
 * @see Spin
 */
public class Spin3D implements Spin
{
	private Quaternion versor;
	
	/**
	 * Creates a new {@code Spin3D}.
	 * 
	 * @param v  a rotation vector
	 * @param a  a rotation angle
	 * 
	 * 
	 * @see Vector3
	 */
	public Spin3D(Vector3 v, float a)
	{
		this(new Quaternion(v, a));
	}
	
	/**
	 * Creates a new {@code Spin3D}.
	 * 
	 * @param v  a rotation versor
	 * 
	 * 
	 * @see Quaternion
	 */
	public Spin3D(Quaternion v)
	{
		versor = v.normalize();
	}
	
	/**
	 * Creates a new {@code Spin3D}.
	 */
	public Spin3D()
	{
		this(new Quaternion());
	}
	
	
	/**
	 * Returns the {@code Spin} versor quaternion.
	 * 
	 * @return  a spin versor
	 * 
	 * 
	 * @see Quaternion
	 */
	public Quaternion Versor()
	{
		return versor;
	}
	
	/**
	 * Returns the forward {@code Spin} vector.
	 * 
	 * @return  a forward vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Vector3 Forward()
	{
		float x = versor.X();
		float y = versor.Y();
		float z = versor.Z();
		float w = versor.W();
		
		return new Vector3
		(
			0 + 2 * (x * z + y * w),
			0 + 2 * (y * z - x * w),
			1 - 2 * (x * x + y * y)
		);
	}
	
	/**
	 * Returns the right {@code Spin} vector.
	 * 
	 * @return  a right vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Vector3 Right()
	{
		float x = versor.X();
		float y = versor.Y();
		float z = versor.Z();
		float w = versor.W();
		
		return new Vector3
		(
			1 - 2 * (y * y + z * z),
			0 + 2 * (x * y + z * w),
			0 + 2 * (x * z - y * w)
		);
	}
	
	/**
	 * Returns the up {@code Spin} vector.
	 * 
	 * @return  an up vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Vector3 Up()
	{
		float x = versor.X();
		float y = versor.Y();
		float z = versor.Z();
		float w = versor.W();
		
		return new Vector3
		(
			0 + 2 * (x * y - z * w),
			1 - 2 * (x * x + z * z),
			0 + 2 * (x * w + y * z)
		);
	}
	
			
	@Override
	public Spin3D compose(Spin s)
	{
		if(s instanceof Spin3D)
		{
			return new Spin3D(versor.times(((Spin3D) s).Versor()));
		}
		
		throw new Geometries.SpinError(this, s);
	}
	
	@Override
	public Matrix generate(int dim)
	{
		Matrix mat = Matrices.identity(dim + 1);
		mat.setOperator(Orthogonal.Type());
		
		float x = versor.X();
		float y = versor.Y();
		float z = versor.Z();
		float w = versor.W();
		
		mat.set(1 - 2 * (y * y + z * z), 0, 0);
		mat.set(0 + 2 * (x * y - z * w), 0, 1);
		mat.set(0 + 2 * (x * z + y * w), 0, 2);
		
		mat.set(0 + 2 * (x * y + z * w), 1, 0);
		mat.set(1 - 2 * (x * x + z * z), 1, 1);
		mat.set(0 + 2 * (y * z - x * w), 1, 2);
		
		mat.set(0 + 2 * (x * z - y * w), 2, 0);
		mat.set(0 + 2 * (x * w + y * z), 2, 1);
		mat.set(1 - 2 * (x * x + y * y), 2, 2);
		
		return mat;
	}

	@Override
	public Vector3 vector(int i)
	{
		if(i == 0)
			return Right();
		if(i == 1)
			return Forward();
		if(i == 2)
			return Up();
		
		return null;
	}
}