package waffles.utils.geom.spatial.maps.data.spin;

import waffles.utils.alg.lin.DotProduct;
import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Orthogonal;
import waffles.utils.alg.lin.measure.vector.complex.Quaternion;
import waffles.utils.alg.lin.measure.vector.fixed.Vector3;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.errors.DimensionError;
import waffles.utils.tools.patterns.basic.errors.NotImplementedError;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Spin3D} object defines a three-dimensional rotation.
 * In three dimensions, a spin is completely defined by a versor.
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
	 * @param s  a spin object
	 * @param d  a spin dimension
	 * @return   a rotation matrix
	 *
	 *
	 * @see Matrix
	 */
	public static Matrix Matrix(Spin3D s, int d)
	{
		Matrix m = Matrices.identity(d);
		m.setOperator(Orthogonal.Type());

		if(d > 2)
		{
			for(int r = 0; r < 3; r++)
			{
				for(int c = 0; c < 3; c++)
				{
					float val = value(s, r, c);
					m.set(val, r, c);
				}
			}
		}

		return m;
	}

	static float value(Spin3D s, int r, int c)
	{
		float x = s.Versor().X();
		float y = s.Versor().Y();
		float z = s.Versor().Z();
		float w = s.Versor().W();


		// Right vector.
		if(c == 0)
		{
			if(r == 0)
			{
				return 1 - 2 * (y * y + z * z);
			}
			if(r == 1)
			{
				return 0 + 2 * (x * y + z * w);
			}
			if(r == 2)
			{
				return 0 + 2 * (x * z - y * w);
			}
		}

		// Up vector.
		if(c == 1)
		{
			if(r == 0)
			{
				return 0 + 2 * (x * y - z * w);
			}
			if(r == 1)
			{
				return 1 - 2 * (x * x + z * z);
			}
			if(r == 2)
			{
				return 0 + 2 * (x * w + y * z);
			}
		}

		// Forward vector.
		if(c == 2)
		{
			if(r == 0)
			{
				return 0 + 2 * (x * z + y * w);
			}
			if(r == 1)
			{
				return 0 + 2 * (y * z - x * w);
			}
			if(r == 2)
			{
				return 1 - 2 * (x * x + y * y);
			}
		}

		return 0f;
	}
	

	private Quaternion v;

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
	 * @param q  a quaternion
	 *
	 *
	 * @see Quaternion
	 */
	public Spin3D(Quaternion q)
	{
		v = q.normalize();
	}

	/**
	 * Creates a new {@code Spin3D}.
	 */
	public Spin3D()
	{
		this(new Quaternion());
	}


	/**
	 * Returns a {@code Quaternion} versor.
	 *
	 * @return  a versor
	 *
	 *
	 * @see Quaternion
	 */
	public Quaternion Versor()
	{
		return v;
	}

	/**
	 * Returns a forward {@code Vector3}.
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
			value(this, 0, 2),
			value(this, 1, 2),
			value(this, 2, 2)
		);
	}

	/**
	 * Returns a right {@code Vector3}.
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
			value(this, 0, 0),
			value(this, 1, 0),
			value(this, 2, 0)
		);
	}

	/**
	 * Returns an up {@code Vector3}.
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
			value(this, 0, 1),
			value(this, 1, 1),
			value(this, 2, 1)
		);
	}

	
	@Override
	public Spin3D inverse()
	{
		float a = -Versor().Angle();
		Vector3 v = Versor().Axis();
		return new Spin3D(v, a);
	}

	@Override
	public Spin3D over(Float s)
	{
		return (Spin3D) Spin.super.over(s);
	}
	
	@Override
	public Spin3D times(Float s)
	{
		return new Spin3D(v.Axis(), v.Angle() * s);
	}

	@Override
	public Spin3D compose(Spin s)
	{
		if(s instanceof Spin3D)
		{
			Quaternion q = Versor();
			q = q.times(((Spin3D) s).Versor());
			return new Spin3D(q);
		}

		throw new DimensionError(this, s);
	}

	@Override
	public Spin3D hadamard(Point p)
	{
		throw new NotImplementedError();
	}
	
	@Override
	public float dot(DotProduct a)
	{
		if(a instanceof Spin3D)
		{
			Spin3D s = (Spin3D) a;
			
			float a1 =   Versor().Angle();
			float a2 = s.Versor().Angle();
			
			return a1 - a2;
		}
		
		return Floats.NaN;
	}
	
	@Override
	public Vector3 Basis(int i)
	{
		if(i == 0)
		{
			return Right();
		}
		if(i == 1)
		{
			return Forward();
		}
		if(i == 2)
		{
			return Up();
		}

		return null;
	}

	@Override
	public int Dimension()
	{
		return 3;
	}

	@Override
	public float norm()
	{
		return Floats.abs(Versor().Angle());
	}
}