package waffles.utils.geom.spatial.data.spin;

import waffles.utils.algebra.elements.complex.Quaternion;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Orthogonal;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;

/**
 * A {@code Spin3D} defines a three-dimensional rotation spin.
 * It is completely determined by a versor quaternion.
 *
 * @author Waffles
 * @since Jan 22, 2020
 * @version 1.1
 * 
 *
 * @see Spin
 */
public class Spin3D implements Spin
{
	/**
	 * Creates a {@code Matrix} from a {@code Spin3D}.
	 * 
	 * @param s    a spin object
	 * @param dim  a matrix dimension
	 * @return  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix Matrix(Spin3D s, int dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Orthogonal.Type());
		
		if(dim > 2)
		{
			Quaternion v = s.Versor();
			for(int r = 0; r < 3; r++)
			{
				for(int c = 0; c < 3; c++)
				{
					float val = get(v, r, c);
					m.set(val, r, c);
				}
			}
		}
		
		return m;
	}
	
	static Float get(Quaternion v, int r, int c)
	{
		float x = v.X();
		float y = v.Y();
		float z = v.Z();
		float w = v.W();
		
		
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
	 * Scales the {@code Spin3D} with a value.
	 * 
	 * @param val  a scalar value
	 * @return  a scaled spin
	 */
	public Spin3D times(float val)
	{
		return new Spin3D(versor.Axis(), versor.Angle() * val);
	}
	
	/**
	 * Returns a {@code Spin3D} versor quaternion.
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
			get(versor, 0, 2),
			get(versor, 1, 2),
			get(versor, 2, 2)
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
			get(versor, 0, 0),
			get(versor, 1, 0),
			get(versor, 2, 0)
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
			get(versor, 0, 1),
			get(versor, 1, 1),
			get(versor, 2, 1)
		);
	}
	
			
	@Override
	public Spin3D compose(Spin s)
	{
		if(s instanceof Spin3D)
		{
			return new Spin3D(versor.times(((Spin3D) s).Versor()));
		}
		
		throw new Spin.Error(this, s);
	}
	
	@Override
	public Vector3 Basis(int i)
	{
		if(i == 0)
			return Right();
		if(i == 1)
			return Forward();
		if(i == 2)
			return Up();
		
		return null;
	}

	@Override
	public Spin3D invert()
	{
		float a = -versor.Angle();
		Vector3 v = versor.Axis();
		return new Spin3D(v, a);
		
//		float x = -versor.X();
//		float y = -versor.Y();
//		float z = -versor.Z();
//		float w = +versor.W();
//		System.out.println(x + ":" + y + ":" + z + ":" + w);
//		Quaternion q = new Quaternion(x, y, z, w);
//		
//		return new Spin3D(q);
	}
}