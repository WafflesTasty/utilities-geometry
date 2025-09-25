package waffles.utils.geom.spatial.data.spin;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Orthogonal;
import waffles.utils.alg.lin.measure.vector.fixed.Vector2;
import waffles.utils.geom.utilities.errors.DimensionError;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Spin2D} object defines a two-dimensional rotation.
 * In two dimensions, a spin is completely defined by an angle.
 *
 * @author Waffles
 * @since Jan 22, 2020
 * @version 1.1
 * 
 *
 * @see Spin
 */
public class Spin2D implements Spin
{
	/**
	 * Creates a {@code Matrix} from a {@code Spin2D}.
	 * 
	 * @param s  a spin object
	 * @param d  a spin dimension
	 * @return   a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix Matrix(Spin2D s, int d)
	{
		Matrix m = Matrices.identity(d);
		m.setOperator(Orthogonal.Type());
		
		if(d > 1)
		{
			float sin = Floats.sin(s.Angle());
			float cos = Floats.cos(s.Angle());

			m.set( cos, 0, 0);
			m.set( sin, 1, 0);
			m.set(-sin, 0, 1);
			m.set( cos, 1, 1);
		}
		
		return m;
	}
	
	
	private float ang;
	
	/**
	 * Creates a new {@code Spin2D}.
	 * 
	 * @param a  a spin ang
	 */
	public Spin2D(float a)
	{
		ang = a;
	}
	
	/**
	 * Creates a new {@code Spin2D}.
	 */
	public Spin2D()
	{
		this(0f);
	}
	
		
	/**
	 * Returns a forward {@code Vector2}.
	 * 
	 * @return  a forward vector
	 * 
	 * 
	 * @see Vector2
	 */
	public Vector2 Forward()
	{
		return new Vector2
		(
			-Floats.sin(Angle()),
			 Floats.cos(Angle())
		);
	}
	
	/**
	 * Returns a right {@code Vector2}.
	 * 
	 * @return  a right vector
	 * 
	 * 
	 * @see Vector2
	 */
	public Vector2 Right()
	{
		return new Vector2
		(
			Floats.cos(Angle()),
			Floats.sin(Angle())
		);
	}

	/**
	 * Returns a {@code Spin} angle.
	 * 
	 * @return  a spin angle
	 */
	public float Angle()
	{
		return ang;
	}
	
	
	@Override
	public Spin2D inverse()
	{
		return new Spin2D(-Angle());
	}
			
	@Override
	public Spin2D times(Float v)
	{
		return new Spin2D(Angle() * v);
	}
	
	@Override
	public Spin2D compose(Spin s)
	{
		if(s instanceof Spin2D)
		{
			float a = Angle() + ((Spin2D) s).Angle();
			return new Spin2D(a);
		}
		
		throw new DimensionError(this, s);
	}
	
	@Override
	public Vector2 Basis(int k)
	{
		if(k == 0)
			return Right();
		if(k == 1)
			return Forward();
		
		return null;
	}

	@Override
	public int Dimension()
	{
		return 2;
	}
	
	@Override
	public float normSqr()
	{
		return Angle() * Angle();
	}
	
	@Override
	public float norm()
	{
		return Floats.abs(Angle());
	}	
}