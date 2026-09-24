package waffles.utils.geom.spatial.maps.data.spin;

import waffles.utils.alg.lin.DotProduct;
import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Orthogonal;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.lin.measure.vector.complex.Quaternion;
import waffles.utils.alg.lin.measure.vector.fixed.Vector3;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.errors.DimensionError;
import waffles.utils.tools.primitives.Doubles;
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
	 * Defines an error for a {@code Spin3D}.
	 */
	public static final double ERROR = Doubles.pow(2, -8);
	
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
			Quaternion q = s.Versor();
			for(int r = 0; r < 3; r++)
			{
				for(int c = 0; c < 3; c++)
				{
					float val = value(q, r, c);
					m.set(val, r, c);
				}
			}
		}

		return m;
	}

	/**
	 * Returns a {@code Spin3D} from a euler vector.
	 * 
	 * @param e  a euler vector
	 * @return   a spin
	 * 
	 * 
	 * @see Point
	 */
	public static Spin3D from(Point e)
	{
		Spin3D s1 = new Spin3D(Vector3.X_AXIS, e.X());
		Spin3D s2 = new Spin3D(Vector3.Y_AXIS, e.Y());
		Spin3D s3 = new Spin3D(Vector3.Z_AXIS, e.Z());

		return s2.compose(s1.compose(s3));
	}
	
	
	static float value(Quaternion q, int r, int c)
	{
		float x = q.X();
		float y = q.Y();
		float z = q.Z();
		float w = q.W();


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
	

	private float ang;
	private Vector3 vec;
	private Quaternion quat;

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
		float n = v.norm();
		if(Floats.isZero(a, 1)
		|| Floats.isZero(n, 1))
		{
			quat = new Quaternion();
			vec = Vector3.Z_AXIS;
			ang = 0f;
		}
		else
		{
			ang = a;
	        vec = v.over(n);
	        
	        float s = Floats.sin(a / 2);
	        float c = Floats.cos(a / 2);

	        float x = vec.X() * s;
	        float y = vec.Y() * s;
	        float z = vec.Z() * s;
	        
	        quat = new Quaternion(x, y, z, c);
		}
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
		float n = q.norm();
		float c = q.W() / n;
		if(Floats.isZero(c - 1f, 1))
		{
			quat = new Quaternion();
			vec = Vector3.Z_AXIS;
			ang = 0f;
		}
		else
		{
			quat = q.over(n);
			vec = Vectors.create(3);
			ang = 2 * Floats.acos(q.W());
			
			float s = Floats.sin(ang / 2);
			for(int k = 0; k < 3; k++)
			{
				float v = q.get(k);
				vec.set(v / s, k);
			}
		}
	}

	/**
	 * Creates a new {@code Spin3D}.
	 */
	public Spin3D()
	{
		this(new Quaternion());
	}

	
	/**
	 * Returns a rotation angle.
	 * 
	 * @return  a rotation angle
	 */
	public float Angle()
	{
		return ang;
	}
	
	/**
	 * Returns a {@code Vector3} axis.
	 * 
	 * @return  a rotation axis
	 * 
	 * 
	 * @see Vector3
	 */
	public Vector3 Axis()
	{
		return vec;
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
		return quat;
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
		Quaternion q = Versor();
		return new Vector3
		(
			value(q, 0, 2),
			value(q, 1, 2),
			value(q, 2, 2)
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
		Quaternion q = Versor();
		return new Vector3
		(
			value(q, 0, 0),
			value(q, 1, 0),
			value(q, 2, 0)
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
		Quaternion q = Versor();
		return new Vector3
		(
			value(q, 0, 1),
			value(q, 1, 1),
			value(q, 2, 1)
		);
	}

	
	@Override
	public Point Euler()
	{
		Quaternion q = Versor();
		float a = Floats.asin(-value(q, 1, 2));
		float b = Floats.atan2(value(q, 2, 2), value(q, 0, 2));
		float c = Floats.atan2(value(q, 1, 1), value(q, 1, 0));

		return new Point(a, b, c, 1f);
	}
	
	@Override
	public Spin3D inverse()
	{
		return new Spin3D(Axis(), -Angle());
	}
	
	@Override
	public Spin3D over(Float s)
	{
		return times(1f / s);
	}
	
	@Override
	public Spin3D times(Float s)
	{
		return new Spin3D(Axis(), Angle() * s);
	}

	@Override
	public Spin3D compose(Spin s)
	{
		if(s.Dimension() == 3)
		{
			Spin3D n = (Spin3D) s;
			
			Quaternion p = n.Versor();
			Quaternion q =   Versor();
			Quaternion r = q.times(p);

			return new Spin3D(r);
		}

		throw new DimensionError(this, s);
	}

	@Override
	public Spin3D hadamard(Point p)
	{
		return (Spin3D) Spin.super.hadamard(p);
	}
	
	@Override
	public float dot(DotProduct a)
	{
		if(a instanceof Spin3D)
		{
			Spin3D s = (Spin3D) a;
			
			float a1 =   Angle();
			float a2 = s.Angle();
			
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
		return Floats.abs(Angle());
	}
}