package zeno.util.geom.utilities.spin;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.types.orthogonal.Orthogonal;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

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
	 * Returns the forward {@code Spin} vector.
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
	 * Returns the right {@code Spin} vector.
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
	 * Returns the {@code Spin} angle.
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
		
		throw new Geometries.SpinError(this, s);
	}
	
	@Override
	public Matrix generate(int dim)
	{
		Matrix mat = Matrices.identity(dim + 1);
		mat.setOperator(Orthogonal.Type());
		
		float sin = Floats.sin(angle);
		float cos = Floats.cos(angle);
		
		mat.set( cos, 0, 0);
		mat.set( sin, 1, 0);
		mat.set(-sin, 0, 1);
		mat.set( cos, 1, 1);
		
		return mat;
	}

	@Override
	public Vector2 vector(int i)
	{
		if(i == 0)
			return Right();
		if(i == 1)
			return Forward();
		
		return null;
	}
}