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
	 * Multiplies the {@code Spin3D} with a value.
	 * 
	 * @param val  a value to multiply
	 * @return  a scaled spin
	 */
	public Spin3D times(float val)
	{
		return new Spin3D(versor.Axis(), versor.Angle() * val);
	}
	
	/**
	 * Returns the {@code Spin3D} versor quaternion.
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
	 * Returns the forward {@code Spin3D} vector.
	 * 
	 * @return  a forward vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Vector3 Forward()
	{
		return new Vector3
		(
			get(0, 2),
			get(1, 2),
			get(2, 2)
		);
	}
	
	/**
	 * Returns the right {@code Spin3D} vector.
	 * 
	 * @return  a right vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Vector3 Right()
	{
		return new Vector3
		(
			get(0, 0),
			get(1, 0),
			get(2, 0)
		);
	}
	
	/**
	 * Returns the up {@code Spin3D} vector.
	 * 
	 * @return  an up vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Vector3 Up()
	{
		return new Vector3
		(
			get(0, 1),
			get(1, 1),
			get(2, 1)
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
		
		for(int r = 0; r < 3; r++)
		{
			for(int c = 0; c < 3; c++)
			{
				mat.set(get(r, c), r, c);
			}
		}
		
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

	Float get(int r, int c)
	{
		float x = versor.X();
		float y = versor.Y();
		float z = versor.Z();
		float w = versor.W();
		
		
		// Right vector.
		if(c == 0)
		{
			if(r == 0)
				return 1 - 2 * (y * y + z * z);
			if(r == 1)
				return 0 + 2 * (x * y + z * w);
			if(r == 2)
				return 0 + 2 * (x * z - y * w);
		}
		
		// Up vector.
		if(c == 1)
		{
			if(r == 0)
				return 0 + 2 * (x * y - z * w);
			if(r == 1)
				return 1 - 2 * (x * x + z * z);
			if(r == 2)
				return 0 + 2 * (x * w + y * z);
		}
		
		// Forward vector.
		if(c == 2)
		{
			if(r == 0)
				return 0 + 2 * (x * z + y * w);
			if(r == 1)
				return 0 + 2 * (y * z - x * w);
			if(r == 2)
				return 1 - 2 * (x * x + y * y);
		}
		
		return null;
	}
}