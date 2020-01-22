package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.ITransformation;
import zeno.util.geom.utilities.spin.Spin;
import zeno.util.geom.utilities.spin.Spin2D;
import zeno.util.geom.utilities.spin.Spin3D;
import zeno.util.geom.utilities.spin.SpinND;

/**
 * The {@code Rotation} class defines an affine rotation.
 * It is defined as a homogeneous linear transformation
 * through the {@code ITransformation} interface.
 *
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public class Rotation implements ITransformation
{
	private static Spin DefaultSpin(int dim)
	{
		if(dim == 2)
			return new Spin2D();
		if(dim == 3)
			return new Spin3D();
		
		return new SpinND();
	}
	
	
	private Spin spin;
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param dim  a space dimension
	 */
	public Rotation(int dim)
	{
		this(DefaultSpin(dim));
	}
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param s  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public Rotation(Spin s)
	{
		spin = s;
	}
			
	/**
	 * Returns the rotation spin.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public Spin Spin()
	{
		return spin;
	}


	@Override
	public Matrix Inverse(int dim)
	{
		return spin.generate(dim).transpose();
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		return spin.generate(dim);
	}
}