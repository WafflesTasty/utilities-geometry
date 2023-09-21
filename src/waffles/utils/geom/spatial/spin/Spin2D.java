package waffles.utils.geom.spatial.spin;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Orthogonal;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.tools.primitives.Floats;

/**
 * The {@code Spin2D} class defines spin in the 2D plane.
 * It is completely determined by its angle scalar.
 *
 * @author Zeno
 * @since Jan 22, 2020
 * @version 1.0
 * 
 *
 * @see Spin
 */
public class Spin2D implements Spin
{
	/**
	 * Creates a {@code Matrix} from a {@code Spin2D}.
	 * 
	 * @param s    a spin object
	 * @param dim  a matrix dimension
	 * @return  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix Matrix(Spin2D s, int dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Orthogonal.Type());
		
		if(dim > 1)
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
	
	
	private float angle;
	
	/**
	 * Creates a new {@code Spin2D}.
	 * 
	 * @param ang  a spin angle
	 */
	public Spin2D(float ang)
	{
		angle = ang;
	}
	
	/**
	 * Creates a new {@code Spin2D}.
	 */
	public Spin2D()
	{
		this(0f);
	}
	
	
	/**
	 * Scales the {@code Spin2D} with a value.
	 * 
	 * @param val  a scalar value
	 * @return  a scaled spin
	 */
	public Spin2D times(float val)
	{
		return new Spin2D(angle * val);
	}
	
	/**
	 * Returns a forward {@code Spin} vector.
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
			-Floats.sin(angle),
			 Floats.cos(angle)
		);
	}
	
	/**
	 * Returns a right {@code Spin} vector.
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
			Floats.cos(angle),
			Floats.sin(angle)
		);
	}

	/**
	 * Returns a {@code Spin} angle.
	 * 
	 * @return  a spin angle
	 */
	public float Angle()
	{
		return angle;
	}
	
	
	@Override
	public Spin2D compose(Spin s)
	{
		if(s instanceof Spin2D)
		{
			return new Spin2D(angle + ((Spin2D) s).Angle());
		}
		
		throw new Spin.Error(this, s);
	}
	
	@Override
	public Vector2 Basis(int i)
	{
		if(i == 0)
			return Right();
		if(i == 1)
			return Forward();
		
		return null;
	}
}